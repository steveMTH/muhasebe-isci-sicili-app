
package IsciCRUD;

import bolumCRUD.BolumSelection;
import elemanCRUD.ElemanSelection;
import entity.ElemanBuilder;
import entity.Isci;
import entity.IsciBuilder;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import postgreSQL.ConnectionUtil;
import type.Address;
import type.Name;

public class IsciUpdate {
    private Connection connection = null;
    private PreparedStatement pst = null;
    ConnectionUtil connectionUtil = null;
    
    
    
    
    public void update(Isci isci) {

        try {
            this.setPst(this.getConnection().prepareStatement("update isci set bolum_id="
                    +isci.getEleman().getBolum_id().getBolum_id()+",isim="+isci.getEleman().getIsim()
                    +",dogum_tarihi='"+isci.getEleman().getDogum_tarihi()+"',konum="+isci.getEleman().getKonum()
                    +",telefon_no='"+isci.getEleman().getTelefon_no()+"',cinsiyet='"+isci.getEleman().getCinsiyet()
                    +"',meslek='"+isci.getMeslek()+"' where eleman_id="+isci.getEleman().getEleman_id()));
            
            pst.executeUpdate();
            System.out.println("Successfull update");
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
        IsciUpdate isci = new IsciUpdate();
        ElemanBuilder eleman = new ElemanBuilder();
        ElemanSelection findEleman = new ElemanSelection();
        
        eleman.SetEleman_id(findEleman.Find(2).getEleman_id());
        BolumSelection bolum = new BolumSelection();
        eleman.SetBolum_id(bolum.FindID(1));
        Name isim = new Name();
        isim.setFirst_name("ali");
        isim.setMiddle_name(" ");
        isim.setLast_name("ahmet");
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
        
        IsciBuilder tmpIsci = new IsciBuilder();
        tmpIsci.SetEleman(eleman.build());
        tmpIsci.SetMeslek("temizlikci");
        
        
        isci.update(tmpIsci.build());
    }
    
}
