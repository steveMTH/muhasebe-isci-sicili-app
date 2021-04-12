
package muteahhitCRUD;

import bolumCRUD.BolumSelection;
import elemanCRUD.ElemanSelection;
import entity.ElemanBuilder;
import entity.Muteahhit;
import entity.MuteahhitBuilder;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import postgreSQL.ConnectionUtil;
import type.Address;
import type.Name;

public class MuteahhitUpdate {
    private Connection connection = null;
    private PreparedStatement pst = null;
    ConnectionUtil connectionUtil = null;
    
    
    
    
    public void update(Muteahhit muteahhit) {

        try {
            this.setPst(this.getConnection().prepareStatement("update muteahhit set bolum_id="
                    +muteahhit.getEleman().getBolum_id().getBolum_id()+",isim="+muteahhit.getEleman().getIsim()
                    +",dogum_tarihi='"+muteahhit.getEleman().getDogum_tarihi()+"',konum="+muteahhit.getEleman().getKonum()
                    +",telefon_no='"+muteahhit.getEleman().getTelefon_no()+"',cinsiyet='"+muteahhit.getEleman().getCinsiyet()
                    +"',is_alani='"+muteahhit.getIs_alani()+"' where eleman_id="+muteahhit.getEleman().getEleman_id()));
            
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
        MuteahhitUpdate muteahit = new MuteahhitUpdate();
        ElemanBuilder eleman = new ElemanBuilder();
        ElemanSelection findEleman = new ElemanSelection();
        
        eleman.SetEleman_id(findEleman.Find(3).getEleman_id());
        BolumSelection bolum = new BolumSelection();
        eleman.SetBolum_id(bolum.FindID(1));
        Name isim = new Name();
        isim.setFirst_name("sait");
        isim.setMiddle_name(" ");
        isim.setLast_name("halwani");
        eleman.SetIsim(isim.toString());
        eleman.SetDogum_tarihi(Date.valueOf("1995-01-01"));
        Address konum = new Address();
        konum.setCountry("irak");
        konum.setCity("tekrit");
        konum.setStreet("dream");
        konum.setAddress("irak tekrit dream city");
        eleman.SetKonum(konum.toString());
        eleman.SetTelefon_no("098765432111");
        eleman.SetCinsiyet("M");
        
        MuteahhitBuilder tmpMuteahhit = new MuteahhitBuilder();
        tmpMuteahhit.SetEleman(eleman.build());
        tmpMuteahhit.SetIs_alani("demirci");
        
        
        muteahit.update(tmpMuteahhit.build());
    }
    
}
