
package cikan_odemelerCRUD;

import entity.Cikan_odemeler;
import entity.Cikan_odemelerBuilder;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import kasaCRUD.KasaSelection;
import postgreSQL.ConnectionUtil;
import type.Name;

public class Cikan_odemelerUpdate {
    private Connection connection = null;
    private PreparedStatement pst = null;
    ConnectionUtil connectionUtil = null;
    
    
    public void update(Cikan_odemeler odeme) {

        try {
            this.setPst(this.getConnection().prepareStatement("update cikan_odemeler set kasa_id="
                    + odeme.getKasa().getKasa_id()+",kimden="+odeme.getKimden()+",kime="+odeme.getKime()
                    + ",odeme_tarihi='"+odeme.getOdeme_tarihi()+"',miktar="+odeme.getMiktar()
                    +" where odeme_id="+odeme.getOdeme_id()));
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
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
        Cikan_odemelerUpdate odeme = new Cikan_odemelerUpdate();

        Cikan_odemelerBuilder cikan_odeme = new Cikan_odemelerBuilder();

        cikan_odeme.setOdeme_id(2);
        KasaSelection kasa = new KasaSelection();
        cikan_odeme.SetKasa(kasa.Find(1));

        Name kimden = new Name();
        kimden.setFirst_name("amin");
        kimden.setMiddle_name("taiseer");
        kimden.setLast_name("alhammouri");
        cikan_odeme.SetKimden(kimden.toString());

        Name kime = new Name();
        kime.setFirst_name("sait");
        kime.setMiddle_name("ahet");
        kime.setLast_name("aluan");
        cikan_odeme.SetKime(kime.toString());
        cikan_odeme.SetOdeme_tarihi(Date.valueOf("2020-02-02"));

        cikan_odeme.SetMiktar(BigInteger.valueOf(999));

        odeme.update(cikan_odeme.build());
    }
    
}
