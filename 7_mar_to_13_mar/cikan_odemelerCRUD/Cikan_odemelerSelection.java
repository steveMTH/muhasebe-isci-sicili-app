
package cikan_odemelerCRUD;

import projeCRUD.*;
import entity.Cikan_odemeler;
import entity.Cikan_odemelerBuilder;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import kasaCRUD.KasaSelection;
import postgreSQL.ConnectionUtil;

public class Cikan_odemelerSelection {
    
    private Connection connection = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    ConnectionUtil connectionUtil = null;
    
    Cikan_odemelerBuilder cikan_odeme = null;
    KasaSelection kasa = new KasaSelection();
    ProjeSelection proje = new ProjeSelection();
    
    
    public Cikan_odemeler Find(int odeme_id) {

        //quary
        try {
            this.setPst(this.getConnection().prepareStatement("select * from cikan_odemeler where odeme_id=?"));
            this.getPst().setInt(1, odeme_id);
            this.setRs(this.getPst().executeQuery());
            
            this.getRs().next();
            cikan_odeme = new Cikan_odemelerBuilder();
            cikan_odeme.setOdeme_id(rs.getInt("odeme_id"));
            cikan_odeme.SetKasa(kasa.Find(rs.getInt("kasa_id")));
            cikan_odeme.SetKimden(rs.getString("kimden"));
            cikan_odeme.SetKimden(rs.getString("kime"));
            cikan_odeme.SetOdeme_tarihi(rs.getDate("odeme_tarihi"));
            cikan_odeme.SetMiktar(BigInteger.valueOf(rs.getInt("miktar")));
            
            
            //control
            System.out.println();
            System.out.println("Successfull serching");
            return cikan_odeme.build();

        } catch (SQLException ex) {
            //control
            System.err.println("Wrong serching ......");
            return null;
        }

    }
    
    
    
    public List<Cikan_odemeler> getCikan_odeme(){
        List<Cikan_odemeler> cikan_odemeList = new ArrayList<>();
        
        try {
            this.setPst(this.getConnection().prepareStatement("select * from cikan_odemeler"));
            this.setRs(this.getPst().executeQuery());
            
            while(rs.next()){
                cikan_odeme = new Cikan_odemelerBuilder();
                cikan_odeme.setOdeme_id(rs.getInt("odeme_id"));
                cikan_odeme.SetKasa(kasa.Find(rs.getInt("kasa_id")));
                cikan_odeme.SetKimden(rs.getString("kimden"));
                cikan_odeme.SetKime(rs.getString("kime"));
                cikan_odeme.SetOdeme_tarihi(rs.getDate("odeme_tarihi"));
                cikan_odeme.SetMiktar(BigInteger.valueOf(rs.getInt("miktar")));
            
                cikan_odemeList.add(cikan_odeme.build());
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return cikan_odemeList;
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
        Cikan_odemelerSelection odeme = new Cikan_odemelerSelection();
        
        System.out.println(odeme.getCikan_odeme());
    }
    
   
}
