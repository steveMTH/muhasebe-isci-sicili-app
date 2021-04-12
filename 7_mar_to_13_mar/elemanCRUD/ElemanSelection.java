
package elemanCRUD;

import bolumCRUD.BolumSelection;
import entity.Eleman;
import entity.ElemanBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import postgreSQL.ConnectionUtil;

public class ElemanSelection {
    private Connection connection = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    ConnectionUtil connectionUtil = null;
    
    BolumSelection bolum = new BolumSelection();
    ElemanBuilder eleman;
    
    
    
    public List<Eleman> getEleman(){
        List<Eleman> elemanList = new ArrayList<>();
        
        try {
            this.setPst(this.getConnection().prepareStatement("select * from eleman"));
            this.setRs(this.getPst().executeQuery());
            
            while(rs.next()){
                eleman = new ElemanBuilder();
                eleman.SetEleman_id(rs.getInt("eleman_id"));
                eleman.SetBolum_id(bolum.FindID(rs.getInt("bolum_id")));
                eleman.SetIsim(rs.getString("isim"));
                eleman.SetDogum_tarihi(rs.getDate("dogum_tarihi"));
                eleman.SetCinsiyet(rs.getString("cinsiyet"));
                eleman.SetTelefon_no(rs.getString("telefon_no"));
                eleman.SetKonum(rs.getString("konum"));
                elemanList.add(eleman.build());
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return elemanList;
    }
    
    
    
    public Eleman Find(int eleman_id) {

        //quary
        try {
            this.setPst(this.getConnection().prepareStatement("select * from eleman where eleman_id=?"));
            this.getPst().setInt(1, eleman_id);
            this.setRs(this.getPst().executeQuery());
            
            rs.next();
            eleman = new ElemanBuilder();
            eleman.SetEleman_id(rs.getInt("eleman_id"));
            eleman.SetBolum_id(bolum.FindID(rs.getInt("bolum_id")));
            eleman.SetIsim(rs.getString("isim"));
            eleman.SetDogum_tarihi(rs.getDate("dogum_tarihi"));
            eleman.SetKonum(rs.getString("konum"));
            eleman.SetTelefon_no(rs.getString("telefon_no"));
            eleman.SetCinsiyet(rs.getString("cinsiyet"));
            
            System.out.println(eleman.toString());
            
            //control
            System.out.println();
            System.out.println("Successfull serching");
            return eleman.build();

        } catch (SQLException ex) {
            //control
            System.err.println("Wrong serching ......");
            return null;
        }

    }
    
    public Eleman FindName(String telefon_no) {

        //quary
        try {
            this.setPst(this.getConnection().prepareStatement("select * from eleman where telefon_no=?"));
            this.getPst().setString(1, telefon_no);
            this.setRs(this.getPst().executeQuery());
            
            rs.next();
            eleman = new ElemanBuilder();
            eleman.SetEleman_id(rs.getInt("eleman_id"));
            eleman.SetBolum_id(bolum.FindID(rs.getInt("bolum_id")));
            eleman.SetIsim(rs.getString("isim"));
            eleman.SetDogum_tarihi(rs.getDate("dogum_tarihi"));
            eleman.SetKonum(rs.getString("konum"));
            eleman.SetTelefon_no(rs.getString("telefon_no"));
            eleman.SetCinsiyet(rs.getString("cinsiyet"));
            
            System.out.println(eleman.toString());
            
            //control
            System.out.println();
            System.out.println("Successfull serching");
            return eleman.build();

        } catch (SQLException ex) {
            //control
            System.err.println("Wrong serching ......");
            return null;
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

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }
    
    
    public static void main(String[] args) {
        ElemanSelection eleman = new ElemanSelection();
        System.out.println(eleman.getEleman());
    }
}
