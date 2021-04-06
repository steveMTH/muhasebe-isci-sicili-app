
package entity;

import java.sql.Date;


public class ElemanBuilder {
    int eleman_id;
    Bolum bolum_id;
    String isim;
    Date dogum_tarihi;
    String konum;
    String telefon_no;
    String cinsiyet;
    Proje proje = null;

    public ElemanBuilder SetEleman_id(int eleman_id){
        this.eleman_id = eleman_id;
        return this;
    }
    
    public ElemanBuilder SetBolum_id(Bolum bolum){
        BolumBuilder tmp = new BolumBuilder();
        tmp.SetBolum_id(bolum.getBolum_id());
        tmp.SetIsim(bolum.getIsim());
        tmp.SetKonum(bolum.getKonum());
        this.bolum_id = tmp.build();
        return this;
    }
    
    public ElemanBuilder SetIsim(String isim){
        this.isim = isim;
        return this;
    }
    
    public ElemanBuilder SetDogum_tarihi(Date dogum_tarihi){
        this.dogum_tarihi = dogum_tarihi;
        return this;
    }
    
    public ElemanBuilder SetKonum(String konum){
        this.konum = konum;
        return this;
    }
    
    public ElemanBuilder SetTelefon_no(String telefon_no){
        this.telefon_no = telefon_no;
        return this;
    }
    
    public ElemanBuilder SetCinsiyet(String cinsiyet){
        this.cinsiyet = cinsiyet;
        return this;
    }
    
    public ElemanBuilder SetProje(Proje proje){
        this.proje = proje;
        return this;
    }
    
    
    //Eleman bilgileri doldurulduktan sonra yapilandirilmasi
    public Eleman build(){
        Eleman eleman = new Eleman(this);
        //ElemanNesneKontrol(eleman); baska bir asama
        return eleman;
    }
    
    
    public void ElemanNesneKontrol(Eleman eleman){
        //nesneyi dogru bir sekilde yuklenip yuklenmedigini kontrol ediler 
        //kodlarimizda gereken her hangi bir kontrol
    }

}
