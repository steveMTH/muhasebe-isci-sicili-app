
package usersCRUD;

import entity.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import postgreSQL.ConnectionUtil;

public class UsersUpdate {
    private Connection connection = null;
    private PreparedStatement pst = null;
    ConnectionUtil connectionUtil = null;
    
    
    public void update(Users user) {

        try {
            this.setPst(this.getConnection().prepareStatement("update users set sozlesmeli_id=?,username=?,password=? where user_id=?"));
            this.getPst().setInt(1, user.getSozlesmeli().getEleman().getEleman_id());
            this.getPst().setString(2, user.getUsername());
            this.getPst().setString(3, user.getPassword());
            this.getPst().setInt(3, user.getUser_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    
    public Connection getConnection() {
        this.connection = this.getConnectionUtil().postgreSQLConnection();
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
