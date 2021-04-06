
package entity;

public class eleman_projede_calisir {
    private Eleman eleman_id;
    private Proje proje_id;

    public eleman_projede_calisir() {
    }

    public eleman_projede_calisir(Eleman eleman_id, Proje proje_id) {
        this.eleman_id = eleman_id;
        this.proje_id = proje_id;
    }

    public Eleman getEleman_id() {
        return eleman_id;
    }

    public void setEleman_id(Eleman eleman_id) {
        this.eleman_id = eleman_id;
    }

    public Proje getProje_id() {
        return proje_id;
    }

    public void setProje_id(Proje proje_id) {
        this.proje_id = proje_id;
    }

    @Override
    public String toString() {
        return "eleman_projede_calisir{" + "eleman_id=" + eleman_id.getEleman_id() + ", proje_id=" + proje_id.getProje_id() + '}';
    }
    
    
}
