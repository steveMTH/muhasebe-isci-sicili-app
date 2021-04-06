
package entity;

import java.sql.Date;

public class Maaslar {
    private final int maas_id;
    private final Kasa kasa;
    private final Eleman eleman;
    private final Date odeme_tarihi;
    private final int miktar;


    public Maaslar(MaaslarBuilder builder) {
        this.maas_id = builder.maas_id;
        this.kasa = builder.kasa;
        this.eleman = builder.eleman;
        this.odeme_tarihi = builder.odeme_tarihi;
        this.miktar = builder.miktar;
    }

    public int getMaas_id() {
        return maas_id;
    }

    public Kasa getKasa() {
        return kasa;
    }

    public Eleman getEleman() {
        return eleman;
    }

    public Date getOdeme_tarihi() {
        return odeme_tarihi;
    }

    public int getMiktar() {
        return miktar;
    }

    @Override
    public String toString() {
        return "Maaslar{" + "maas_id=" + maas_id + ", kasa=" + kasa.getKasa_id() + ", eleman=" + eleman.getIsim() + ", odeme_tarihi=" + odeme_tarihi + ", miktar=" + miktar + '}';
    }

    
}
