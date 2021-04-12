
package muteahhitCRUD;

import elemanCRUD.ElemanSelection;
import eleman_projede_calisirCRUD.eleman_projede_calisirRemove;
import entity.Muteahhit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import postgreSQL.ConnectionUtil;

public class MuteahhitRemove {
    private Connection connection = null;
    private PreparedStatement pst = null;
    ConnectionUtil connectionUtil = null;
    
    eleman_projede_calisirRemove calisir;
   
    ElemanSelection eleman = new ElemanSelection();
    
    
    public void delete(Muteahhit muteahhit) {

        try {
            
            this.setPst(this.getConnection().prepareStatement("delete from muteahhit where eleman_id=?"));
            this.getPst().setObject(1, muteahhit.getEleman().getEleman_id());
            this.getPst().executeUpdate();
            
            if(muteahhit.getEleman().getProje() != null){
                System.out.println(eleman.FindName(muteahhit.getEleman().getTelefon_no()).getEleman_id());
                
                calisir.deleteElemanFromProje(eleman.FindName(muteahhit.getEleman().getTelefon_no()).getEleman_id());
            }
            
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
    
}
