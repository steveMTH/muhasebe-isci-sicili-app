
package IsciCRUD;

import elemanCRUD.ElemanSelection;
import eleman_projede_calisirCRUD.eleman_projede_calisirRemove;
import entity.Isci;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import postgreSQL.ConnectionUtil;

public class IsciRemove {
    private Connection connection = null;
    private PreparedStatement pst = null;
    ConnectionUtil connectionUtil = null;
    
    eleman_projede_calisirRemove calisir;
   
    ElemanSelection eleman = new ElemanSelection();
    
    public void delete(Isci isci) {

        try {
            
            this.setPst(this.getConnection().prepareStatement("delete from isci where eleman_id=?"));
            this.getPst().setObject(1, isci.getEleman().getEleman_id());
            this.getPst().executeUpdate();
            
            if(isci.getEleman().getProje() != null){
                System.out.println(eleman.FindName(isci.getEleman().getTelefon_no()).getEleman_id());
                
                calisir.deleteElemanFromProje(eleman.FindName(isci.getEleman().getTelefon_no()).getEleman_id());
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
