
package maaslarCRUD;

import cikan_odemelerCRUD.*;
import elemanCRUD.ElemanSelection;
import entity.Cikan_odemeler;
import entity.Maaslar;
import entity.MaaslarBuilder;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import kasaCRUD.KasaSelection;
import postgreSQL.ConnectionUtil;

public class MaaslarUpdate {
    private Connection connection = null;
    private PreparedStatement pst = null;
    ConnectionUtil connectionUtil = null;
    
    
    public void update(Maaslar maas) {

        try {
            this.setPst(this.getConnection().prepareStatement("update maaslar set kasa_id="+maas.getKasa().getKasa_id()
                    + ",eleman_id="+maas.getEleman().getEleman_id()+",odeme_tarihi='"+maas.getOdeme_tarihi()
                    +"',miktar="+maas.getMiktar()+" where maas_id="+maas.getMaas_id()));
            
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
        MaaslarUpdate maas = new MaaslarUpdate();
        MaaslarBuilder tmpMaas = new MaaslarBuilder();
        
        tmpMaas.setMaas_id(1);
        KasaSelection kasa = new KasaSelection();
        tmpMaas.SetKasa(kasa.Find(1));
        
        ElemanSelection eleman = new ElemanSelection();
        tmpMaas.SetEleman(eleman.Find(1));
        tmpMaas.SetOdeme_tarihi(Date.valueOf("2020-03-01"));
        tmpMaas.SetMiktar(560);
        
        maas.update(tmpMaas.build());
    }
    
}
