
package muteahhitCRUD;

import elemanCRUD.ElemanSelection;
import entity.Muteahhit;
import entity.MuteahhitBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import postgreSQL.ConnectionUtil;

public class MuteahhitSelection {
    private Connection connection = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    ConnectionUtil connectionUtil = null;
    
    ElemanSelection eleman = new ElemanSelection();
    MuteahhitBuilder muteahhit;
    
    
    
    public List<Muteahhit> getMuteahhit(){
        List<Muteahhit> muteahhitList = new ArrayList<>();
        
        try {
            this.setPst(this.getConnection().prepareStatement("select * from muteahhit"));
            this.setRs(this.getPst().executeQuery());
            
            while(rs.next()){
                muteahhit = new MuteahhitBuilder();
                muteahhit.SetEleman(eleman.Find(rs.getInt("eleman_id")));
                muteahhit.SetIs_alani(rs.getString("is_alani"));
                
                muteahhitList.add(muteahhit.build());
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return muteahhitList;
    }
    
    
    
    public Muteahhit Find(int eleman_id) {

        //quary
        try {
            this.setPst(this.getConnection().prepareStatement("select * from muteahhit where eleman_id=?"));
            this.getPst().setInt(1, eleman_id);
            this.setRs(this.getPst().executeQuery());
            
            this.getRs().next();
            muteahhit = new MuteahhitBuilder();
            muteahhit.SetEleman(eleman.Find(rs.getInt("eleman_id")));
            muteahhit.SetIs_alani(rs.getString("is_alani"));
            
            
            
            //control
            System.out.println();
            System.out.println("Successfull serching");
            return muteahhit.build();

        } catch (SQLException ex) {
            //control
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
        MuteahhitSelection muteahit = new MuteahhitSelection();
        System.out.println(muteahit.getMuteahhit());
    }
}
