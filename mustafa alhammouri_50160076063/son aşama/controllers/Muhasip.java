
package controllers;

import cikan_odemelerCRUD.Cikan_odemelerInsertion;
import cikan_odemelerCRUD.Cikan_odemelerRemove;
import cikan_odemelerCRUD.Cikan_odemelerSelection;
import static controllers.Users.userName;
import elemanCRUD.ElemanSelection;
import entity.Cikan_odemeler;
import entity.Cikan_odemelerBuilder;
import entity.Eleman;
import entity.Giren_odemeler;
import entity.Giren_odemelerBuilder;
import entity.Maaslar;
import entity.MaaslarBuilder;
import giren_odemelerCRUD.Giren_odemelerInsertion;
import giren_odemelerCRUD.Giren_odemelerRemove;
import giren_odemelerCRUD.Giren_odemelerSelection;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import kasaCRUD.KasaSelection;
import kasaCRUD.butceSelection;
import maaslarCRUD.MaaslarInsertion;
import maaslarCRUD.MaaslarRemove;
import maaslarCRUD.MaaslarSelection;
import type.Name;
import usersCRUD.UserSelection;

public class Muhasip implements Initializable {
    private int kasa_id;
    Stage primaryStage = new Stage();
    UserSelection userSelection = new UserSelection();
    Cikan_odemelerBuilder cikanOdeme;
    Giren_odemelerBuilder girenOdme;
    MaaslarBuilder maas;
    KasaSelection kasa = new KasaSelection();
    butceSelection butce = new butceSelection();
    ElemanSelection eleman = new ElemanSelection();
    
    @FXML
    public Button cikis;
    
    
    @FXML
    private TableView<Cikan_odemeler> table_CikanOdemeler;
    @FXML
    private TableColumn<Cikan_odemeler , Integer> col_OdemeId;
    @FXML
    private TableColumn<Cikan_odemeler , String> col_Kimden;
    @FXML
    private TableColumn<Cikan_odemeler , String> col_Kime;
    @FXML
    private TableColumn<Cikan_odemeler , Date> col_odemeTarihi;
    @FXML
    private TableColumn<Cikan_odemeler , BigInteger> col_miktar;
    
    
    
    ObservableList<Cikan_odemeler> cikanOdemelerList;
    
    Cikan_odemelerSelection cikanOdemeler = new Cikan_odemelerSelection();
    
    public void getCikanOdemeler(){
        System.err.println("11111,,,,");
        System.out.println(cikanOdemeler.getCikan_odeme(userSelection.FindKasaIdFromUsers(userName)));
        cikanOdemelerList = FXCollections.observableArrayList(
                cikanOdemeler.getCikan_odeme(userSelection.FindKasaIdFromUsers(userName)));
        System.err.println("11111,,,,");
        
        col_OdemeId.setCellValueFactory(new PropertyValueFactory<>("odeme_id"));
        col_Kimden.setCellValueFactory(new PropertyValueFactory<>("kimden"));
        col_Kime.setCellValueFactory(new PropertyValueFactory<>("kime"));
        col_odemeTarihi.setCellValueFactory(new PropertyValueFactory<>("odeme_tarihi"));
        col_miktar.setCellValueFactory(new PropertyValueFactory<>("miktar"));
        
        table_CikanOdemeler.setItems(cikanOdemelerList);
        System.err.println("11111,,,,");
    }
    
    @FXML
    private TableView<Giren_odemeler> table_GirenOdemeler;
    @FXML
    private TableColumn<Giren_odemeler , Integer> col_cikanOdemeler_OdemeId;
    @FXML
    private TableColumn<Giren_odemeler , String> col_cikanOdemeler_Kimden;
    @FXML
    private TableColumn<Giren_odemeler , Date> col_cikanOdemeler_odemeTarihi;
    @FXML
    private TableColumn<Giren_odemeler , BigInteger> col_cikanOdemeler_miktar;
    
    Giren_odemelerSelection girenOdemeler = new Giren_odemelerSelection();
    ObservableList<Giren_odemeler> girenOdemelerList;
    
