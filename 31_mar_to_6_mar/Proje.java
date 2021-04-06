
package entity;

public class Proje {
    private final int proje_id;
    private final Bolum bolum;
    private final String isim;
    private final String konum;

    
    public Proje(ProjeBuilder builder) {
        this.proje_id = builder.proje_id;
        this.bolum = builder.bolum;
        this.isim = builder.isim;
        this.konum = builder.konum;
    }

    public int getProje_id() {
        return proje_id;
    }

    public Bolum getBolum() {
        return bolum;
    }

    public String getIsim() {
        return isim;
    }

    public String getKonum() {
        return konum;
    }

    @Override
    public String toString() {
        return "Proje{" + "proje_id=" + proje_id + ", bolum=" + bolum.getBolum_id() + ", isim=" + isim + ", konum=" + konum + '}';
    }
    
    
}
