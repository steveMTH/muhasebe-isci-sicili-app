
package bolumCRUD;

import entity.Bolum;
import entity.BolumBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import postgreSQL.ConnectionUtil;
import type.Address;

public class BolumUpdate {
    private Connection connection = null;
    private PreparedStatement pst = null;
    ConnectionUtil connectionUtil = null;
    
    
    public void updateIsim(String newName,String oldName) {

        try {
            this.setPst(this.getConnection().prepareStatement("update bolum set isim='"+newName+"' where isim='"+oldName+"'"));
            //this.getPst().setString(1, bolum.getIsim());
            //this.getPst().setString(2, bolum.getIsim());
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    
    public void updateAddress(String isim,Address konum) {

        try {
            this.setPst(this.getConnection().prepareStatement("update bolum set konum="+konum+" where isim='"+isim+"'"));
            //this.getPst().setString(1, konum.toString());
            //this.getPst().setString(2, isim);
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
        BolumUpdate update = new BolumUpdate();
        Address konum = new Address();
        konum.setCountry("irak");
        
        update.updateAddress("vision",konum);
    }
}
