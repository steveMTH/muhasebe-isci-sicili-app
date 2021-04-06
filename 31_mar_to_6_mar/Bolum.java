
package entity;

public class Bolum {
    private final int bolum_id;
    private final String isim;
    private final String konum;


    public Bolum(BolumBuilder builder) {
        this.bolum_id = builder.bolum_id;
        this.isim = builder.isim;
        this.konum = builder.konum;
    }

    public int getBolum_id() {
        return bolum_id;
    }


    public String getIsim() {
        return isim;
    }


    public String getKonum() {
        return konum;
    }

    @Override
    public String toString() {
        return "Bolum{" + "bolum_id=" + bolum_id + ", isim=" + isim + ", konum=" + konum + '}';
    }

    
    
}
