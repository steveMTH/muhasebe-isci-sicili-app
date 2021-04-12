
package projeCRUD;

import entity.Proje;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import postgreSQL.ConnectionUtil;

public class ProjeRemove {
    private Connection connection = null;
    private PreparedStatement pst = null;
    ConnectionUtil connectionUtil = null;
    
    
    public void delete(Proje proje) {

        try {
            
            this.setPst(this.getConnection().prepareStatement("delete from proje where proje_id=?"));
            this.getPst().setInt(1, proje.getProje_id());
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
        ProjeRemove proje = new ProjeRemove();
        ProjeSelection s = new ProjeSelection();
        
        proje.delete(s.Find(3));
    }
}
