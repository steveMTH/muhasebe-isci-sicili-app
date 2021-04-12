
package sozlesmeliCRUD;

import elemanCRUD.ElemanSelection;
import eleman_projede_calisirCRUD.eleman_projede_calisirRemove;
import entity.Sozlesmeli;
import entity.eleman_projede_calisir;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import postgreSQL.ConnectionUtil;

public class SozlesmeliRemove {
    private Connection connection = null;
    private PreparedStatement pst = null;
    ConnectionUtil connectionUtil = null;
    
    eleman_projede_calisirRemove calisir;
   
    ElemanSelection eleman = new ElemanSelection();
    public void delete(Sozlesmeli sozlesmeli) {

        try {
            
            this.setPst(this.getConnection().prepareStatement("delete from sozlesmeli where eleman_id=?"));
            this.getPst().setObject(1, sozlesmeli.getEleman().getEleman_id());
            this.getPst().executeUpdate();
            
            if(sozlesmeli.getEleman().getProje() != null){
                System.out.println(eleman.FindName(sozlesmeli.getEleman().getTelefon_no()).getEleman_id());
                
                calisir.deleteElemanFromProje(eleman.FindName(sozlesmeli.getEleman().getTelefon_no()).getEleman_id());
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
    
    public static void main(String[] args) {
        SozlesmeliRemove sozlesmeli = new SozlesmeliRemove();
        SozlesmeliSelection tmpSozlesmeli = new SozlesmeliSelection();
        
        sozlesmeli.delete(tmpSozlesmeli.Find(5));
    }
    
}
