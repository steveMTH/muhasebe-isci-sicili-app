
package kasaCRUD;

import entity.Cikan_odemeler;
import entity.Kasa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import postgreSQL.ConnectionUtil;

public class KasaRemove {
    private Connection connection = null;
    private PreparedStatement pst = null;
    ConnectionUtil connectionUtil = null;
    
    
    public void delete(Kasa kasa) {

        try {
            
            this.setPst(this.getConnection().prepareStatement("delete from kasa where kasa_id=?"));
            this.getPst().setInt(1, kasa.getKasa_id());
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
