
package cikan_odemelerCRUD;

import ekipmanCRUD.*;
import entity.Cikan_odemeler;
import entity.Ekipman;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import postgreSQL.ConnectionUtil;

public class Cikan_odemelerRemove {
    private Connection connection = null;
    private PreparedStatement pst = null;
    ConnectionUtil connectionUtil = null;
    
    
    public void delete(Cikan_odemeler cikan_odeme) {

        try {
            
            this.setPst(this.getConnection().prepareStatement("delete from cikan_odemeler where odeme_id=?"));
            this.getPst().setInt(1, cikan_odeme.getOdeme_id());
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
