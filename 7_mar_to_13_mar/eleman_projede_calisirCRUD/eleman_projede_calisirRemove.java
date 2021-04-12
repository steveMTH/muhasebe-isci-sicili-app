
package eleman_projede_calisirCRUD;

import projeCRUD.*;
import entity.Proje;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import postgreSQL.ConnectionUtil;

public class eleman_projede_calisirRemove {
    private Connection connection = null;
    private PreparedStatement pst = null;
    ConnectionUtil connectionUtil = null;
    
    
    public void deleteElemanFromProje(int eleman_id) {

        try {
            
            this.setPst(this.getConnection().prepareStatement("delete from eleman_projede_calisir where eleman_id=?"));
            this.getPst().setInt(1, eleman_id);
            this.getPst().executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void deleteProje(int proje_id) {

        try {
            
            this.setPst(this.getConnection().prepareStatement("delete from eleman_projede_calisir where proje_id=?"));
            this.getPst().setInt(1, proje_id);
            this.getPst().executeUpdate();
            
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
