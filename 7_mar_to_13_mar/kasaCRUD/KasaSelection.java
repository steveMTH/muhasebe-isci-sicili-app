
package kasaCRUD;

import bolumCRUD.BolumSelection;
import projeCRUD.*;
import entity.Kasa;
import entity.KasaBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import postgreSQL.ConnectionUtil;
import sozlesmeliCRUD.SozlesmeliSelection;
import type.Address;

public class KasaSelection {
    
    private Connection connection = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    ConnectionUtil connectionUtil = null;
    
    KasaBuilder kasa = null;
    BolumSelection bolum = new BolumSelection();
    ProjeSelection proje = new ProjeSelection();
    SozlesmeliSelection muhasip = new SozlesmeliSelection();
    
    
    public Kasa Find(int kasa_id) {

        //quary
        try {
            this.setPst(this.getConnection().prepareStatement("select * from kasa where kasa_id=?"));
            this.getPst().setInt(1, kasa_id);
            this.setRs(this.getPst().executeQuery());
            
            this.getRs().next();
            kasa = new KasaBuilder();
            kasa.setKasa_id(rs.getInt("kasa_id"));
            kasa.SetBolum(bolum.FindID(rs.getInt("bolum_id")));
            kasa.SetProje(proje.Find(rs.getInt("proje_id")));
            kasa.SetMuhasip(muhasip.Find(rs.getInt("muhasip_id")));
            kasa.SetSifre(rs.getString("sifre"));
            
            
            
            //control
            System.out.println();
            System.out.println("Successfull serching");
            return kasa.build();

        } catch (SQLException ex) {
            //control
            System.err.println("Wrong serching ......");
            return null;
        }

    }
    
    
    
    public List<Kasa> getKasa(){
        List<Kasa> kasaList = new ArrayList<>();
        
        try {
            this.setPst(this.getConnection().prepareStatement("select * from kasa"));
            this.setRs(this.getPst().executeQuery());
            System.out.println(this.getRs().toString());
            
            while(rs.next()){
                kasa = new KasaBuilder();
                kasa.setKasa_id(rs.getInt("kasa_id"));
                kasa.SetBolum(bolum.FindID(rs.getInt("bolum_id")));
                kasa.SetProje(proje.Find(rs.getInt("proje_id")));
                kasa.SetMuhasip(muhasip.Find(rs.getInt("muhasip_id")));
                kasa.SetSifre(rs.getString("sifre"));
            
                kasaList.add(kasa.build());
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return kasaList;
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
        KasaSelection kasa = new KasaSelection();
        System.out.println(kasa.getKasa());
    }
}
