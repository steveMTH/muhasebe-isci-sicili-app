
package elemanCRUD;

import entity.Eleman;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import postgreSQL.ConnectionUtil;

public class ElemanRemove {
    private Connection connection = null;
    private PreparedStatement pst = null;
    ConnectionUtil connectionUtil = null;
    
    
    public void delete(Eleman eleman) {

        try {
            
            this.setPst(this.getConnection().prepareStatement("delete from eleman where isim=?"));
            this.getPst().setObject(1, eleman.getIsim());
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
