
package sozlesmeliCRUD;

import bolumCRUD.BolumSelection;
import elemanCRUD.ElemanSelection;
import eleman_projede_calisirCRUD.eleman_projede_calisirInsertion;
import entity.ElemanBuilder;
import entity.Sozlesmeli;
import entity.SozlesmeliBuilder;
import entity.eleman_projede_calisir;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import postgreSQL.ConnectionUtil;
import projeCRUD.ProjeSelection;
import type.Address;
import type.Name;

public class SozlesmeliInsertion {
    
    private Connection connection = null;
    private PreparedStatement pst = null;
    ConnectionUtil connectionUtil = null;
    
    eleman_projede_calisirInsertion calisir = new eleman_projede_calisirInsertion();
    
    //eleman proje calisir iliskisi icin kullanilan entity
    eleman_projede_calisir tmpCalisir;
    ElemanSelection eleman = new ElemanSelection();
    
    public String insert(Sozlesmeli sozlesmeli) {

        try {
            this.setPst(this.getConnection().prepareStatement("insert into sozlesmeli values"
                    + "(default,"+sozlesmeli.getEleman().getBolum_id().getBolum_id()+","+sozlesmeli.getEleman().getIsim()
                    +",'"+sozlesmeli.getEleman().getDogum_tarihi()+"',"+sozlesmeli.getEleman().getKonum()
                    +",'"+sozlesmeli.getEleman().getTelefon_no()+"','"+sozlesmeli.getEleman().getCinsiyet()
                    +"','"+sozlesmeli.getBaslangic_tarihi()+"','"+sozlesmeli.getBitis_tarihi()
                    +"','"+sozlesmeli.getUzmanlik()+"')"));
            
            this.getPst().executeUpdate();
            
            
            if(sozlesmeli.getEleman().getProje() != null){
                tmpCalisir = new eleman_projede_calisir();
                System.out.println(eleman.FindName(sozlesmeli.getEleman().getTelefon_no()).getEleman_id());
                tmpCalisir.setEleman_id(eleman.FindName(sozlesmeli.getEleman().getTelefon_no()));
                tmpCalisir.setProje_id(sozlesmeli.getEleman().getProje());
                calisir.insert(tmpCalisir);
            }
            
                /*this.setPst(this.getConnection().prepareStatement("insert into muteahhit "
                        + "(bolum_id,isim,dogum_tarihi,konum,telefon_no,cinsiyet,baslangic_tarihi"
                        + ",bitis_tarihi,uzmanlik) values(?,?,?,?,?,?,?,?,?)"));
                this.getPst().setInt(1, sozlesmeli.getEleman().getBolum_id().getBolum_id());
                this.getPst().setObject(2, sozlesmeli.getEleman().getIsim());
                this.getPst().setDate(3, sozlesmeli.getEleman().getDogum_tarihi());
                this.getPst().setObject(4, sozlesmeli.getEleman().getKonum());
                this.getPst().setString(5, sozlesmeli.getEleman().getTelefon_no());
                this.getPst().setString(6, sozlesmeli.getEleman().getCinsiyet());
                this.getPst().setDate(7, sozlesmeli.getBaslangic_tarihi());
                this.getPst().setDate(8, sozlesmeli.getBitis_tarihi());
                this.getPst().setString(9, sozlesmeli.getUzmanlik());
                this.getPst().executeUpdate();*/
            
            //control
            System.out.println("Successfull insertion");
            return "Success";
            
        } catch (SQLException ex) {
            //control
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
        SozlesmeliInsertion sozlesmeli = new SozlesmeliInsertion();
        ElemanBuilder eleman = new ElemanBuilder();
        
        BolumSelection bolum = new BolumSelection();
        eleman.SetBolum_id(bolum.FindID(1));
        Name isim = new Name();
        isim.setFirst_name("mucahit");
        isim.setMiddle_name(" ");
        isim.setLast_name("ahmetoglu");
        eleman.SetIsim(isim.toString());
        eleman.SetDogum_tarihi(Date.valueOf("1985-01-01"));
        Address konum = new Address();
        konum.setCountry("irak");
        konum.setCity("tekrit");
        konum.setStreet("dream");
        konum.setAddress("irak erbil dream city");
        eleman.SetKonum(konum.toString());
        eleman.SetTelefon_no("235542543");
        eleman.SetCinsiyet("M");
        
        ProjeSelection proje = new ProjeSelection();
        eleman.SetProje(proje.Find(2));
        
        SozlesmeliBuilder tmpSozlesmeli = new SozlesmeliBuilder();
        tmpSozlesmeli.SetEleman(eleman.build());
        tmpSozlesmeli.SetBaslangic_tarihi(Date.valueOf("2015-01-01"));
        tmpSozlesmeli.SetBitis_tarihi(Date.valueOf("2015-01-01"));
        tmpSozlesmeli.SetUzmanlik("insan kaynakları mudurr");
        
        
        sozlesmeli.insert(tmpSozlesmeli.build());
    }
    
    
}
