
package entity;

import java.sql.Date;

public class Sozlesmeli {
    private final Eleman eleman;
    private final Date baslangic_tarihi;
    private final Date bitis_tarihi;
    private final String uzmanlik;


    public Sozlesmeli(SozlesmeliBuilder builder) {
        this.eleman = builder.eleman;
        this.baslangic_tarihi = builder.baslangic_tarihi;
        this.bitis_tarihi = builder.bitis_tarihi;
        this.uzmanlik = builder.uzmanlik;
    }

    public Eleman getEleman() {
        return eleman;
    }
    
    public Date getBaslangic_tarihi() {
        return baslangic_tarihi;
    }

    public Date getBitis_tarihi() {
        return bitis_tarihi;
    }

    public String getUzmanlik() {
        return uzmanlik;
    }

    @Override
    public String toString() {
        return "Sozlesmeli{" + "eleman=" + eleman + ", baslangic_tarihi=" + baslangic_tarihi + ", bitis_tarihi=" + bitis_tarihi + ", uzmanlik=" + uzmanlik + '}';
    }
    
    
}
