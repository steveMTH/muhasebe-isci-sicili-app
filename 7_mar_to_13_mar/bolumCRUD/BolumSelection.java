
package bolumCRUD;

import entity.Bolum;
import entity.BolumBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import postgreSQL.ConnectionUtil;
import type.Address;
import type.adres;

public class BolumSelection {
    
    private Connection connection = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    ConnectionUtil connectionUtil = null;
    BolumBuilder bolum = null;
    
    
    public Bolum FindID(int bolum_id) {

        //quary
        try {
            this.setPst(this.getConnection().prepareStatement("select * from bolum where bolum_id=?"));
            this.getPst().setInt(1, bolum_id);
            this.setRs(this.getPst().executeQuery());
            
            rs.next();
            bolum = new BolumBuilder();
            bolum.SetBolum_id(rs.getInt("bolum_id"));
            bolum.SetIsim(rs.getString("isim"));
            bolum.SetKonum(rs.getString("konum"));
            
            
            //control
            System.out.println();
            System.out.println("Successfull serching");
            return bolum.build();

        } catch (SQLException ex) {
            //control
            System.err.println("Wrong serching ......");
            return null;
        }

    }
    
    
    public Bolum FindName(String isim) {

        //quary
        try {
            this.setPst(this.getConnection().prepareStatement("select * from bolum where isim=?"));
            this.getPst().setString(1, isim);
            this.setRs(this.getPst().executeQuery());
            
            rs.next();
            bolum = new BolumBuilder();
            bolum.SetBolum_id(rs.getInt("bolum_id"));
            bolum.SetIsim(rs.getString("isim"));
            bolum.SetKonum(rs.getString("konum"));
            
            
            //control
            System.out.println();
            System.out.println("Successfull serching");
            return bolum.build();

        } catch (SQLException ex) {
            //control
            System.err.println("Wrong serching ......");
            return null;
        }

    }
    
    
    
    public List<Bolum> getBolum(){
        List<Bolum> bolumList = new ArrayList<>();
        
        try {
            this.setPst(this.getConnection().prepareStatement("select * from bolum"));
            this.setRs(this.getPst().executeQuery());
            
            while(rs.next()){
                bolum = new BolumBuilder();
                bolum.SetBolum_id(rs.getInt("bolum_id"));
                bolum.SetIsim(rs.getString("isim"));
                bolum.SetKonum(rs.getString("konum"));
                bolumList.add(bolum.build());
                
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return bolumList;
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
        BolumSelection bolum = new BolumSelection();
        System.out.println(bolum.getBolum());
        //System.out.println(bolum.Find(1));
    }
   
}
