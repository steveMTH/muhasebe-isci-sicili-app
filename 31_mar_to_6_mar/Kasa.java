
package entity;

import type.Address;

public class Kasa {
    private final int kasa_id;
    private final Bolum bolum;
    private final Proje proje;
    private final Sozlesmeli muhasip;
    private final String sifre;


    public Kasa(KasaBuilder builder) {
        this.kasa_id = builder.kasa_id;
        this.bolum = builder.bolum;
        this.proje = builder.proje;
        this.muhasip = builder.muhasip;
        this.sifre = builder.sifre;
    }

    public int getKasa_id() {
        return kasa_id;
    }

    public Bolum getBolum() {
        return bolum;
    }


    public Proje getProje() {
        return proje;
    }


    public Sozlesmeli getMuhasip() {
        return muhasip;
    }

    public String getSifre() {
        return sifre;
    }

    @Override
    public String toString() {
        return "Kasa{" + "kasa_id=" + kasa_id + ", bolum id =" + bolum.getBolum_id() 
                + ", proje id =" + proje.getProje_id() + ", muhasip id =" 
                + muhasip.getEleman().getEleman_id() + ", sifre =" + sifre + '}';
    }


    
}
