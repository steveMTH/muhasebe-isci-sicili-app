
package maaslarCRUD;

import elemanCRUD.ElemanSelection;
import entity.Maaslar;
import entity.MaaslarBuilder;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import kasaCRUD.KasaSelection;
import postgreSQL.ConnectionUtil;

public class MaaslarInsertion {
    
    private Connection connection = null;
    private PreparedStatement pst = null;
    ConnectionUtil connectionUtil = null;
    
    public String insert(Maaslar maas) {

        try {
            this.setPst(this.getConnection().prepareStatement("insert into maaslar values"
                    + "(default,"+maas.getKasa().getKasa_id()+","+maas.getEleman().getEleman_id()
                    +",'"+maas.getOdeme_tarihi()+"',"+maas.getMiktar()+")"));
       
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
        MaaslarInsertion maas = new MaaslarInsertion();
        MaaslarBuilder tmpMaas = new MaaslarBuilder();
        
        KasaSelection kasa = new KasaSelection();
        tmpMaas.SetKasa(kasa.Find(1));
        
        ElemanSelection eleman = new ElemanSelection();
        tmpMaas.SetEleman(eleman.Find(1));
        tmpMaas.SetOdeme_tarihi(Date.valueOf("2020-03-01"));
        tmpMaas.SetMiktar(600);
        
        maas.insert(tmpMaas.build());
    }
}
