
package entity;

import java.sql.Date;

public class MaaslarBuilder {
    int maas_id;
    Kasa kasa;
    Eleman eleman;
    Date odeme_tarihi;
    int miktar;

    public MaaslarBuilder setMaas_id(int maas_id) {
        this.maas_id = maas_id;
        return this;
    }
    
    public MaaslarBuilder SetKasa(Kasa kasa){
        this.kasa = kasa;
        return this;
    }
    
    public MaaslarBuilder SetEleman(Eleman eleman){
        this.eleman = eleman;
        return this;
    }
    
    public MaaslarBuilder SetOdeme_tarihi(Date odeme_tarihi){
        this.odeme_tarihi = odeme_tarihi;
        return this;
    }
    
    public MaaslarBuilder SetMiktar(int miktar){
        this.miktar = miktar;
        return this;
    }
    
    //Maaslar bilgileri doldurulduktan sonra yapilandirilmasi
    public Maaslar build(){
        Maaslar maaslar = new Maaslar(this);
        //MaaslarNesneKontrol(maaslar); baska bir asama
        return maaslar;
    }
    
    public void MaaslarNesneKontrol(Maaslar maaslar){
        //nesneyi dogru bir sekilde yuklenip yuklenmedigini kontrol ediler 
        //kodlarimizda gereken her hangi bir kontrol
    }
}
