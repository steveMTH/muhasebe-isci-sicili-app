
package kasaCRUD;

import bolumCRUD.BolumSelection;
import entity.Kasa;
import entity.KasaBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import postgreSQL.ConnectionUtil;
import projeCRUD.ProjeSelection;
import sozlesmeliCRUD.SozlesmeliSelection;

public class KasaInsertion {
    
    private Connection connection = null;
    private PreparedStatement pst = null;
    ConnectionUtil connectionUtil = null;
    
    public String insert(Kasa kasa) {

        try {
            this.setPst(this.getConnection().prepareStatement("insert into kasa values"
                    + "(default,"+kasa.getBolum().getBolum_id()+","+kasa.getProje().getProje_id()
                    +","+kasa.getMuhasip().getEleman().getEleman_id()+",'"+kasa.getSifre()+"')"));
            System.out.println(this.getPst().toString());
            this.getPst().executeUpdate();
            
            //control
            System.out.println("Successfull insertion");
            return "Success";
            
        } catch (SQLException ex) {
            //control
            System.out.println(ex.getMessage());
            System.err.println("Wrong insertion ......");
            return "Error";
        }
    }

    
    
    public Connection getConnection() {
        this.connection = ConnectionUtil.postgreSQLConnection();
        return connection;
    }

    public PreparedStatement getPst() {
        return pst;
    }

    public void setPst(PreparedStatement pst) {
        this.pst = pst;
    }


    public ConnectionUtil getConnectionUtil() {
        this.connectionUtil = new ConnectionUtil();
        return connectionUtil;
    }
    
    
    public static void main(String[] args) {
        KasaInsertion kasa = new KasaInsertion();
        
        KasaBuilder tmpKasa = new KasaBuilder();
        
        BolumSelection bolum = new BolumSelection();
        tmpKasa.SetBolum(bolum.FindID(1));
        
        ProjeSelection proje = new ProjeSelection();
        tmpKasa.SetProje(proje.Find(2));
        
        SozlesmeliSelection muhasip = new SozlesmeliSelection();
        tmpKasa.SetMuhasip(muhasip.Find(6));
        tmpKasa.SetSifre("123");
        
        
        kasa.insert(tmpKasa.build());
    }
}
