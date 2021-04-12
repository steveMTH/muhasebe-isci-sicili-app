
package projeCRUD;

import bolumCRUD.BolumSelection;
import entity.Proje;
import entity.ProjeBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import postgreSQL.ConnectionUtil;
import type.Address;

public class ProjeInsertion {
    
    private Connection connection = null;
    private PreparedStatement pst = null;
    ConnectionUtil connectionUtil = null;
    
    public String insert(Proje proje,Address konum) {

        try {
            this.setPst(this.getConnection().prepareStatement("insert into proje values(default,"+proje.getBolum().getBolum_id()+
                    ",'"+proje.getIsim()+"',ROW('"+konum.getCountry()+"','"+konum.getCity()+
                    "','"+konum.getStreet()+"','"+konum.getAddress()+"'))"));
            //this.getPst().setInt(1, proje.getBolum().getBolum_id());
            //this.getPst().setString(2, proje.getIsim());
            //this.getPst().setObject(3, proje.getKonum());
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
        ProjeBuilder proje = new ProjeBuilder();
        BolumSelection bolum = new BolumSelection();
        
        proje.SetBolum(bolum.FindID(1));
        //System.out.println(bolum.FindID(1));
        proje.SetIsim("tekrit");
        
        Address konum = new Address();
        konum.setCountry("irak");
        konum.setCity("tekrit");
        konum.setStreet("");
        konum.setAddress("irak tekrit");
        
        
        ProjeInsertion insert = new ProjeInsertion();
        insert.insert(proje.build(),konum);
        
    }
    
}
