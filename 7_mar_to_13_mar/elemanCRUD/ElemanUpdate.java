
package elemanCRUD;

import entity.Eleman;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import postgreSQL.ConnectionUtil;

public class ElemanUpdate {
    private Connection connection = null;
    private PreparedStatement pst = null;
    ConnectionUtil connectionUtil = null;
    
    
    
    
    public void update(Eleman eleman) {

        try {
            this.setPst(this.getConnection().prepareStatement("update eleman set bolum_id=?,isim=?"
                    + ",dogum_tarihi=?,konum=?,telefon_no=?,cinsiyet=? where eleman_id=?"));
            this.getPst().setInt(1, eleman.getBolum_id().getBolum_id());
            this.getPst().setObject(2, eleman.getIsim());
            this.getPst().setDate(3, eleman.getDogum_tarihi());
            this.getPst().setObject(4, eleman.getKonum());
            this.getPst().setString(5, eleman.getTelefon_no());
            this.getPst().setString(6, eleman.getCinsiyet());
            this.getPst().setInt(7, eleman.getEleman_id());
            
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
