
package entity;

public class ProjeBuilder {
    int proje_id;
    Bolum bolum;
    String isim;
    String konum;

    public void setProje_id(int proje_id) {
        this.proje_id = proje_id;
    }
    
    
    public ProjeBuilder SetBolum(Bolum bolum){
        this.bolum = bolum;
        return this;
    }
    
    public ProjeBuilder SetIsim(String isim){
        this.isim = isim;
        return this;
    }
    
    public ProjeBuilder SetKonum(String konum){
        this.konum = konum;
        return this;
    }
    
    
    //proje bilgileri doldurulduktan sonra yapilandirilmasi
    public Proje build(){
        Proje proje = new Proje(this);
        //projeNesneKontrol(proje); baska bir asama
        return proje;
    }
    
    public void projeNesneKontrol(Proje proje){
        //nesneyi dogru bir sekilde yuklenip yuklenmedigini kontrol ediler 
        //kodlarimizda gereken her hangi bir kontrol
    }
}
