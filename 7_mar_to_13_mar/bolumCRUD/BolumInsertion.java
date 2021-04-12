
package bolumCRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import postgreSQL.ConnectionUtil;
import type.Address;

public class BolumInsertion {
    
    private Connection connection = null;
    private PreparedStatement pst = null;
    ConnectionUtil connectionUtil = null;
    
    public String insert(String isim,Address konum) {

        try {
            this.setPst(this.getConnection().prepareStatement("insert into bolum values(default,'"+isim+"',ROW("+
                    "'"+konum.getCountry()+"','"+konum.getCity()+"','"+konum.getStreet()+"','"+konum.getAddress()+"')"));
            //this.getPst().setString(1, bolum.getIsim());
            //this.getPst().setObject(2, bolum.getKonum());
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
        BolumInsertion bolum = new BolumInsertion();
        Address konum = new Address();
        konum.setCountry("irak");
        konum.setCity("erbil");
        konum.setStreet("dream");
        konum.setAddress("irak erbil dream city");
        bolum.insert("vision",konum);
    }
}
