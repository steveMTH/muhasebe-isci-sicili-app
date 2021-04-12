
package maaslarCRUD;

import elemanCRUD.ElemanSelection;
import entity.Maaslar;
import entity.MaaslarBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import kasaCRUD.KasaSelection;
import postgreSQL.ConnectionUtil;

public class MaaslarSelection {
    
    private Connection connection = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    ConnectionUtil connectionUtil = null;
    
    MaaslarBuilder maas = null;
    KasaSelection kasa = new KasaSelection();
    ElemanSelection eleman = new ElemanSelection();
    
    
    public Maaslar Find(int maas_id) {

        //quary
        try {
            this.setPst(this.getConnection().prepareStatement("select * from maaslar where maas_id=?"));
            this.getPst().setInt(1, maas_id);
            this.setRs(this.getPst().executeQuery());
            
            this.getRs().next();
            maas = new MaaslarBuilder();
            maas.setMaas_id(rs.getInt("maas_id"));
            maas.SetKasa(kasa.Find(rs.getInt("kasa_id")));
            maas.SetEleman(eleman.Find(rs.getInt("eleman_id")));
            maas.SetOdeme_tarihi(rs.getDate("odeme_tarihi"));
            maas.SetMiktar(rs.getInt("miktar"));
            
            
            //control
            System.out.println();
            System.out.println("Successfull serching");
            return maas.build();

        } catch (SQLException ex) {
            //control
            System.err.println("Wrong serching ......");
            return null;
        }

    }
    
    
    
    public List<Maaslar> getMaasler(){
        List<Maaslar> maasList = new ArrayList<>();
        
        try {
            this.setPst(this.getConnection().prepareStatement("select * from maaslar"));
            this.setRs(this.getPst().executeQuery());
            
            while(rs.next()){
                maas = new MaaslarBuilder();
                maas.setMaas_id(rs.getInt("maas_id"));
                maas.SetKasa(kasa.Find(rs.getInt("kasa_id")));
                maas.SetEleman(eleman.Find(rs.getInt("eleman_id")));
                maas.SetOdeme_tarihi(rs.getDate("odeme_tarihi"));
                maas.SetMiktar(rs.getInt("miktar"));
            
                maasList.add(maas.build());
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return maasList;
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
        MaaslarSelection maas = new MaaslarSelection();
        
        System.out.println(maas.getMaasler());
    }
    
   
}
