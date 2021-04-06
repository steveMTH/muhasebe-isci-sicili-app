
package entity;

public class BolumBuilder {
    int bolum_id;
    String isim;
    String konum;

    public BolumBuilder SetBolum_id(int bolum_id){
        this.bolum_id = bolum_id;
        return this;
    }
    
    public BolumBuilder SetIsim(String isim){
        this.isim = isim;
        return this;
    }
    
    public BolumBuilder SetKonum(String konum){
        this.konum = konum;
        return this;
    }
    
    
    //Bolum bilgileri doldurulduktan sonra yapilandirilmasi
    public Bolum build(){
        Bolum bolum = new Bolum(this);
        //BolumNesneKontrol(bolum); baska bir asama
        return bolum;
    }
    
    
    public void BolumNesneKontrol(Bolum bloum){
        //nesneyi dogru bir sekilde yuklenip yuklenmedigini kontrol ediler 
        //kodlarimizda gereken her hangi bir kontrol
    }
}
