
package kasaCRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import postgreSQL.ConnectionUtil;

public class butceSelection {
    private int giren_odemeler;
    private int cikan_odemeler;
    private int maaslar;
    
    private Connection connection = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    ConnectionUtil connectionUtil = null;
    
    
    
    public int Giren_odemeler() {

        //quary
        try {
            this.setPst(this.getConnection().prepareStatement("select giren_odemeler from get_giren_odemeler where kasa_id = 1"));
            this.setRs(this.getPst().executeQuery());
            
            this.getRs().next();
            //System.out.println(this.getRs().getInt("giren_odemeler"));
            
            //control
            System.out.println("Successfull selection");
            return this.getRs().getInt("giren_odemeler");

        } catch (SQLException ex) {
            //control
            System.out.println(ex.getMessage());
            System.err.println("Wrong selection ......");
            return 0;
        }

    }
    
    public int Cikan_odemeler() {

        //quary
        try {
            this.setPst(this.getConnection().prepareStatement("select cikan_odemeler from get_cikan_odemeler where kasa_id = 1"));
            this.setRs(this.getPst().executeQuery());
            
            this.getRs().next();
            //System.out.println(this.getRs().getInt("giren_odemeler"));
            
            //control
            System.out.println("Successfull selection");
            return this.getRs().getInt("cikan_odemeler");

        } catch (SQLException ex) {
            //control
            System.out.println(ex.getMessage());
            System.err.println("Wrong selection ......");
            return 0;
        }

    }
    
    
    public int Maaslar() {

        //quary
        try {
            this.setPst(this.getConnection().prepareStatement("select maaşların_toplamı from get_maaslar where kasa_id = 1"));
            this.setRs(this.getPst().executeQuery());
            
            this.getRs().next();
            //System.out.println(this.getRs().getInt("giren_odemeler"));
            
            //control
            System.out.println("Successfull selection");
            return this.getRs().getInt("maaşların_toplamı");

        } catch (SQLException ex) {
            //control
            System.out.println(ex.getMessage());
            System.err.println("Wrong selection ......");
            return 0;
        }

    }
    
    public int getButce(){
        if(Cikan_odemeler() != 0 && Giren_odemeler() != 0 && Maaslar() != 0){
            return Giren_odemeler() - Cikan_odemeler() - Maaslar();
        }
        else{
            return 0;
        }
    }
    
    

    public int getGiren_odemeler() {
        return giren_odemeler;
    }

    public void setGiren_odemeler(int giren_odemeler) {
        this.giren_odemeler = giren_odemeler;
    }

    public int getCikan_odemeler() {
        return cikan_odemeler;
    }

    public void setCikan_odemeler(int cikan_odemeler) {
        this.cikan_odemeler = cikan_odemeler;
    }

    public int getMaaslar() {
        return maaslar;
    }

    public void setMaaslar(int maaslar) {
        this.maaslar = maaslar;
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

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    public ConnectionUtil getConnectionUtil() {
        this.connectionUtil = new ConnectionUtil();
        return connectionUtil;
    }
    
    public static void main(String[] args) {
        butceSelection butce = new butceSelection();
        System.out.println(butce.Giren_odemeler());
        System.out.println(butce.Cikan_odemeler());
        System.out.println(butce.Maaslar());
        
        System.out.println("butemiz = "+butce.getButce());
        
    }
    
    
}