    public void getGirenOdemeler(){
        System.err.println("22222,,,,");
        girenOdemelerList = FXCollections.observableArrayList(girenOdemeler.getOdeme(
                                                userSelection.FindKasaIdFromUsers(
                                                                userName)));
        
        col_cikanOdemeler_OdemeId.setCellValueFactory(new PropertyValueFactory<>("odeme_id"));
        col_cikanOdemeler_Kimden.setCellValueFactory(new PropertyValueFactory<>("kimden"));
        col_cikanOdemeler_odemeTarihi.setCellValueFactory(new PropertyValueFactory<>("odeme_tarihi"));
        col_cikanOdemeler_miktar.setCellValueFactory(new PropertyValueFactory<>("miktar"));
        
        table_GirenOdemeler.setItems(girenOdemelerList);
    }

    @FXML
    private TableView<Maaslar> table_Maaslar;
    @FXML
    private TableColumn<Maaslar , Integer> col_Maaslar_odemeId;
    @FXML
    private TableColumn<Maaslar , Integer> col_elemanId;
    @FXML
    private TableColumn<Maaslar , String> col_eleman_ismi;
    @FXML
    private TableColumn<Maaslar , Date> col_Maaslar_odemeTarihi;
    @FXML
    private TableColumn<Maaslar , BigInteger> col_Maaslar_miktar;
    
    MaaslarSelection maaslar = new MaaslarSelection();
    ObservableList<Maaslar> maaslarList;
    
    public void getMaaslar(){
        System.err.println("333333,,,,");
        maaslarList = FXCollections.observableArrayList(maaslar.getMaasler(
                                                            userSelection.FindKasaIdFromUsers(
                                                                userName)));
        
        col_Maaslar_odemeId.setCellValueFactory(new PropertyValueFactory<>("maas_id"));
        col_elemanId.setCellValueFactory(new PropertyValueFactory<>("eleman_id"));
        col_eleman_ismi.setCellValueFactory(new PropertyValueFactory<>("eleman_ismi"));
        col_Maaslar_odemeTarihi.setCellValueFactory(new PropertyValueFactory<>("odeme_tarihi"));
        col_Maaslar_miktar.setCellValueFactory(new PropertyValueFactory<>("miktar"));
        
        table_Maaslar.setItems(maaslarList);
    }
    
    @FXML
    private TextField textKimdenCikanOdeme;
    
    @FXML
    private TextField textKimeCikanOdeme;
    
    @FXML
    private TextField textOdemeTarihiCikanOdeme;
    
    @FXML
    private TextField textMiktarCikanOdeme;
    
    
    Cikan_odemelerRemove cikanOdemeRemove = new Cikan_odemelerRemove();
    @FXML
    public void btnDeleteCikanOdemeItem(ActionEvent event){
        cikanOdeme = new Cikan_odemelerBuilder();
        
        cikanOdeme.SetKasa(kasa.Find(userSelection.FindKasaIdFromUsers(userName)));
        
        cikanOdeme.setOdeme_id(table_CikanOdemeler.getSelectionModel().getSelectedItem().getOdeme_id());
        cikanOdeme.SetKimden(this.SetNameFromSelectedItem(table_CikanOdemeler.getSelectionModel().getSelectedItem().getKimden()).toString());
        cikanOdeme.SetKime(this.SetNameFromSelectedItem(table_CikanOdemeler.getSelectionModel().getSelectedItem().getKime()).toString());
        cikanOdeme.SetOdeme_tarihi(table_CikanOdemeler.getSelectionModel().getSelectedItem().getOdeme_tarihi());
        cikanOdeme.SetMiktar(table_CikanOdemeler.getSelectionModel().getSelectedItem().getMiktar());
        
        cikanOdemeRemove.delete(cikanOdeme.build());
        this.getCikanOdemeler();
        this.ReturnDefaultValueForTextFieldCikanOdemeler();
    }
    
    
    Cikan_odemelerInsertion cikanOdemeInsertion = new Cikan_odemelerInsertion();
    @FXML
    public void btnInsertCikanOdemeItem(ActionEvent event){
        cikanOdeme = new Cikan_odemelerBuilder();
        
        cikanOdeme.SetKasa(kasa.Find(userSelection.FindKasaIdFromUsers(userName)));
        cikanOdeme.SetKimden(this.SetNameFromTextField(textKimdenCikanOdeme.getText()).toString());
        cikanOdeme.SetKime(this.SetNameFromTextField(textKimeCikanOdeme.getText()).toString());
        cikanOdeme.SetOdeme_tarihi(Date.valueOf(textOdemeTarihiCikanOdeme.getText()));
        cikanOdeme.SetMiktar(BigInteger.valueOf(Long.valueOf(textMiktarCikanOdeme.getText())));
        
        cikanOdemeInsertion.insert(cikanOdeme.build());
        this.getCikanOdemeler();
        this.ReturnDefaultValueForTextFieldCikanOdemeler();
    }
    
    
    @FXML
    public void btnFindCikanOdemeItem(ActionEvent event){
        try{
            
            cikanOdemelerList = FXCollections.observableArrayList(
                    cikanOdemeler.FindFromOdemeItem(this.SetNameFromTextField(textKimdenCikanOdeme.getText()).toString()
                            , this.SetNameFromTextField(textKimeCikanOdeme.getText()).toString()));

            col_OdemeId.setCellValueFactory(new PropertyValueFactory<>("odeme_id"));
            col_Kimden.setCellValueFactory(new PropertyValueFactory<>("kimden"));
            col_Kime.setCellValueFactory(new PropertyValueFactory<>("kime"));
            col_odemeTarihi.setCellValueFactory(new PropertyValueFactory<>("odeme_tarihi"));
            col_miktar.setCellValueFactory(new PropertyValueFactory<>("miktar"));

            table_CikanOdemeler.setItems(cikanOdemelerList);
            this.ReturnDefaultValueForTextFieldCikanOdemeler();
        }catch(StringIndexOutOfBoundsException ex){
            System.out.println(ex.getMessage());
            cikanOdemelerList = FXCollections.observableArrayList();
            table_CikanOdemeler.setItems(cikanOdemelerList);
            this.ReturnDefaultValueForTextFieldCikanOdemeler();
            new Alert(Alert.AlertType.ERROR,"aradığınız eleman bulunamadı").showAndWait();
        }
    }
    
