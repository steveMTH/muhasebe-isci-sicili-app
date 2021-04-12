
package projeCRUD;

import bolumCRUD.*;
import entity.Proje;
import entity.ProjeBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import postgreSQL.ConnectionUtil;

public class ProjeSelection {
    
    private Connection connection = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    ConnectionUtil connectionUtil = null;
    
    ProjeBuilder proje = null;
    BolumSelection bolum = new BolumSelection();
    
    
    public Proje Find(int proje_id) {

        //quary
        try {
            this.setPst(this.getConnection().prepareStatement("select * from proje where proje_id=?"));
            this.getPst().setInt(1, proje_id);
            this.setRs(this.getPst().executeQuery());
            
            this.getRs().next();
            proje = new ProjeBuilder();
            proje.setProje_id(rs.getInt("proje_id"));
            proje.SetBolum(bolum.FindID(rs.getInt("bolum_id")));
            proje.SetIsim(rs.getString("isim"));
            proje.SetKonum(rs.getString("konum"));
            
            
            //control
            System.out.println();
            System.out.println("Successfull serching");
            return proje.build();

        } catch (SQLException ex) {
            //control
            System.err.println("Wrong serching ......");
            return null;
        }

    }
    
    
    
    public List<Proje> getProje(){
        List<Proje> projeList = new ArrayList<>();
        
        try {
            this.setPst(this.getConnection().prepareStatement("select * from proje"));
            this.setRs(this.getPst().executeQuery());
            
            while(rs.next()){
                proje = new ProjeBuilder();
                proje.setProje_id(rs.getInt("proje_id"));
                proje.SetBolum(bolum.FindID(rs.getInt("bolum_id")));
                proje.SetIsim(rs.getString("isim"));
                proje.SetKonum(rs.getString("konum"));
                projeList.add(proje.build());
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return projeList;
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
        ProjeSelection proje = new ProjeSelection();
        System.out.println(proje.getProje());
    }
   
}
