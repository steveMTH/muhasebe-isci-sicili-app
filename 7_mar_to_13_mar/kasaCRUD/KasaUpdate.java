
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

public class KasaUpdate {
    private Connection connection = null;
    private PreparedStatement pst = null;
    ConnectionUtil connectionUtil = null;
    
    
    public void update(Kasa kasa) {

        try {
            this.setPst(this.getConnection().prepareStatement("update kasa set bolum_id="+kasa.getBolum().getBolum_id()
                    + ",proje_id="+kasa.getProje().getProje_id()+",muhasip_id="+kasa.getMuhasip().getEleman().getEleman_id()
                    +",sifre="+kasa.getSifre()+" where kasa_id="+kasa.getKasa_id()));
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
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
        KasaUpdate kasa = new KasaUpdate();
        
        KasaBuilder tmpKasa = new KasaBuilder();
        tmpKasa.setKasa_id(1);
        BolumSelection bolum = new BolumSelection();
        tmpKasa.SetBolum(bolum.FindID(1));
        
        ProjeSelection proje = new ProjeSelection();
        tmpKasa.SetProje(proje.Find(2));
        
        SozlesmeliSelection muhasip = new SozlesmeliSelection();
        tmpKasa.SetMuhasip(muhasip.Find(6));
        tmpKasa.SetSifre("123");
        
        
        kasa.update(tmpKasa.build());
    }
}
