
package entity;

import java.math.BigInteger;
import java.sql.Date;
import type.Name;

public class Cikan_odemeler {
    private final int odeme_id;
    private final Kasa kasa;
    private final String kimden;
    private final String kime;
    private final Date odeme_tarihi;
    private final BigInteger miktar;


    public Cikan_odemeler(Cikan_odemelerBuilder builder) {
        this.odeme_id = builder.odeme_id;
        this.kasa = builder.kasa;
        this.kimden = builder.kimden;
        this.kime = builder.kime;
        this.odeme_tarihi = builder.odeme_tarihi;
        this.miktar = builder.miktar;
    }

    public int getOdeme_id() {
        return odeme_id;
    }

    public Kasa getKasa() {
        return kasa;
    }


    public String getKimden() {
        return kimden;
    }


    public String getKime() {
        return kime;
    }

    public Date getOdeme_tarihi() {
        return odeme_tarihi;
    }

    public BigInteger getMiktar() {
        return miktar;
    }

    @Override
    public String toString() {
        return "Cikan_odemeler{" + "odeme_id=" + odeme_id + ", kasa=" + kasa.getKasa_id() + ", kimden=" + kimden + ", kime=" + kime + ", odeme_tarihi=" + odeme_tarihi + ", miktar=" + miktar + '}';
    }
    
    
    

    
}
