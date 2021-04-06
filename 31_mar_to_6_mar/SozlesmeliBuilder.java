
package entity;

import java.sql.Date;

public class SozlesmeliBuilder {
    Eleman eleman;
    Date baslangic_tarihi;
    Date bitis_tarihi;
    String uzmanlik;


    public SozlesmeliBuilder SetEleman(Eleman eleman){
        this.eleman = eleman;
        return this;
    }
    
    public SozlesmeliBuilder SetBaslangic_tarihi(Date baslangic_tarihi){
        this.baslangic_tarihi = baslangic_tarihi;
        return this;
    }
    
    public SozlesmeliBuilder SetBitis_tarihi(Date bitis_tarihi){
        this.bitis_tarihi = bitis_tarihi;
        return this;
    }
    
    public SozlesmeliBuilder SetUzmanlik(String uzmanlik){
        this.uzmanlik = uzmanlik;
        return this;
    }
    
    //sozlesmeli bilgileri doldurulduktan sonra yapilandirilmasi
    public Sozlesmeli build(){
        Sozlesmeli sozlesmeli = new Sozlesmeli(this);
        //sozlesmeliNesneKontrol(sozlesmeli); baska bir asama
        return sozlesmeli;
    }
    
    public void sozlesmeliNesneKontrol(Sozlesmeli sozlesmeli){
        //nesneyi dogru bir sekilde yuklenip yuklenmedigini kontrol ediler 
        //kodlarimizda gereken her hangi bir kontrol
    }
}
