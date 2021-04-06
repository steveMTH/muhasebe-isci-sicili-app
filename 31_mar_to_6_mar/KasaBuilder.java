
package entity;

import type.Address;

public class KasaBuilder {
    int kasa_id;
    Bolum bolum;
    Proje proje;
    Sozlesmeli muhasip;
    String sifre;

    public KasaBuilder setKasa_id(int kasa_id) {
        this.kasa_id = kasa_id;
        return this;
    }
    
    public KasaBuilder SetBolum(Bolum bolum){
        this.bolum = bolum;
        return this;
    }
    
    public KasaBuilder SetProje(Proje proje){
        this.proje = proje;
        return this;
    }
    
    public KasaBuilder SetMuhasip(Sozlesmeli muhasip){
        this.muhasip = muhasip;
        return this;
    }
    
    public KasaBuilder SetSifre(String sifre){
        this.sifre = sifre;
        return this;
    }
    //kasa bilgileri doldurulduktan sonra yapilandirilmasi
    public Kasa build(){
        Kasa kasa = new Kasa(this);
        //KasaNesneLontrol(kasa); baska bir asama
        return kasa;
    }
    
    public void KasaNesneLontrol(Kasa kasa){
        //nesneyi dogru bir sekilde yuklenip yuklenmedigini kontrol ediler 
        //kodlarimizda gereken her hangi bir kontrol
    }
}
