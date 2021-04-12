
package IsciCRUD;

import elemanCRUD.ElemanSelection;
import entity.Isci;
import entity.IsciBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import postgreSQL.ConnectionUtil;

public class IsciSelection {
    private Connection connection = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    ConnectionUtil connectionUtil = null;
    
    ElemanSelection eleman = new ElemanSelection();
    IsciBuilder isci;
    
    
    
    public List<Isci> getIsci(){
        List<Isci> isciList = new ArrayList<>();
        
        try {
            this.setPst(this.getConnection().prepareStatement("select * from isci"));
            this.setRs(this.getPst().executeQuery());
            
            while(rs.next()){
                isci = new IsciBuilder();
                isci.SetEleman(eleman.Find(rs.getInt("eleman_id")));
                isci.SetMeslek(rs.getString("meslek"));
                
                isciList.add(isci.build());
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return isciList;
    }
    
    
    
    public Isci Find(int eleman_id) {

        //quary
        try {
            this.setPst(this.getConnection().prepareStatement("select * from isci where eleman_id=?"));
            this.getPst().setInt(1, eleman_id);
            this.setRs(this.getPst().executeQuery());
            
            this.getRs().next();
            isci = new IsciBuilder();
            isci.SetEleman(eleman.Find(rs.getInt("eleman_id")));
            isci.SetMeslek(rs.getString("meslek"));
            
            
            
            //control
            System.out.println();
            System.out.println("Successfull serching");
            return isci.build();

        } catch (SQLException ex) {
            //control
            System.out.println(ex.getMessage());
            System.err.println("Wrong serching ......");
            return null;
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

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }
    
    
    public static void main(String[] args) {
        IsciSelection isci = new IsciSelection();
        //System.out.println(isci.getIsci());
        System.out.println(isci.Find(1));
    }
    
}
