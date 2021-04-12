
package usersCRUD;

import entity.Users;
import entity.UsersBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import postgreSQL.ConnectionUtil;
import sozlesmeliCRUD.SozlesmeliSelection;

public class UserInsertion {
    
    private Connection connection = null;
    private PreparedStatement pst = null;
    ConnectionUtil connectionUtil = null;
    
    public String insert(Users user) {

        try {
            this.setPst(this.getConnection().prepareStatement("insert into users (sozlesmeli_id,username,password) values(?,?,?)"));
            this.getPst().setInt(1, user.getSozlesmeli().getEleman().getEleman_id());
            this.getPst().setString(2, user.getUsername());
            this.getPst().setString(3, user.getPassword());
            this.getPst().executeUpdate();
            
            //control
            System.out.println("Successfull insertion");
            return "Success";
            
        } catch (SQLException ex) {
            //control
            System.err.println("Wrong insertion ......");
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


    public ConnectionUtil getConnectionUtil() {
        this.connectionUtil = new ConnectionUtil();
        return connectionUtil;
    }
    
    public static void main(String[] args) {
        UserInsertion user = new UserInsertion();
        UsersBuilder tmpUser = new UsersBuilder();
        
        SozlesmeliSelection sozlesmeli = new SozlesmeliSelection();
        tmpUser.SetSozlesmeli(sozlesmeli.Find(4));
        
        tmpUser.SetUsername("ahmet alali");
        tmpUser.SetPassword("123");
        
        user.insert(tmpUser.build());
    }
    
}
