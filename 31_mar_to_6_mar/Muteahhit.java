
package entity;

public class Muteahhit {
    private final Eleman eleman;
    private final String is_alani;

    public Muteahhit(MuteahhitBuilder builder) {
        this.eleman = builder.eleman;
        this.is_alani = builder.is_alani;
    }

    public Eleman getEleman() {
        return eleman;
    }
    
    public String getIs_alani() {
        return is_alani;
    }

    @Override
    public String toString() {
        return "eleman = {" + eleman + ", is_alani=" + is_alani + '}';
    }

    
    
}
