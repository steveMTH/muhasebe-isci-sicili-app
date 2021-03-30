
package postgreSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    
    public static Connection postgreSQLConnection(){
        Connection connection = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:1409/creative_vision", "postgres", "123");
            return connection;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("ConnectionUtil : "+ex.getMessage());
            return null;
        }   
        
    }
   
    
}
