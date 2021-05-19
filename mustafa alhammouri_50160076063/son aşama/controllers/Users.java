
package controllers;

import entity.Sozlesmeli;
import entity.SozlesmeliBuilder;
import entity.UsersBuilder;
import java.io.IOException;
import usersCRUD.UserSelection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sozlesmeliCRUD.SozlesmeliSelection;
import type.Name;
import usersCRUD.UserInsertion;

public class Users implements Initializable {
    static String userName;
    static int proje_id;
    static int bolum_id;
    static int eleman_id;

    //Login islemi
    
    UserSelection userSelection = new UserSelection();
    UserInsertion userInsertion = new UserInsertion();
    SozlesmeliSelection sozlesmeli = new SozlesmeliSelection();
    SozlesmeliBuilder tmpSozlesmeliBuilder = new SozlesmeliBuilder();
    Sozlesmeli tmpSozlesmeli = new Sozlesmeli(tmpSozlesmeliBuilder);
    Stage primaryStage = new Stage();
    Muhasip muhasip;
    
    @FXML
    private Pane pane_login;
    
    @FXML
    private Pane pane_createAcount;
    
    @FXML
    private Pane pane_updateAcount;
    
    @FXML
    private TextField textUserName;

    @FXML
    private TextField textPassword;

    @FXML
    private Button btnSignin;
    
    @FXML
    private Button btnUpdateAcount;
    
    @FXML
    private Button btnCreateAcount;

   // @FXML
   // private Label lbl_close;

    @FXML
    private Label lblMessage_login;
    
    @FXML
    private Label lblMessage_update;
    
    @FXML
    private Label lblMessage_create;

    
    @FXML
    private TextField textUserName_create;
    
    @FXML
    private TextField textPassword_create;
    
    @FXML
    private TextField textElemanin_ismi_create;
    
    
    
    
    
