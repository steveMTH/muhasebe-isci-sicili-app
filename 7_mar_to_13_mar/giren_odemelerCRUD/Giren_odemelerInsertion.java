
package giren_odemelerCRUD;

import entity.Giren_odemeler;
import entity.Giren_odemelerBuilder;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import kasaCRUD.KasaSelection;
import postgreSQL.ConnectionUtil;
import type.Name;

public class Giren_odemelerInsertion {
    
    private Connection connection = null;
    private PreparedStatement pst = null;
    ConnectionUtil connectionUtil = null;
    
    public String insert(Giren_odemeler Giren_odeme) {

        try {
            this.setPst(this.getConnection().prepareStatement("insert into giren_odemeler values"
                    + "(default,"+Giren_odeme.getKasa().getKasa_id()+","+Giren_odeme.getKimden()
                    +",'"+Giren_odeme.getOdeme_tarihi()+"',"+Giren_odeme.getMiktar()+")"));
           
            this.getPst().executeUpdate();
            
            //control
            System.out.println("Successfull insertion");
            return "Success";
            
        } catch (SQLException ex) {
            //control
            System.out.println(ex.getMessage());
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
    
    public static void main(String[] args) {
        Giren_odemelerInsertion odeme = new Giren_odemelerInsertion();
        
        Giren_odemelerBuilder giren_odeme = new Giren_odemelerBuilder();
        
        KasaSelection kasa = new KasaSelection();
        giren_odeme.SetKasa(kasa.Find(1));
        
        Name isim = new Name();
        isim.setFirst_name("amin");
        isim.setMiddle_name("taiseer");
        isim.setLast_name("alhammouri");
        giren_odeme.SetKimden(isim.toString());
        giren_odeme.SetOdeme_tarihi(Date.valueOf("2020-01-02"));
        giren_odeme.SetMiktar(BigInteger.valueOf(3000));
        
        odeme.insert(giren_odeme.build());
    }
    
}
