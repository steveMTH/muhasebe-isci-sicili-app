
package entity;

import java.math.BigInteger;
import java.sql.Date;
import type.Name;

public class Giren_odemeler {
    private final int odeme_id;
    private final Kasa kasa;
    private final String kimden;
    private final Date odeme_tarihi;
    private final BigInteger miktar;


    public Giren_odemeler(Giren_odemelerBuilder builder) {
        this.odeme_id = builder.odeme_id;
        this.kasa = builder.kasa;
        this.kimden = builder.kimden;
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

    public Date getOdeme_tarihi() {
        return odeme_tarihi;
    }

    public BigInteger getMiktar() {
        return miktar;
    }

    @Override
    public String toString() {
        return "Giren_odemeler{" + "odeme_id=" + odeme_id + ", kasa id =" + kasa.getKasa_id() + ", kimden=" + kimden + ", odeme_tarihi=" + odeme_tarihi + ", miktar=" + miktar + '}';
    }
    
    
}
