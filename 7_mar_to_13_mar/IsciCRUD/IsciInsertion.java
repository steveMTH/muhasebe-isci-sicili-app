
package IsciCRUD;

import bolumCRUD.BolumSelection;
import elemanCRUD.ElemanSelection;
import eleman_projede_calisirCRUD.eleman_projede_calisirInsertion;
import entity.ElemanBuilder;
import entity.Isci;
import entity.IsciBuilder;
import entity.eleman_projede_calisir;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import postgreSQL.ConnectionUtil;
import projeCRUD.ProjeSelection;
import type.Address;
import type.Name;

public class IsciInsertion {
    
    private Connection connection = null;
    private PreparedStatement pst = null;
    ConnectionUtil connectionUtil = null;
    
    eleman_projede_calisirInsertion calisir = new eleman_projede_calisirInsertion();
    
    //eleman proje calisir iliskisi icin kullanilan entity
    eleman_projede_calisir tmpCalisir;
    ElemanSelection eleman = new ElemanSelection();
    
    public String insert(Isci isci) {
        
        try {
            this.setPst(this.getConnection().prepareStatement("insert into isci values"
                    + "(default,"+isci.getEleman().getBolum_id().getBolum_id()+","+isci.getEleman().getIsim()
                    +",'"+isci.getEleman().getDogum_tarihi()+"',"+isci.getEleman().getKonum()
                    +",'"+isci.getEleman().getTelefon_no()+"','"+isci.getEleman().getCinsiyet()
                    +"','"+isci.getMeslek()+"')"));
            
            this.getPst().executeUpdate();
            if(isci.getEleman().getProje() != null){
                tmpCalisir = new eleman_projede_calisir();
                System.out.println(eleman.FindName(isci.getEleman().getTelefon_no()).getEleman_id());
                tmpCalisir.setEleman_id(eleman.FindName(isci.getEleman().getTelefon_no()));
                tmpCalisir.setProje_id(isci.getEleman().getProje());
                calisir.insert(tmpCalisir);
            }
            
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
        IsciInsertion isci = new IsciInsertion();
        ElemanBuilder eleman = new ElemanBuilder();
        
        BolumSelection bolum = new BolumSelection();
        eleman.SetBolum_id(bolum.FindID(1));
        Name isim = new Name();
        isim.setFirst_name("halil");
        isim.setMiddle_name(" ");
        isim.setLast_name("yilmaz");
        eleman.SetIsim(isim.toString());
        eleman.SetDogum_tarihi(Date.valueOf("1990-01-01"));
        Address konum = new Address();
        konum.setCountry("irak");
        konum.setCity("erbil");
        konum.setStreet("dream");
        konum.setAddress("irak erbil dream city");
        eleman.SetKonum(konum.toString());
        eleman.SetTelefon_no("435765324");
        eleman.SetCinsiyet("M");
        
        ProjeSelection proje = new ProjeSelection();
        eleman.SetProje(proje.Find(2));
        
        IsciBuilder tmpIsci = new IsciBuilder();
        tmpIsci.SetEleman(eleman.build());
        tmpIsci.SetMeslek("temizlikci");
        
        
        isci.insert(tmpIsci.build());
    }
    
}
