
package elemanCRUD;

import entity.Eleman;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import postgreSQL.ConnectionUtil;

public class ElemanInsertion {
    
    private Connection connection = null;
    private PreparedStatement pst = null;
    ConnectionUtil connectionUtil = null;
    
    public String insert(Eleman eleman) {

        try {
            this.setPst(this.getConnection().prepareStatement("insert into eleman values"
                    + "(default,"+eleman.getBolum_id().getBolum_id()+","+eleman.getIsim()
                    +",'"+eleman.getDogum_tarihi()+"',"+eleman.getKonum()+",'"+eleman.getTelefon_no()
                    +"',"+eleman.getCinsiyet()+"')"));
            this.getPst().setInt(1, eleman.getBolum_id().getBolum_id());
            this.getPst().setObject(2, eleman.getIsim());
            this.getPst().setDate(3, eleman.getDogum_tarihi());
            this.getPst().setObject(4, eleman.getKonum());
            this.getPst().setString(5, eleman.getTelefon_no());
            this.getPst().setString(6, eleman.getCinsiyet());
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
