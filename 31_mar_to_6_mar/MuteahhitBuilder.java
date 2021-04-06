
package entity;

public class MuteahhitBuilder {
    Eleman eleman;
    String is_alani;

    
    public MuteahhitBuilder SetEleman(Eleman eleman){
        this.eleman = eleman;
        return this;
    }
    
    public MuteahhitBuilder SetIs_alani(String is_alani){
        this.is_alani = is_alani;
        return this;
    }
    //muteahhit bilgileri doldurulduktan sonra yapilandirilmasi
    public Muteahhit build(){
        Muteahhit muteahhit = new Muteahhit(this);
        //MuteahhitNesneKontrol(muteahhit); baska bir asama
        return muteahhit;
    }
    
    public void MuteahhitNesneKontrol(Muteahhit muteahhit){
        //nesneyi dogru bir sekilde yuklenip yuklenmedigini kontrol ediler 
        //kodlarimizda gereken her hangi bir kontrol
    }
}
