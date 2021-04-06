
package entity;

import java.math.BigInteger;
import java.sql.Date;
import type.Name;

public class Giren_odemelerBuilder {
    int odeme_id;
    Kasa kasa;
    String kimden;
    Date odeme_tarihi;
    BigInteger miktar;

    public Giren_odemelerBuilder setOdeme_id(int odeme_id) {
        this.odeme_id = odeme_id;
        return this;
    }
    
    public Giren_odemelerBuilder SetKasa(Kasa kasa){
        this.kasa = kasa;
        return this;
    }
    
    public Giren_odemelerBuilder SetKimden(String kimden){
        this.kimden = kimden;
        return this;
    }
    
    public Giren_odemelerBuilder SetOdeme_tarihi(Date odeme_tarihi){
        this.odeme_tarihi = odeme_tarihi;
        return this;
    }
    
    public Giren_odemelerBuilder SetMiktar(BigInteger miktar){
        this.miktar = miktar;
        return this;
    }
    
    //Giren_odemeler bilgileri doldurulduktan sonra yapilandirilmasi
    public Giren_odemeler build(){
        Giren_odemeler giren_odeme = new Giren_odemeler(this);
        //Giren_odemelerNesneKotrol(giren_odeme); baska bir asama
        return giren_odeme;
    }
    
    
    public void Giren_odemelerNesneKotrol(Giren_odemeler giren_odemeler){
        //nesneyi dogru bir sekilde yuklenip yuklenmedigini kontrol ediler 
        //kodlarimizda gereken her hangi bir kontrol
    }
}
