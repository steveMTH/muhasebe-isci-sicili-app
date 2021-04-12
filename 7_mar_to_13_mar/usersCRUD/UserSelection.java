
package usersCRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import postgreSQL.ConnectionUtil;

public class UserSelection {
    
    private Connection connection = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    ConnectionUtil connectionUtil = null;
    
    
    public String login(String username, String password) {

        //quary
        try {
            this.setPst(this.getConnection().prepareStatement("select * from users where username=? and password=?"));
            this.getPst().setString(1, username);
            this.getPst().setString(2, password);
            this.setRs(this.getPst().executeQuery());
            
            
            this.getRs().next();
            
            //control
            System.out.println("username = "+rs.getString("username")+"  password = "+rs.getString("username"));
            System.out.println("Successfull Login");
            return "Success";

        } catch (SQLException ex) {
            //control
            System.err.println("Wrong Login ......");
            return "Error";
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

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    public ConnectionUtil getConnectionUtil() {
        this.connectionUtil = new ConnectionUtil();
        return connectionUtil;
    }
    
    
    public static void main(String[] args) {
        UserSelection user = new UserSelection();
        System.out.println(user.login("ahmet alali", "123").equals("Success"));
    }
    
   
}
