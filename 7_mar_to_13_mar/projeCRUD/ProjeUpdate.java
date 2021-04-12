
package projeCRUD;

import entity.Proje;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import postgreSQL.ConnectionUtil;

public class ProjeUpdate {
    private Connection connection = null;
    private PreparedStatement pst = null;
    ConnectionUtil connectionUtil = null;
    
    
    public void update(Proje proje) {

        try {
            this.setPst(this.getConnection().prepareStatement("update proje set bolum_id="+proje.getBolum().getBolum_id()+
                    ",isim='"+proje.getIsim()+"',konum='"+proje.getKonum()+" where proje_id="+proje.getProje_id()));
            //this.getPst().setInt(1, proje.getBolum().getBolum_id());
            //this.getPst().setString(2, proje.getIsim());
            //this.getPst().setObject(3, proje.getKonum());
            //this.getPst().setInt(4, proje.getProje_id());
            
            pst.executeUpdate();
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