    @FXML
    public void btnCikanOdemelerListele(ActionEvent event){
        this.getCikanOdemeler();
        this.ReturnDefaultValueForTextFieldCikanOdemeler();
    }
    
    @FXML
    public void btnOdemeleritoplaCikanOdemeler(ActionEvent event){
        textMiktarCikanOdeme.setText(String.valueOf(butce.Cikan_odemeler(userSelection.FindKasaIdFromUsers(userName))));
    }
    
    
    
    
    
    @FXML
    private TextField textKimdenGirenOdeme;
    
    @FXML
    private TextField textOdemeTarihiGirenOdeme;
    
    @FXML
    private TextField textMiktarGirenOdeme;
    
    
    Giren_odemelerRemove girenOdemeRemove = new Giren_odemelerRemove();
    @FXML
    public void btnDeleteGirenOdemeItem(ActionEvent event){
        girenOdme = new Giren_odemelerBuilder();
        
        girenOdme.SetKasa(kasa.Find(userSelection.FindKasaIdFromUsers(userName)));
        
        girenOdme.setOdeme_id(table_GirenOdemeler.getSelectionModel().getSelectedItem().getOdeme_id());
        girenOdme.SetKimden(this.SetNameFromSelectedItem(table_GirenOdemeler.getSelectionModel().getSelectedItem().getKimden()).toString());
        girenOdme.SetOdeme_tarihi(table_GirenOdemeler.getSelectionModel().getSelectedItem().getOdeme_tarihi());
        girenOdme.SetMiktar(table_GirenOdemeler.getSelectionModel().getSelectedItem().getMiktar());
        
        girenOdemeRemove.delete(girenOdme.build());
        this.getGirenOdemeler();
        this.ReturnDefaultValueForTextFieldGirenOdemeler();
    }
    
    
    Giren_odemelerInsertion girenOdemeInsertion = new Giren_odemelerInsertion();
    @FXML
    public void btnInsertGirenOdemeItem(ActionEvent event){
        girenOdme = new Giren_odemelerBuilder();
        
        girenOdme.SetKasa(kasa.Find(userSelection.FindKasaIdFromUsers(userName)));
        girenOdme.SetKimden(this.SetNameFromTextField(textKimdenGirenOdeme.getText()).toString());
        girenOdme.SetOdeme_tarihi(Date.valueOf(textOdemeTarihiGirenOdeme.getText()));
        girenOdme.SetMiktar(BigInteger.valueOf(Long.valueOf(textMiktarGirenOdeme.getText())));
        
        girenOdemeInsertion.insert(girenOdme.build());
        this.getGirenOdemeler();
        this.ReturnDefaultValueForTextFieldGirenOdemeler();
    }
    
    
    @FXML
    public void btnFindGirenOdemeItem(ActionEvent event){
        try{
            
            System.out.println(this.SetNameFromTextField(textKimdenGirenOdeme.getText()).toString());

            girenOdemelerList = FXCollections.observableArrayList(
                    girenOdemeler.FindFromOdemeItem(this.SetNameFromTextField(textKimdenGirenOdeme.getText()).toString()));

            col_cikanOdemeler_OdemeId.setCellValueFactory(new PropertyValueFactory<>("odeme_id"));
            col_cikanOdemeler_Kimden.setCellValueFactory(new PropertyValueFactory<>("kimden"));
            col_cikanOdemeler_odemeTarihi.setCellValueFactory(new PropertyValueFactory<>("odeme_tarihi"));
            col_cikanOdemeler_miktar.setCellValueFactory(new PropertyValueFactory<>("miktar"));

            table_GirenOdemeler.setItems(girenOdemelerList);
            this.ReturnDefaultValueForTextFieldGirenOdemeler();
        }catch(StringIndexOutOfBoundsException ex){
            System.out.println(ex.getMessage());
            girenOdemelerList = FXCollections.observableArrayList();
            table_GirenOdemeler.setItems(girenOdemelerList);
            this.ReturnDefaultValueForTextFieldGirenOdemeler();
            new Alert(Alert.AlertType.ERROR,"aradığınız eleman bulunamadı").showAndWait();
        }
    }
    
