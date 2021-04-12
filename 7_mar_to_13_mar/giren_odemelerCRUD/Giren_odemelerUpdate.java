
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

public class Giren_odemelerUpdate {
    private Connection connection = null;
    private PreparedStatement pst = null;
    ConnectionUtil connectionUtil = null;
    
    
    public void update(Giren_odemeler giren_odeme) {

        try {
            this.setPst(this.getConnection().prepareStatement("update giren_odemeler set kasa_id="
                    +giren_odeme.getKasa().getKasa_id()+ ",kimden="+giren_odeme.getKimden()
                    +",odeme_tarihi='"+giren_odeme.getOdeme_tarihi()+"',miktar="+giren_odeme.getMiktar()
                    +" where odeme_id="+giren_odeme.getOdeme_id()));
         
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.err.println("woring update ......");
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
        Giren_odemelerUpdate odeme = new Giren_odemelerUpdate();
        
        Giren_odemelerBuilder giren_odeme = new Giren_odemelerBuilder();
        
        giren_odeme.setOdeme_id(1);
        KasaSelection kasa = new KasaSelection();
        giren_odeme.SetKasa(kasa.Find(1));
        
        Name isim = new Name();
        isim.setFirst_name("amin");
        isim.setMiddle_name("taiseer");
        isim.setLast_name("alhammouri");
        giren_odeme.SetKimden(isim.toString());
        giren_odeme.SetOdeme_tarihi(Date.valueOf("2020-02-02"));
        giren_odeme.SetMiktar(BigInteger.valueOf(999));
        
        odeme.update(giren_odeme.build());
    }
    
}
