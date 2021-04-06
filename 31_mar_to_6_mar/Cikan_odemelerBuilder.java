
package entity;

import java.math.BigInteger;
import java.sql.Date;
import type.Name;

public class Cikan_odemelerBuilder {
    int odeme_id;
    Kasa kasa;
    String kimden;
    String kime;
    Date odeme_tarihi;
    BigInteger miktar;

    public Cikan_odemelerBuilder setOdeme_id(int odeme_id) {
        this.odeme_id = odeme_id;
        return this;
    }
    
    
    public Cikan_odemelerBuilder SetKasa(Kasa kasa){
        this.kasa = kasa;
        return this;
    }
    
    public Cikan_odemelerBuilder SetKimden(String kimden){
        this.kimden = kimden;
        return this;
    }
    
    public Cikan_odemelerBuilder SetKime(String kime){
        this.kime = kime;
        return this;
    }
    
    public Cikan_odemelerBuilder SetOdeme_tarihi(Date odeme_tarihi){
        this.odeme_tarihi = odeme_tarihi;
        return this;
    }
    
    public Cikan_odemelerBuilder SetMiktar(BigInteger miktar){
        this.miktar = miktar;
        return this;
    }
    
    
    //Cikan_odemeler bilgileri doldurulduktan sonra yapilandirilmasi
    public Cikan_odemeler build(){
        Cikan_odemeler cikan_odeme = new Cikan_odemeler(this);
        //Cikan_odemelerNesneKotrol(cikan_odeme); baska bir asama
        return cikan_odeme;
    }
    
    
    public void Cikan_odemelerNesneKotrol(Cikan_odemeler cikan_odeme){
        //nesneyi dogru bir sekilde yuklenip yuklenmedigini kontrol ediler 
        //kodlarimizda gereken her hangi bir kontrol
    }
}
