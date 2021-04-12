
package eleman_projede_calisirCRUD;

import projeCRUD.*;
import bolumCRUD.*;
import elemanCRUD.ElemanSelection;
import entity.Proje;
import entity.ProjeBuilder;
import entity.eleman_projede_calisir;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import postgreSQL.ConnectionUtil;

public class eleman_projede_calisirSelection {
    
    private Connection connection = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    ConnectionUtil connectionUtil = null;
    
    eleman_projede_calisir calisir;
    ElemanSelection eleman = new ElemanSelection();
    ProjeSelection proje = new ProjeSelection();
    
    
   
    public eleman_projede_calisir FindEleman(int eleman_id) {

        try {
            this.setPst(this.getConnection().prepareStatement("select * from eleman_projede_calisir where eleman_id=?"));
            this.getPst().setInt(1, eleman_id);
            this.setRs(this.getPst().executeQuery());
            
            while(rs.next()){
                calisir = new eleman_projede_calisir();
                calisir.setEleman_id(eleman.Find(rs.getInt("eleman_id")));
                calisir.setProje_id(proje.Find(rs.getInt("proje_id")));
                return calisir;
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return null;

    }
    
    public List<eleman_projede_calisir> FindProje(int proje_id){
        List<eleman_projede_calisir> calisirList = new ArrayList<>();
        
        try {
            this.setPst(this.getConnection().prepareStatement("select * from eleman_projede_calisir where proje_id=?"));
            this.getPst().setInt(1, proje_id);
            this.setRs(this.getPst().executeQuery());
            
            while(rs.next()){
                calisir = new eleman_projede_calisir();
                calisir.setEleman_id(eleman.Find(rs.getInt("eleman_id")));
                calisir.setProje_id(proje.Find(rs.getInt("proje_id")));
                calisirList.add(calisir);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return calisirList;
    }
    
    
    
    public List<eleman_projede_calisir> getEleman_projede_calisir(){
        List<eleman_projede_calisir> calisirList = new ArrayList<>();
        
        try {
            this.setPst(this.getConnection().prepareStatement("select * from eleman_projede_calisir"));
            this.setRs(this.getPst().executeQuery());
            
            while(rs.next()){
                calisir = new eleman_projede_calisir();
                calisir.setEleman_id(eleman.Find(rs.getInt("eleman_id")));
                calisir.setProje_id(proje.Find(rs.getInt("proje_id")));
                calisirList.add(calisir);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return calisirList;
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
        eleman_projede_calisirSelection calisir = new eleman_projede_calisirSelection();
        //System.out.println(calisir.getEleman_projede_calisir());
        //System.out.println(calisir.FindEleman(4));
        System.out.println(calisir.FindProje(2));
    }
   
}
