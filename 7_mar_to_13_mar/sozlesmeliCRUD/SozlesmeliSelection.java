
package sozlesmeliCRUD;

import elemanCRUD.ElemanSelection;
import entity.Sozlesmeli;
import entity.SozlesmeliBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import postgreSQL.ConnectionUtil;

public class SozlesmeliSelection {
    private Connection connection = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    ConnectionUtil connectionUtil = null;
    
    ElemanSelection eleman = new ElemanSelection();
    SozlesmeliBuilder sozlesmeli;
    
    
    
    public List<Sozlesmeli> getSozlesmeli(){
        List<Sozlesmeli> sozlesmeliList = new ArrayList<>();
        
        try {
            this.setPst(this.getConnection().prepareStatement("select * from sozlesmeli"));
            this.setRs(this.getPst().executeQuery());
            
            while(rs.next()){
                sozlesmeli = new SozlesmeliBuilder();
                sozlesmeli.SetEleman(eleman.Find(rs.getInt("eleman_id")));
                sozlesmeli.SetBaslangic_tarihi(rs.getDate("baslangic_tarihi"));
                sozlesmeli.SetBitis_tarihi(rs.getDate("bitis_tarihi"));
                sozlesmeli.SetUzmanlik(rs.getString("uzmanlik"));
                
                sozlesmeliList.add(sozlesmeli.build());
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return sozlesmeliList;
    }
    
    
    
    public Sozlesmeli Find(int eleman_id) {

        //quary
        try {
            this.setPst(this.getConnection().prepareStatement("select * from sozlesmeli where eleman_id=?"));
            this.getPst().setInt(1, eleman_id);
            this.setRs(this.getPst().executeQuery());
            
            this.getRs().next();
            sozlesmeli = new SozlesmeliBuilder();
            sozlesmeli.SetEleman(eleman.Find(rs.getInt("eleman_id")));
            sozlesmeli.SetBaslangic_tarihi(rs.getDate("baslangic_tarihi"));
            sozlesmeli.SetBitis_tarihi(rs.getDate("bitis_tarihi"));
            sozlesmeli.SetUzmanlik(rs.getString("uzmanlik"));
            
            
            
            //control
            System.out.println();
            System.out.println("Successfull serching");
            return sozlesmeli.build();

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
        SozlesmeliSelection sozlesmeli = new SozlesmeliSelection();
        System.out.println(sozlesmeli.getSozlesmeli());
    }
    
}
