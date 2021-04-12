
package eleman_projede_calisirCRUD;

import projeCRUD.*;
import elemanCRUD.ElemanSelection;
import entity.eleman_projede_calisir;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import postgreSQL.ConnectionUtil;

public class eleman_projede_calisirInsertion {
    
    private Connection connection = null;
    private PreparedStatement pst = null;
    ConnectionUtil connectionUtil = null;
    
    public String insert(eleman_projede_calisir calisir) {

        try {
            this.setPst(this.getConnection().prepareStatement("insert into eleman_projede_calisir"
                    + " (eleman_id,proje_id) values(?,?)"));
            this.getPst().setInt(1, calisir.getEleman_id().getEleman_id());
            this.getPst().setInt(2, calisir.getProje_id().getProje_id());
            
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
        eleman_projede_calisirInsertion calisir = new eleman_projede_calisirInsertion();
        ProjeSelection proje = new ProjeSelection();
        ElemanSelection eleman = new ElemanSelection();
        
        eleman_projede_calisir tmp = new eleman_projede_calisir();
        tmp.setEleman_id(eleman.Find(6));
        tmp.setProje_id(proje.Find(2));
        
        calisir.insert(tmp);
    }
    
}
