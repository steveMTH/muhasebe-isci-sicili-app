
package entity;

import java.sql.Date;
import type.Address;
import type.Name;

public class Eleman {
    private final int eleman_id;
    private final Bolum bolum_id;
    private final String isim;
    private final Date dogum_tarihi;
    private final String konum;
    private final String telefon_no;
    private final String cinsiyet;
    private final Proje proje;


    public Eleman(ElemanBuilder builder) {
        this.eleman_id = builder.eleman_id;
        this.bolum_id = builder.bolum_id;
        this.isim = builder.isim;
        this.dogum_tarihi = builder.dogum_tarihi;
        this.konum = builder.konum;
        this.telefon_no = builder.telefon_no;
        this.cinsiyet = builder.cinsiyet;
        this.proje = builder.proje;
    }

    

    public int getEleman_id() {
        return eleman_id;
    }


    public Bolum getBolum_id() {
        return bolum_id;
    }


    public String getIsim() {
        return isim;
    }

    public Date getDogum_tarihi() {
        return dogum_tarihi;
    }


    public String getKonum() {
        return konum;
    }


    public String getTelefon_no() {
        return telefon_no;
    }

    public String getCinsiyet() {
        return cinsiyet;
    }

    public Proje getProje() {
        return proje;
    }
    
    

    @Override
    public String toString() {
        return "Eleman{" + "eleman_id=" + eleman_id + ", bolum_id=" + bolum_id.getBolum_id() + ", isim=" + isim + ", dogum_tarihi=" + dogum_tarihi + ", konum=" + konum + ", telefon_no=" + telefon_no + ", cinsiyet=" + cinsiyet + '}';
    }

    
}
