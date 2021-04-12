
package bolumCRUD;

import entity.Bolum;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import postgreSQL.ConnectionUtil;

public class BolumRemove {
    private Connection connection = null;
    private PreparedStatement pst = null;
    ConnectionUtil connectionUtil = null;
    
    
    public void delete(Bolum bolum) {

        try {
            
            this.setPst(this.getConnection().prepareStatement("delete from bolum where isim=?"));
            this.getPst().setString(1, bolum.getIsim());
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
    
    public static void main(String[] args) {
        BolumRemove bolum = new BolumRemove();
        BolumSelection find = new BolumSelection();
        
        bolum.delete(find.FindID(1));
    }
    
}
