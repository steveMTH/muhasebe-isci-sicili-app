


create type name AS(
	first_name varchar(30),
	middle_name varchar(30),
	last_name varchar(30)
);

create type address AS(
	country varchar(50),
	city varchar(50),
	street varchar(50),
	address text
);

create type sex AS ENUM('M','F');


create table eleman(
	eleman_id serial not null primary key,
	bolum_id integer references bolum(bolum_id) on delete cascade on update cascade ,
	isim name not null,
	dogum_tarihi date not null,
	konum address not null,
	telefon_no varchar(14) not null,
	cinsiyet sex not null
);

select * from eleman;


create table eleman_elemani_yonetir(
	from_id int not null,
	to_id int not null,
	primary key(from_id,to_id),
	foreign key(from_id) references eleman(eleman_id),
	foreign key(to_id) references eleman(eleman_id)
);

create table isci(
	meslek varchar(50) not null
)inherits(eleman);

insert into isci values(default,1,ROW('mustafa','taiseer','alhammouri'),'1998-01-29',
						ROW('irak','erbil','dream','irak erbil dream city'),'+905396415223','M','sdv');
select * from isci;


select * from eleman where bolum_id is null;


create table muteahhit(
	is_alani varchar(50) not null
)inherits(eleman);

select * from muteahhit;

create table sozlesmeli(
	baslangic_tarihi date not null,
	bitis_tarihi date not null,
	uzmanlik varchar(50) not null
)inherits(eleman);

select * from sozlesmeli where uzmanlik = 'muhasip';
update sozlesmeli set isim=ROW('anas','','aybur') where eleman_id=6;

select * from sozlesmeli where eleman_id = 16;


create table bolum(
	bolum_id serial primary key,
	isim varchar(40) not null,
	konum address not null
);

insert into bolum values(default,'creative vision',ROW('irak','erbil','dream','irak erbil dream city'));
update bolum set konum=Row('','','','irak') where isim='vision';

select * from bolum;



create table proje(
	proje_id serial primary key,
	bolum_id integer not null references bolum(bolum_id) on delete cascade on update cascade,
	isim varchar(50) not null,
	konum address not null
);

insert into proje values(default,1,'tekrit',ROW('irak','tekrit','','irak tekrit'));
select * from proje;
delete from proje where proje_id=1;


create table eleman_projede_calisir(
	eleman_id integer references eleman(eleman_id) on delete cascade on update cascade,
	proje_id integer references proje(proje_id) on delete cascade on update cascade,
	primary key(eleman_id,proje_id)
);

select * from eleman_projede_calisir;
insert into eleman_projede_calisir values(18,4);

//one to one ilişkisinde hata oluştupundan dolayı function çözümüne yönelindi
//çıkan problem : birden fazla kalıtım seviyesi kurulduğunda veriler çekişiyor ve tekrarlanmaya musait

create or replace function muhasip_id_its_exists(id int)
returns bool as
$BODY$
begin
if EXISTS (select 1 from sozlesmeli where eleman_id = id and uzmanlik = 'muhasip') then
	return true;
else return false;
end if;
end;
$BODY$
LANGUAGE plpgsql VOLATILE;

select muhasip_id_its_exists from muhasip_id_its_exists(6)

select kasa.kasa_id from kasa kasa
inner join sozlesmeli sozlesmeli on sozlesmeli.eleman_id = 6 and sozlesmeli.bolum_id = kasa.bolum_id

create or replace function kasa_id_from_muhasip(id int)
returns integer as
$BODY$
begin
if EXISTS (select 1 from sozlesmeli where eleman_id = id and muhasip_id_its_exists(id) = true and bolum_id = 0) then
	return (select kasa.kasa_id from kasa kasa
			inner join eleman_projede_calisir p_c on p_c.eleman_id = id and p_c.proje_id = kasa.proje_id);
elseif EXISTS (select 1 from sozlesmeli where eleman_id = id and muhasip_id_its_exists(id) = true and bolum_id != 0) then
	return (select kasa.kasa_id from kasa kasa
			inner join sozlesmeli sozlesmeli on sozlesmeli.eleman_id = 6 and sozlesmeli.bolum_id = kasa.bolum_id);
else return 0;
end if;
end;
$BODY$
LANGUAGE plpgsql VOLATILE;

select kasa_id_from_muhasip from kasa_id_from_muhasip(16);



create or replace function mudur_id_its_exists(id int)
returns bool as
$BODY$
begin
if EXISTS (select 1 from sozlesmeli where eleman_id = id and uzmanlik = 'mudur') then
	return true;
