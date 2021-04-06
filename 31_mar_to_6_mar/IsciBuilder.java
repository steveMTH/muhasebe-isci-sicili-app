
package entity;

public class IsciBuilder {
    Eleman eleman;
    String meslek;


    public IsciBuilder SetEleman(Eleman eleman){
        this.eleman = eleman;
        return this;
    }
    
    public IsciBuilder SetMeslek(String meslek){
        this.meslek = meslek;
        return this;
    }
    
    //Isci bilgileri doldurulduktan sonra yapilandirilmasi
    public Isci build(){
        Isci isci = new Isci(this);
        //IsciNesneKontrol(isci); baska bir asama
        return isci;
    }
    
    public void IsciNesneKontrol(Isci isci){
        //nesneyi dogru bir sekilde yuklenip yuklenmedigini kontrol ediler 
        //kodlarimizda gereken her hangi bir kontrol
    }
}
