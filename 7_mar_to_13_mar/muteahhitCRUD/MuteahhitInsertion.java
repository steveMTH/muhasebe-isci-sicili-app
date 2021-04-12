
package muteahhitCRUD;

import bolumCRUD.BolumSelection;
import elemanCRUD.ElemanSelection;
import eleman_projede_calisirCRUD.eleman_projede_calisirInsertion;
import entity.ElemanBuilder;
import entity.Muteahhit;
import entity.MuteahhitBuilder;
import entity.eleman_projede_calisir;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import postgreSQL.ConnectionUtil;
import projeCRUD.ProjeSelection;
import type.Address;
import type.Name;

public class MuteahhitInsertion {
    
    private Connection connection = null;
    private PreparedStatement pst = null;
    ConnectionUtil connectionUtil = null;
    
    eleman_projede_calisirInsertion calisir = new eleman_projede_calisirInsertion();
    
    //eleman proje calisir iliskisi icin kullanilan entity
    eleman_projede_calisir tmpCalisir;
    ElemanSelection eleman = new ElemanSelection();
    
    public String insert(Muteahhit muteahhit) {

        try {
            this.setPst(this.getConnection().prepareStatement("insert into muteahhit values"
                    + "(default,"+muteahhit.getEleman().getBolum_id().getBolum_id()+","+muteahhit.getEleman().getIsim()
                    +",'"+muteahhit.getEleman().getDogum_tarihi()+"',"+muteahhit.getEleman().getKonum()
                    +",'"+muteahhit.getEleman().getTelefon_no()+"','"+muteahhit.getEleman().getCinsiyet()
                    +"','"+muteahhit.getIs_alani()+"')"));
            
            this.getPst().executeUpdate();
            if(muteahhit.getEleman().getProje() != null){
                tmpCalisir = new eleman_projede_calisir();
                System.out.println(eleman.FindName(muteahhit.getEleman().getTelefon_no()).getEleman_id());
                tmpCalisir.setEleman_id(eleman.FindName(muteahhit.getEleman().getTelefon_no()));
                tmpCalisir.setProje_id(muteahhit.getEleman().getProje());
                calisir.insert(tmpCalisir);
            }
            
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
        MuteahhitInsertion muteahit = new MuteahhitInsertion();
        ElemanBuilder eleman = new ElemanBuilder();
        
        BolumSelection bolum = new BolumSelection();
        eleman.SetBolum_id(bolum.FindID(1));
        Name isim = new Name();
        isim.setFirst_name("abdullah");
        isim.setMiddle_name(" ");
        isim.setLast_name("alioglu");
        eleman.SetIsim(isim.toString());
        eleman.SetDogum_tarihi(Date.valueOf("1980-01-01"));
        Address konum = new Address();
        konum.setCountry("irak");
        konum.setCity("erbil");
        konum.setStreet("dream");
        konum.setAddress("irak erbil dream city");
        eleman.SetKonum(konum.toString());
        eleman.SetTelefon_no("23456576756");
        eleman.SetCinsiyet("M");
        
        ProjeSelection proje = new ProjeSelection();
        eleman.SetProje(proje.Find(4));
        
        MuteahhitBuilder tmpmuteahhit = new MuteahhitBuilder();
        tmpmuteahhit.SetEleman(eleman.build());
        tmpmuteahhit.SetIs_alani("havalandirma sistemleri");
        
        
        muteahit.insert(tmpmuteahhit.build());
    }
}