    @FXML
    public void btnGirenOdemelerListele(ActionEvent event){
        this.getGirenOdemeler();
        this.ReturnDefaultValueForTextFieldGirenOdemeler();
    }
    
    @FXML
    public void btnOdemeleritoplaGirenOdemeler(ActionEvent event){
        textMiktarGirenOdeme.setText(String.valueOf(butce.Giren_odemeler(userSelection.FindKasaIdFromUsers(userName))));
    }
    
    
    
    
    @FXML
    private TextField texteleman_ismiMaas;
    
    @FXML
    private TextField textOdemeTarihiMaas;
    
    @FXML
    private TextField textMiktarMaas;
    
    
    MaaslarRemove MaaslarRemove = new MaaslarRemove();
    @FXML
    public void btnDeleteMaasItem(ActionEvent event){
        maas = new MaaslarBuilder();
        
        maas.SetKasa(kasa.Find(userSelection.FindKasaIdFromUsers(userName)));
        maas.SetEleman(eleman.FindFromName(this.SetNameFromSelectedItem(table_Maaslar.getSelectionModel().getSelectedItem().getEleman_ismi()).toString()));
        maas.SetOdeme_tarihi(table_Maaslar.getSelectionModel().getSelectedItem().getOdeme_tarihi());
        maas.SetMiktar(table_Maaslar.getSelectionModel().getSelectedItem().getMiktar());
        
        MaaslarRemove.delete(maas.build());
        this.getMaaslar();
        this.ReturnDefaultValueForTextFieldMaaslar();
    }
    
    
    MaaslarInsertion maasInsertion = new MaaslarInsertion();
    @FXML
    public void btnInsertMaasItem(ActionEvent event){
        maas = new MaaslarBuilder();
        
        maas.SetKasa(kasa.Find(userSelection.FindKasaIdFromUsers(userName)));
        maas.SetEleman(eleman.FindFromName(this.SetNameFromTextField(texteleman_ismiMaas.getText()).toString()));
        maas.SetOdeme_tarihi(Date.valueOf(textOdemeTarihiMaas.getText()));
        maas.SetMiktar(Integer.valueOf(textMiktarMaas.getText()));
        
        maasInsertion.insert(maas.build());
        this.getMaaslar();
        this.ReturnDefaultValueForTextFieldMaaslar();
    }
    
    
    @FXML
    public void btnFindMaasItem(ActionEvent event){
        
        
        try
        {
            System.out.println(this.SetNameFromTextField(texteleman_ismiMaas.getText()).toString());
            Eleman tmpEleman = eleman.FindFromName(this.SetNameFromTextField(texteleman_ismiMaas.getText()).toString());
            
            if(tmpEleman != null){
            maaslarList = FXCollections.observableArrayList(
                maaslar.FindFromOdemeItem(tmpEleman.getEleman_id()));
            }
            else{
                maaslarList = FXCollections.observableArrayList();
            }

            col_Maaslar_odemeId.setCellValueFactory(new PropertyValueFactory<>("maas_id"));
            col_elemanId.setCellValueFactory(new PropertyValueFactory<>("eleman_id"));
            col_eleman_ismi.setCellValueFactory(new PropertyValueFactory<>("eleman_ismi"));
            col_Maaslar_odemeTarihi.setCellValueFactory(new PropertyValueFactory<>("odeme_tarihi"));
            col_Maaslar_miktar.setCellValueFactory(new PropertyValueFactory<>("miktar"));

            table_Maaslar.setItems(maaslarList);
            this.ReturnDefaultValueForTextFieldMaaslar();
            
            
        }catch(StringIndexOutOfBoundsException ex){
            System.out.println(ex.getMessage());
            maaslarList = FXCollections.observableArrayList();
            table_Maaslar.setItems(maaslarList);
            this.ReturnDefaultValueForTextFieldMaaslar();
            new Alert(Alert.AlertType.ERROR,"aradığınız eleman bulunamadı").showAndWait();
        }
        
        
        
    }
    
