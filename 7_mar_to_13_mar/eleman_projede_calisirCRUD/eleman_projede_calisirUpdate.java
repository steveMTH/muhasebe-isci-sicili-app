
package eleman_projede_calisirCRUD;

import projeCRUD.*;
import entity.Proje;
import entity.eleman_projede_calisir;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import postgreSQL.ConnectionUtil;

public class eleman_projede_calisirUpdate {
    private Connection connection = null;
    private PreparedStatement pst = null;
    ConnectionUtil connectionUtil = null;
    
    
    public void update(eleman_projede_calisir calisir , eleman_projede_calisir oldCalisir) {

        try {
            this.setPst(this.getConnection().prepareStatement("update eleman_projede_calisir set eleman_id="
                    +calisir.getEleman_id().getEleman_id()+",proje_id='"+calisir.getProje_id().getProje_id()
                    +" where eleman_id="+oldCalisir.getProje_id()));
            
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
    
}
