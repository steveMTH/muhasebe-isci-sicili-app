
package entity;

public class Isci {
    private final Eleman eleman;
    private final String meslek;

    public Isci(IsciBuilder builder) {
        this.eleman = builder.eleman;
        this.meslek = builder.meslek;
    }

    public Eleman getEleman() {
        return eleman;
    }

    public String getMeslek() {
        return meslek;
    }

    @Override
    public String toString() {
        return eleman + ", meslek=" + meslek;
    }

    
}