    @FXML
    private void btnSigninAction(ActionEvent event) throws SQLException {
       if (event.getSource() == btnSignin) {
            //login here
            userName = textUserName.getText();
            System.out.println("username = "+userName);
            if(userSelection.login(textUserName.getText(), textPassword.getText()).equals("Success")){
                System.out.println(this.SetNameFromTextField(userName).toString());
                if(sozlesmeli.FindFromName(this.SetNameFromTextField(userName).toString()) != null){
                    
                    tmpSozlesmeliBuilder.setSozlesmeli(sozlesmeli.FindFromName(this.SetNameFromTextField(userName).toString()));
                    tmpSozlesmeli = new Sozlesmeli(tmpSozlesmeliBuilder);
                    
                    System.err.println("111111 "+tmpSozlesmeli.getEleman().getEleman_id());
                    if(sozlesmeli.muhasip_id_its_exists(tmpSozlesmeli.getEleman().getEleman_id())){

                        //lblMessage_login.setTextFill(Color.GREEN);
                        //lblMessage_login.setText("Login Successful");

                        try {

                        //new Alert(Alert.AlertType.CONFIRMATION,"Basarli").showAndWait();
                        kapat();
                        Parent root = FXMLLoader.load(getClass().getResource("/muhasipFXML/Muhasip.fxml"));
                        Scene scene = new Scene(root);
                        primaryStage.setScene(scene);
                        primaryStage.show();

                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                    else if(sozlesmeli.mudur_id_its_exists(tmpSozlesmeli.getEleman().getEleman_id())){
                            System.out.println("111111111111111");
                        try {
                            
                            eleman_id = tmpSozlesmeli.getEleman().getEleman_id();
                            if(tmpSozlesmeli.getEleman().getBolum_id().getBolum_id() != 0){
                                bolum_id = tmpSozlesmeli.getEleman().getBolum_id().getBolum_id();
                            }
                            else{
                                proje_id = sozlesmeli.FindProje_id(eleman_id);
                            }
                            
                            kapat();
                            Parent root = FXMLLoader.load(getClass().getResource("/mudurFXML/Mudur.fxml"));
                            Scene scene = new Scene(root);
                            primaryStage.setScene(scene);
                            primaryStage.show();

                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }

                    }
                    else{
                        lblMessage_login.setTextFill(Color.TOMATO);
                        lblMessage_login.setText("aradığınız eleman mudur yada muhasip degil");
                    }
                }
                else{
                    lblMessage_login.setTextFill(Color.TOMATO);
                    lblMessage_login.setText("aradığınız eleman Bulunmamktadır");
                }
                
            }
            else{
                lblMessage_login.setTextFill(Color.TOMATO);
                lblMessage_login.setText("Enter correct Email/Password");
            }
        }
    }
    
    @FXML
    private void btnCreateAction(ActionEvent event) {
        String name;
        Sozlesmeli tmpSozlesmeli;
        name = this.SetNameFromTextField(textElemanin_ismi_create.getText()).toString();
        /*name = "ROW('"+textElemanin_ismi_create.getText().substring(0 , textElemanin_ismi_create.getText().indexOf(" "))+"','"
               + textElemanin_ismi_create.getText().substring(textElemanin_ismi_create.getText().indexOf(" "), textElemanin_ismi_create.getText().lastIndexOf(" "))
                +"','"+textElemanin_ismi_create.getText().substring(textElemanin_ismi_create.getText().lastIndexOf(" ")+1)+"')";*/
        System.err.println("\n\n"+name+"\n\n");
        tmpSozlesmeli = sozlesmeli.FindFromName(name);
        if(tmpSozlesmeli != null){
            UsersBuilder user = new UsersBuilder();
            user.SetSozlesmeli(tmpSozlesmeli);
            user.SetUsername(textUserName_create.getText());
            user.SetPassword(textPassword_create.getText());
           userInsertion.insert(user.build());
           
           lblMessage_create.setTextFill(Color.GREEN);
           lblMessage_create.setText("Created");
        }
        else if(tmpSozlesmeli == null){
           lblMessage_create.setTextFill(Color.TOMATO);
           lblMessage_create.setText("eleman bulunamdi");
        }
    }
    
    
    @FXML
    private void btnUpdatePane(ActionEvent event) {
        this.GosterUpdateAcount(event);
    }
    
    @FXML
    private void btnCreatePane(ActionEvent event) {
        this.GosterCreateAcount(event);
    }
    
    @FXML
    private void btnLoginPane(ActionEvent event) {
        this.GosterLogin(event);
    }
    
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    

    private void showDialog(String info, String header, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(info);
        alert.setHeaderText(header);
        alert.setTitle(title);
        alert.showAndWait();
    }
    
    public void kapat(){
        primaryStage = (Stage) btnSignin.getScene().getWindow();
        primaryStage.close();
    }
    
    
    public void GosterLogin(ActionEvent event){
        this.pane_login.setVisible(true);
        this.pane_createAcount.setVisible(false);
    }
    
    public void GosterCreateAcount(ActionEvent event){
        this.pane_login.setVisible(false);
        this.pane_createAcount.setVisible(true);
    }
    
    public void GosterUpdateAcount(ActionEvent event){
        this.pane_login.setVisible(false);
        this.pane_createAcount.setVisible(false);
        this.pane_updateAcount.setVisible(true);
    }
    
    
    private Name SetNameFromTextField(String name){
        System.out.println(name);
        Name tmpName = new Name();
        
        tmpName.setFirst_name(name.substring(0 , name.indexOf(" ")));
        if( (name.indexOf(" ")+1) > (name.lastIndexOf(" ")) ){
            tmpName.setMiddle_name(" ");
        }
        else{
            tmpName.setMiddle_name(name.substring(name.indexOf(" ")+1, name.lastIndexOf(" ")));
        }
        tmpName.setLast_name(name.substring(name.lastIndexOf(" ")+1));
        return tmpName;
    } 

}
