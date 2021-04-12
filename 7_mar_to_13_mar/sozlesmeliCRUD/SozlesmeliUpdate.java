
package sozlesmeliCRUD;

import bolumCRUD.BolumSelection;
import entity.ElemanBuilder;
import entity.Sozlesmeli;
import entity.SozlesmeliBuilder;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import postgreSQL.ConnectionUtil;
import type.Address;
import type.Name;

public class SozlesmeliUpdate {
    private Connection connection = null;
    private PreparedStatement pst = null;
    ConnectionUtil connectionUtil = null;
    
    
    
    
    public void update(Sozlesmeli sozlesmeli) {

        try {
            this.setPst(this.getConnection().prepareStatement("update sozlesmeli set bolum_id="
                    +sozlesmeli.getEleman().getBolum_id().getBolum_id()+",isim="+sozlesmeli.getEleman().getIsim()
                    +",dogum_tarihi='"+sozlesmeli.getEleman().getDogum_tarihi()+"',konum="+sozlesmeli.getEleman().getKonum()
                    +",telefon_no='"+sozlesmeli.getEleman().getTelefon_no()+"',cinsiyet='"+sozlesmeli.getEleman().getCinsiyet()
                    +"',baslangic_tarihi='"+sozlesmeli.getBaslangic_tarihi()+"',bitis_tarihi='"+sozlesmeli.getBitis_tarihi()
                    +"',uzmanlik='"+sozlesmeli.getUzmanlik()+"' where eleman_id="+sozlesmeli.getEleman().getEleman_id()));
            
            pst.executeUpdate();
            
            /*this.setPst(this.getConnection().prepareStatement("update sozlesmeli set bolum_id=?,isim=?"
                    + ",dogum_tarihi=?,konum=?,telefon_no=?,cinsiyet=?,baslangic_tarihi=?,bitis_tarihi=?"
                    + ",uzmanlik=? where eleman_id=?"));
            this.getPst().setInt(1, sozlesmeli.getEleman().getBolum_id().getBolum_id());
            this.getPst().setObject(2, sozlesmeli.getEleman().getIsim());
            this.getPst().setDate(3, sozlesmeli.getEleman().getDogum_tarihi());
            this.getPst().setObject(4, sozlesmeli.getEleman().getKonum());
            this.getPst().setString(5, sozlesmeli.getEleman().getTelefon_no());
            this.getPst().setString(6, sozlesmeli.getEleman().getCinsiyet());
            this.getPst().setDate(7, sozlesmeli.getBaslangic_tarihi());
            this.getPst().setDate(8, sozlesmeli.getBitis_tarihi());
            this.getPst().setString(9, sozlesmeli.getUzmanlik());
            this.getPst().setInt(10, sozlesmeli.getEleman().getEleman_id());
            
            pst.executeUpdate();*/
            System.out.println("Successfull update");
        } catch (SQLException ex) {
            System.err.println("woring update .....");
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
        SozlesmeliUpdate sozlesmeli = new SozlesmeliUpdate();
        ElemanBuilder eleman = new ElemanBuilder();
        
        eleman.SetEleman_id(4);
        BolumSelection bolum = new BolumSelection();
        eleman.SetBolum_id(bolum.FindID(1));
        Name isim = new Name();
        isim.setFirst_name("ahmet");
        isim.setMiddle_name(" ");
        isim.setLast_name("alali");
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
        
        SozlesmeliBuilder tmpSozlesmeli = new SozlesmeliBuilder();
        tmpSozlesmeli.SetEleman(eleman.build());
        tmpSozlesmeli.SetBaslangic_tarihi(Date.valueOf("2015-01-01"));
        tmpSozlesmeli.SetBitis_tarihi(Date.valueOf("2015-01-01"));
        tmpSozlesmeli.SetUzmanlik("muhendis");
        
        
        sozlesmeli.update(tmpSozlesmeli.build());
    }
    
    
}