    @FXML
    public void btnMaaslarListele(ActionEvent event){
        this.getMaaslar();
        this.ReturnDefaultValueForTextFieldMaaslar();
    }
    
    @FXML
    public void btnOdemeleritoplaMaaslar(ActionEvent event){
        textMiktarMaas.setText(String.valueOf(butce.Maaslar(userSelection.FindKasaIdFromUsers(userName))));
    }
    
    
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.getCikanOdemeler();
        this.getGirenOdemeler();
        this.getMaaslar();
    }

    public int getKasa_id() {
        return kasa_id;
    }

    public void setKasa_id(int kasa_id) {
        this.kasa_id = kasa_id;
    }
    
    private Name SetNameFromSelectedItem(String name){
        System.out.println(name);
        Name tmpName = new Name();
        
        tmpName.setFirst_name(name.substring(1 , name.indexOf(",")));
        tmpName.setMiddle_name(name.substring(name.indexOf(",")+2, name.lastIndexOf(",")-1));
        tmpName.setLast_name(name.substring(name.lastIndexOf(",")+1 , name.lastIndexOf(")")));
        
        return tmpName;
    }
    
    private Name SetNameFromTextField(String name){
        System.out.println(name);
        Name tmpName = new Name();
        
        tmpName.setFirst_name(name.substring(0 , name.indexOf(" ")));
        if( (name.indexOf(" ")+1) > (name.lastIndexOf(" ")) ){
            tmpName.setMiddle_name("");
        }
        else{
            tmpName.setMiddle_name(name.substring(name.indexOf(" ")+1, name.lastIndexOf(" ")));
        }
        tmpName.setLast_name(name.substring(name.lastIndexOf(" ")+1));
        
        return tmpName;
    }
    
   
    
    private void ReturnDefaultValueForTextFieldCikanOdemeler(){
        textKimdenCikanOdeme.setText("kimden");
        textKimeCikanOdeme.setText("kime");
        textOdemeTarihiCikanOdeme.setText("ödeme tarihi");
        textMiktarCikanOdeme.setText("miktar");
    }
    
    private void ReturnDefaultValueForTextFieldGirenOdemeler(){
        textKimdenGirenOdeme.setText("kimden");
        textOdemeTarihiGirenOdeme.setText("ödeme tarihi");
        textMiktarGirenOdeme.setText("miktar");
    }
    
    private void ReturnDefaultValueForTextFieldMaaslar(){
        texteleman_ismiMaas.setText("eleman_ismi");
        textOdemeTarihiMaas.setText("ödeme tarihi");
        textMiktarMaas.setText("miktar");
    }

    @FXML
    public void cikisYap(ActionEvent event) throws IOException{
        kapat();
        Parent root = FXMLLoader.load(getClass().getResource("/loginFXML/Login.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void kapat(){
        primaryStage = (Stage) cikis.getScene().getWindow();
        primaryStage.close();
    }
}
