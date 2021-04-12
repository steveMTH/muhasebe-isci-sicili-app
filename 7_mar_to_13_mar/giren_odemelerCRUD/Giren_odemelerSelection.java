
package giren_odemelerCRUD;

import projeCRUD.*;
import entity.Giren_odemeler;
import entity.Giren_odemelerBuilder;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import kasaCRUD.KasaSelection;
import postgreSQL.ConnectionUtil;
import type.Name;

public class Giren_odemelerSelection {
    
    private Connection connection = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    ConnectionUtil connectionUtil = null;
    
    Giren_odemelerBuilder giren_odeme = null;
    KasaSelection kasa = new KasaSelection();
    ProjeSelection proje = new ProjeSelection();
    
    
    public Giren_odemeler Find(int odeme_id) {

        //quary
        try {
            this.setPst(this.getConnection().prepareStatement("select * from giren_odemeler where odeme_id=?"));
            this.getPst().setInt(1, odeme_id);
            this.setRs(this.getPst().executeQuery());
            
            this.getRs().next();
            //System.out.println(this.getRs().toString());
            giren_odeme = new Giren_odemelerBuilder();
            giren_odeme.setOdeme_id(rs.getInt("odeme_id"));
            giren_odeme.SetKasa(kasa.Find(rs.getInt("kasa_id")));
            giren_odeme.SetKimden(rs.getString("kimden"));
            giren_odeme.SetOdeme_tarihi(rs.getDate("odeme_tarihi"));
            giren_odeme.SetMiktar(BigInteger.valueOf(rs.getLong("miktar")));
            
            
            //control
            System.out.println();
            System.out.println("Successfull serching");
            return giren_odeme.build();

        } catch (SQLException ex) {
            //control
            System.out.println(ex.getMessage());
            System.err.println("Wrong serching ......");
            return null;
        }

    }
    
    
    
    public List<Giren_odemeler> getOdeme(){
        List<Giren_odemeler> cikan_odemeList = new ArrayList<>();
        
        try {
            this.setPst(this.getConnection().prepareStatement("select * from giren_odemeler"));
            this.setRs(this.getPst().executeQuery());
            
            while(rs.next()){
                giren_odeme = new Giren_odemelerBuilder();
                giren_odeme.setOdeme_id(rs.getInt("odeme_id"));
                giren_odeme.SetKasa(kasa.Find(rs.getInt("kasa_id")));
                giren_odeme.SetKimden(rs.getString("kimden"));
                giren_odeme.SetOdeme_tarihi(rs.getDate("odeme_tarihi"));
                giren_odeme.SetMiktar(BigInteger.valueOf(rs.getInt("miktar")));
            
                cikan_odemeList.add(giren_odeme.build());
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
        Giren_odemelerSelection odeme = new Giren_odemelerSelection();
        
        System.out.println(odeme.getOdeme());
    }
}