else return false;
end if;
end;
$BODY$
LANGUAGE plpgsql VOLATILE;
select * from mudur_id_its_exists(17)


create or replace function mudur_bolum_id_from_eleman_id(id int)
returns integer as
$BODY$
begin
if (mudur_id_its_exists(id) = true) then
	return (select sozlesmeli.bolum_id from sozlesmeli sozlesmeli
			where eleman_id = id);
else return 0;
end if;
end;
$BODY$
LANGUAGE plpgsql VOLATILE;

select mudur_bolum_id_from_eleman_id from mudur_bolum_id_from_eleman_id(17)



create or replace function mudur_proje_id_from_eleman_id(id int)
returns integer as
$BODY$
begin
if (mudur_bolum_id_from_muhasip(id) = 0) then
	return (select p_c.proje_id from eleman_projede_calisir p_c
			where eleman_id = id);
else return 0;
end if;
end;
$BODY$
LANGUAGE plpgsql VOLATILE;


select * from isci isci
inner join eleman_projede_calisir p_c on p_c.eleman_id = isci.eleman_id and proje_id = 2

select * from sozlesmeli sozlesmeli
inner join eleman_projede_calisir p_c on p_c.eleman_id = sozlesmeli.eleman_id and proje_id = 4

select * from muteahhit muteahhit
inner join eleman_projede_calisir p_c on p_c.eleman_id = muteahhit.eleman_id and proje_id = 2



select * from mudur_bolum_id_from_muhasip(17);
select * from mudur_proje_id_from_muhasip(15);
select * from muhasip_id_its_exists(4)
delete from eleman_projede_calisir where eleman_id = 17
delete from eleman_projede_calisir where eleman_id = 10 and proje_id = 4

select * from isci where bolum_id != 0
select * from sozlesmeli where bolum_id != 0
select * from muteahhit where bolum_id != 0
select * from eleman_projede_calisir

select * from sozlesmeli where isim = ROW('mustafa',' ','alhammouri')::name
select * from sozlesmeli where isim = ROW('ahmet',' ','alali')::name
update sozlesmeli set isim=ROW('anas',' ','aybur') where eleman_id = 6
select * from users




create table kasa(
	kasa_id serial primary key,
	bolum_id integer UNIQUE REFERENCES bolum(bolum_id) on delete cascade on update cascade,
	proje_id integer UNIQUE REFERENCES proje(proje_id) on delete cascade on update cascade,
	muhasip_id integer UNIQUE not null check(muhasip_id_its_exists(muhasip_id) = true),
	sifre varchar(50) not null
);
insert into kasa values(default,1,2,6,'123');
update kasa set bolum_id = 0 where kasa_id = 1
select * from sozlesmeli
select * from kasa where Bolum_id = 1;
select * from kasa where proje_id != 0


create table giren_odemeler(
	odeme_id serial primary key,
	kasa_id integer not null references kasa(kasa_id) on delete cascade on update cascade,
	kimden name not null,
	odeme_tarihi date not null,
	miktar BIGINT not null
);

select * from giren_odemeler;

create table cikan_odemeler(
	odeme_id serial primary key,
	kasa_id integer not null references kasa(kasa_id) on delete cascade on update cascade,
	kimden name not null,
	kime name not null,
	odeme_tarihi date not null,
	miktar BIGINT not null
);

select * from cikan_odemeler;

create table maaslar(
	maas_id serial primary key,
	kasa_id integer not null references kasa(kasa_id) on delete cascade on update cascade,
	eleman_id integer UNIQUE REFERENCES eleman(eleman_id) on delete cascade on update cascade,
	odeme_tarihi date not null,
	miktar int not null
);

select * from maaslar


create table ekipman(
	aletin_id serial primary key,
	bolum_id integer REFERENCES bolum(bolum_id) on delete cascade on update cascade,
	proje_id integer REFERENCES proje(proje_id) on delete cascade on update cascade,
	aletin_tipi varchar(50) not null,
	plaka_no varchar(10)
); 

select * from ekipman;


create or replace function sozlesmeli_is_exists(id int)
returns bool as
$BODY$
begin
if EXISTS (select 1 from sozlesmeli where eleman_id = id) then
	return true;
else return false;
end if;
end;
$BODY$
LANGUAGE plpgsql VOLATILE;

create table users(
	user_id serial primary key,
	sozlesmeli_id integer UNIQUE not null check(sozlesmeli_is_exists(sozlesmeli_id) = true),
	username varchar(100) not null,
	password varchar(50) not null
);




