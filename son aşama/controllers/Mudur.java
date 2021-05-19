
package controllers;

import IsciCRUD.IsciInsertion;
import IsciCRUD.IsciRemove;
import IsciCRUD.IsciSelection;
import bolumCRUD.BolumSelection;
import entity.Isci;
import java.io.IOException;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static controllers.Users.bolum_id;
import static controllers.Users.proje_id;
import entity.BolumBuilder;
import entity.ElemanBuilder;
import entity.IsciBuilder;
import entity.Kasa;
import entity.KasaBuilder;
import entity.Muteahhit;
import entity.MuteahhitBuilder;
import entity.Proje;
import entity.ProjeBuilder;
import entity.Sozlesmeli;
import entity.SozlesmeliBuilder;
import java.sql.Date;
import javafx.scene.control.cell.PropertyValueFactory;
import kasaCRUD.KasaInsertion;
import kasaCRUD.KasaRemove;
import kasaCRUD.KasaSelection;
import muteahhitCRUD.MuteahhitInsertion;
import muteahhitCRUD.MuteahhitRemove;
import muteahhitCRUD.MuteahhitSelection;
import projeCRUD.ProjeInsertion;
import projeCRUD.ProjeRemove;
import projeCRUD.ProjeSelection;
import sozlesmeliCRUD.SozlesmeliInsertion;
import sozlesmeliCRUD.SozlesmeliRemove;
import sozlesmeliCRUD.SozlesmeliSelection;
import type.Address;
import type.Name;

public class Mudur implements Initializable {
    Stage primaryStage = new Stage();
    
    @FXML
    public Button cikis;
    
    @FXML
    private Button isciGoster;
    @FXML
    private Button muteahhitGoster;
    @FXML
    private Button sozlesmeliGoster;
    @FXML
    private Button kasaGoster;
    @FXML
    private Button projeGoster;
    
    
    @FXML
    private AnchorPane pane_isci;
    @FXML
    private AnchorPane pane_muteahhit;
    @FXML
    private AnchorPane pane_sozlesmeli;
    @FXML
    private AnchorPane pane_kasa;
    @FXML
    private AnchorPane pane_proje;
    
    /*
    @FXML
    private TextField text_elemanId;
    @FXML
    private TextField text_elemanIsmi;
    @FXML
    private TextField text_elemanCinsiyeti;
    @FXML
    private TextField text_elemanTelefonNo;
    @FXML
    private TextField text_IsciMeslek;
    @FXML
    private TextField text_MuteahhitIsAlani;
    @FXML
    private TextField text_SozlesmeliBitisTarihi;
    @FXML
    private TextField text_SozlesmeliUzmanlik;

*/
    
    @FXML
    private TableView<Isci> table_Isci;
    @FXML
    private TableColumn<Isci , Integer> col_IsciElemanId;
    @FXML
    private TableColumn<Isci , String> col_IsciElemanIsmi;
    @FXML
    private TableColumn<Isci , String> col_IsciElemanCinsyeti;
    @FXML
    private TableColumn<Isci , String> col_IsciElemanTelefonNo;
    @FXML
    private TableColumn<Isci , String> col_IsciMeslek;
    
    ObservableList<Isci> isciList;
    IsciSelection isci = new IsciSelection();
    
    public void getIsciler(){
        isciList = FXCollections.observableArrayList(
                isci.getIsciFromBolumId(bolum_id));
        
        System.err.println("435243456435643");
        
        col_IsciElemanId.setCellValueFactory(new PropertyValueFactory("eleman_id"));
        col_IsciElemanIsmi.setCellValueFactory(new PropertyValueFactory("isim"));
        col_IsciElemanCinsyeti.setCellValueFactory(new PropertyValueFactory("cinsyet"));
        col_IsciElemanTelefonNo.setCellValueFactory(new PropertyValueFactory("telefon_no"));
        col_IsciMeslek.setCellValueFactory(new PropertyValueFactory("meslek"));
        
        //System.out.println(isciList.toString());
        
        table_Isci.setItems(isciList);
    }
    
    
    @FXML
    private TableView<Muteahhit> table_Muteahhit;
    @FXML
    private TableColumn<Muteahhit , Integer> col_MuteahhitElemanId;
    @FXML
    private TableColumn<Muteahhit , String> col_MuteahhitIsmi;
    @FXML
    private TableColumn<Muteahhit , String> col_Muteahhitcinsyeti;
    @FXML
    private TableColumn<Muteahhit , String> col_MuteahhitTelefonNo;
    @FXML
    private TableColumn<Muteahhit , String> col_MuteahhitIsAlani;
    
    ObservableList<Muteahhit> muteahhitList;
    MuteahhitSelection muteahhit = new MuteahhitSelection();
    
    public void getMuteahhitler(){
        muteahhitList = FXCollections.observableArrayList(
                muteahhit.getMuteahhiFromBolumIdt(bolum_id));
        
        System.err.println("435243456435643");
        
        col_MuteahhitElemanId.setCellValueFactory(new PropertyValueFactory("eleman_id"));
        col_MuteahhitIsmi.setCellValueFactory(new PropertyValueFactory("isim"));
        col_Muteahhitcinsyeti.setCellValueFactory(new PropertyValueFactory("cinsyet"));
        col_MuteahhitTelefonNo.setCellValueFactory(new PropertyValueFactory("telefon_no"));
        col_MuteahhitIsAlani.setCellValueFactory(new PropertyValueFactory("is_alani"));
        
        table_Muteahhit.setItems(muteahhitList);
    }
    
    
    
    @FXML
    private TableView<Sozlesmeli> table_Sozlesmeli;
    @FXML
    private TableColumn<Sozlesmeli , Integer> col_SozlesmeliId;
    @FXML
    private TableColumn<Sozlesmeli , String> col_SozlesmeliIsmi;
    @FXML
    private TableColumn<Sozlesmeli , String> col_SozlesmeliCinsyeti;
    @FXML
    private TableColumn<Sozlesmeli , String> col_SozlesmeliTelefonNo;
    @FXML
    private TableColumn<Sozlesmeli , Date> col_SozlesmelinBitisTarihi;
    @FXML
    private TableColumn<Sozlesmeli , String> col_SozlesmeliUzmanlik;
    
    ObservableList<Sozlesmeli> sozlesmeliList;
    SozlesmeliSelection sozlesmeli = new SozlesmeliSelection();
    
    public void getSozlesmeli(){
        sozlesmeliList = FXCollections.observableArrayList(
                sozlesmeli.getSozlesmeliFromBolumId(bolum_id));
        
        System.err.println("435243456435643");
        
        col_SozlesmeliId.setCellValueFactory(new PropertyValueFactory("eleman_id"));
        col_SozlesmeliIsmi.setCellValueFactory(new PropertyValueFactory("isim"));
        col_SozlesmeliCinsyeti.setCellValueFactory(new PropertyValueFactory("cinsyet"));
        col_SozlesmeliTelefonNo.setCellValueFactory(new PropertyValueFactory("telefon_no"));
        col_SozlesmelinBitisTarihi.setCellValueFactory(new PropertyValueFactory("bitis_tarihi"));
        col_SozlesmeliUzmanlik.setCellValueFactory(new PropertyValueFactory("uzmanlik"));
        
        System.out.println(sozlesmeliList.toString());
        
        table_Sozlesmeli.setItems(sozlesmeliList);
    }
    
    
    @FXML
    private TableView<Kasa> table_Kasa;
    @FXML
    private TableColumn<Kasa , Integer> col_KasaId;
    @FXML
    private TableColumn<Kasa , String> col_KasaIsmi;
    @FXML
    private TableColumn<Kasa , String> col_Kasasifresi;
    
    ObservableList<Kasa> kasaList;
    KasaSelection kasa = new KasaSelection();
    
    public void getKasa(){
        System.err.println("12212121212122112");
        kasaList = FXCollections.observableArrayList(
                kasa.getKasaFromBolumId(bolum_id));
        
        System.err.println("435243456435643");
        
        col_KasaId.setCellValueFactory(new PropertyValueFactory("kasa_id"));
        col_KasaIsmi.setCellValueFactory(new PropertyValueFactory("isim"));
        col_Kasasifresi.setCellValueFactory(new PropertyValueFactory("sifre"));
        
        System.out.println(kasaList.toString());
        
        table_Kasa.setItems(kasaList);
    }

    
    @FXML
    private TableView<Proje> table_Proje;
    @FXML
    private TableColumn<Proje , Integer> col_ProjeId;
    @FXML
    private TableColumn<Proje , String> col_ProjeIsmi;
    @FXML
    private TableColumn<Proje , String> col_ProjeBolumIsmi;
    @FXML
    private TableColumn<Proje , String> col_ProjeKonum;
    
    ObservableList<Proje> projeList;
    ProjeSelection proje = new ProjeSelection();
    
    public void getProje(){
        System.err.println("12212121212122112");
        projeList = FXCollections.observableArrayList(
                proje.getProjeFromBolumId(bolum_id));
        
        System.err.println("435243456435643");
        
        col_ProjeId.setCellValueFactory(new PropertyValueFactory("proje_id"));
        col_ProjeIsmi.setCellValueFactory(new PropertyValueFactory("isim"));
        col_ProjeBolumIsmi.setCellValueFactory(new PropertyValueFactory("bolum_ismi"));
        col_ProjeKonum.setCellValueFactory(new PropertyValueFactory("konum"));
        
        System.out.println(projeList.toString());
        
        table_Proje.setItems(projeList);
    }
    
    
    
    @FXML
    private TextField text_IsciId;
    @FXML
    private TextField text_IsciIsim;
    @FXML
    private TextField text_IsciDogumTarihi;
    @FXML
    private TextField text_IsciTelefonNumarasi;
    @FXML
    private TextField text_IsciMeslek;
    @FXML
    private Button IsciListele;
    
    
    IsciInsertion isciInsert = new IsciInsertion();
    ElemanBuilder eleman = new ElemanBuilder();
    
    @FXML
    public void IsciInsert(ActionEvent event){
        BolumSelection bolum = new BolumSelection();
        eleman.SetBolum_id(bolum.FindID(bolum_id));
        Name isim = new Name();
        isim.setFirst_name(this.SetNameFromTextField(this.text_IsciIsim.getText()).getFirst_name());
        isim.setMiddle_name(this.SetNameFromTextField(this.text_IsciIsim.getText()).getMiddle_name());
        isim.setLast_name(this.SetNameFromTextField(this.text_IsciIsim.getText()).getLast_name());
        eleman.SetIsim(isim.toString());
        eleman.SetDogum_tarihi(Date.valueOf(this.text_IsciDogumTarihi.getText()));
        Address konum = new Address();
        konum.setCountry("irak");
        konum.setCity("erbil");
        konum.setStreet("dream");
        konum.setAddress("irak erbil dream city");
        eleman.SetKonum(konum.toString());
        eleman.SetTelefon_no(this.text_IsciTelefonNumarasi.getText());
        eleman.SetCinsiyet("M");
        
        ProjeSelection proje = new ProjeSelection();
        eleman.SetProje(proje.Find(proje_id));
        
        IsciBuilder tmpIsci = new IsciBuilder();
        tmpIsci.SetEleman(eleman.build());
        tmpIsci.SetMeslek(this.text_IsciMeslek.getText());
        
        
        isciInsert.insert(tmpIsci.build());
        this.ActionIsci(event);
    }
    
    IsciRemove isciRemove = new IsciRemove();
    
    @FXML
    public void IsciRemove(ActionEvent event){
        Name isim = new Name();
        isim.setFirst_name(this.SetNameFromSelectedItem(table_Isci.getSelectionModel().getSelectedItem().getIsim()).getFirst_name());
        isim.setMiddle_name(this.SetNameFromSelectedItem(table_Isci.getSelectionModel().getSelectedItem().getIsim()).getMiddle_name());
        isim.setLast_name(this.SetNameFromSelectedItem(table_Isci.getSelectionModel().getSelectedItem().getIsim()).getLast_name());
        
        IsciBuilder tmpIsci = new IsciBuilder();
        tmpIsci.SetEleman(isci.FindFromIsim(isim.toString()).getEleman());
        
        isciRemove.delete(tmpIsci.build());
        this.ActionIsci(event);
    }
    
    @FXML
    public void IsciArama(ActionEvent event){
        Name isim = new Name();
        isim.setFirst_name(this.SetNameFromTextField(this.text_IsciIsim.getText()).getFirst_name());
        isim.setMiddle_name(this.SetNameFromTextField(this.text_IsciIsim.getText()).getMiddle_name());
        isim.setLast_name(this.SetNameFromTextField(this.text_IsciIsim.getText()).getLast_name());
        
        IsciBuilder tmpIsci = new IsciBuilder();
        tmpIsci.SetEleman(isci.FindFromIsim(isim.toString()).getEleman());
        
        isciList = FXCollections.observableArrayList(tmpIsci.build());
        
        col_IsciElemanId.setCellValueFactory(new PropertyValueFactory("eleman_id"));
        col_IsciElemanIsmi.setCellValueFactory(new PropertyValueFactory("isim"));
        col_IsciElemanCinsyeti.setCellValueFactory(new PropertyValueFactory("cinsyet"));
        col_IsciElemanTelefonNo.setCellValueFactory(new PropertyValueFactory("telefon_no"));
        col_IsciMeslek.setCellValueFactory(new PropertyValueFactory("meslek"));
        
        table_Isci.setItems(isciList);
    }
    
    
    @FXML
    private TextField text_MuteahhitIsim;
    @FXML
    private TextField text_MuteahhitDogumTarihi;
    @FXML
    private TextField text_MuteahhitTelefonNumarasi;
    @FXML
    private TextField text_MuteahhitIsAlani;
    @FXML
    private Button MuteahhitListele;
    
    MuteahhitInsertion muteahhitInsert = new MuteahhitInsertion();
    MuteahhitBuilder tmpMuteahhit = new MuteahhitBuilder();
    
    @FXML
    public void MuteahhitInsert(ActionEvent event){
        BolumSelection bolum = new BolumSelection();
        eleman.SetBolum_id(bolum.FindID(bolum_id));
        Name isim = new Name();
        isim.setFirst_name(this.SetNameFromTextField(this.text_MuteahhitIsim.getText()).getFirst_name());
        isim.setMiddle_name(this.SetNameFromTextField(this.text_MuteahhitIsim.getText()).getMiddle_name());
        isim.setLast_name(this.SetNameFromTextField(this.text_MuteahhitIsim.getText()).getLast_name());
        eleman.SetIsim(isim.toString());
        eleman.SetDogum_tarihi(Date.valueOf(this.text_MuteahhitDogumTarihi.getText()));
        Address konum = new Address();
        konum.setCountry("irak");
        konum.setCity("erbil");
        konum.setStreet("dream");
        konum.setAddress("irak erbil dream city");
        eleman.SetKonum(konum.toString());
        eleman.SetTelefon_no(this.text_MuteahhitTelefonNumarasi.getText());
        eleman.SetCinsiyet("M");
        
        ProjeSelection proje = new ProjeSelection();
        eleman.SetProje(proje.Find(proje_id));
        
        MuteahhitBuilder tmpmuteahhit = new MuteahhitBuilder();
        tmpmuteahhit.SetEleman(eleman.build());
        tmpmuteahhit.SetIs_alani(this.text_MuteahhitIsAlani.getText());
        
        
        muteahhitInsert.insert(tmpmuteahhit.build());
        this.ActionMuteahhit(event);
    }
    
    MuteahhitRemove muteahhitRemove = new MuteahhitRemove();
    
    @FXML
    public void MuteahhitRemove(ActionEvent event){
        Name isim = new Name();
        isim.setFirst_name(this.SetNameFromSelectedItem(table_Muteahhit.getSelectionModel().getSelectedItem().getIsim()).getFirst_name());
        isim.setMiddle_name(this.SetNameFromSelectedItem(table_Muteahhit.getSelectionModel().getSelectedItem().getIsim()).getMiddle_name());
        isim.setLast_name(this.SetNameFromSelectedItem(table_Muteahhit.getSelectionModel().getSelectedItem().getIsim()).getLast_name());
        
        tmpMuteahhit = new MuteahhitBuilder();
        tmpMuteahhit.SetEleman(muteahhit.FindFromIsim(isim.toString()).getEleman());
        
        muteahhitRemove.delete(tmpMuteahhit.build());
        this.ActionMuteahhit(event);
    }
    
    @FXML
    public void MuteahhitArama(ActionEvent event){
        Name isim = new Name();
        isim.setFirst_name(this.SetNameFromTextField(this.text_MuteahhitIsim.getText()).getFirst_name());
        isim.setMiddle_name(this.SetNameFromTextField(this.text_MuteahhitIsim.getText()).getMiddle_name());
        isim.setLast_name(this.SetNameFromTextField(this.text_MuteahhitIsim.getText()).getLast_name());
        
        tmpMuteahhit = new MuteahhitBuilder();
        tmpMuteahhit.SetEleman(muteahhit.FindFromIsim(isim.toString()).getEleman());
        tmpMuteahhit.SetIs_alani(muteahhit.FindFromIsim(isim.toString()).getIs_alani());
        
        muteahhitList = FXCollections.observableArrayList(tmpMuteahhit.build());
        
        col_MuteahhitElemanId.setCellValueFactory(new PropertyValueFactory("eleman_id"));
        col_MuteahhitIsmi.setCellValueFactory(new PropertyValueFactory("isim"));
        col_Muteahhitcinsyeti.setCellValueFactory(new PropertyValueFactory("cinsyet"));
        col_MuteahhitTelefonNo.setCellValueFactory(new PropertyValueFactory("telefon_no"));
        col_MuteahhitIsAlani.setCellValueFactory(new PropertyValueFactory("is_alani"));
        
        table_Muteahhit.setItems(muteahhitList);
    }
    
    

    @FXML
    private TextField text_SozlesmeliIsim;
    @FXML
    private TextField text_SozlesmeliDogumTarihi;
    @FXML
    private TextField text_SozlesmeliTelefonNumarasi;
    @FXML
    private TextField text_SozlesmeliBitisTarihi;
    @FXML
    private TextField text_SozlesmeliUzmanlik;
    
    SozlesmeliInsertion sozlesmeliInsert = new SozlesmeliInsertion();
    SozlesmeliBuilder tmpSozlesmeli = new SozlesmeliBuilder();
    
    @FXML
    public void SozlesmeliInsert(ActionEvent event){
        eleman = new ElemanBuilder();
        
        BolumSelection bolum = new BolumSelection();
        eleman.SetBolum_id(bolum.FindID(bolum_id));
        Name isim = new Name();
        isim.setFirst_name(this.SetNameFromTextField(this.text_SozlesmeliIsim.getText()).getFirst_name());
        isim.setMiddle_name(this.SetNameFromTextField(this.text_SozlesmeliIsim.getText()).getMiddle_name());
        isim.setLast_name(this.SetNameFromTextField(this.text_SozlesmeliIsim.getText()).getLast_name());
        eleman.SetIsim(isim.toString());
        eleman.SetDogum_tarihi(Date.valueOf(this.text_SozlesmeliDogumTarihi.getText()));
        Address konum = new Address();
        konum.setCountry("irak");
        konum.setCity("tekrit");
        konum.setStreet("dream");
        konum.setAddress("irak erbil dream city");
        eleman.SetKonum(konum.toString());
        eleman.SetTelefon_no(this.text_SozlesmeliTelefonNumarasi.getText());
        eleman.SetCinsiyet("M");
        
        //ProjeSelection proje = new ProjeSelection();
        //eleman.SetProje(proje.Find(proje_id));
        
        tmpSozlesmeli = new SozlesmeliBuilder();
        tmpSozlesmeli.SetEleman(eleman.build());
        tmpSozlesmeli.SetBaslangic_tarihi(Date.valueOf("2020-01-01"));
        tmpSozlesmeli.SetBitis_tarihi(Date.valueOf(this.text_SozlesmeliBitisTarihi.getText()));
        tmpSozlesmeli.SetUzmanlik(this.text_SozlesmeliUzmanlik.getText());
        
        System.err.println(tmpSozlesmeli.build());
        
        sozlesmeliInsert.insert(tmpSozlesmeli.build());
        this.ActionSozlesmeli(event);
    }
    
    SozlesmeliRemove sozlesmeliRemove = new SozlesmeliRemove();
    
    @FXML
    public void SozlesmeliRemove(ActionEvent event){
        Name isim = new Name();
        isim.setFirst_name(this.SetNameFromSelectedItem(table_Sozlesmeli.getSelectionModel().getSelectedItem().getIsim()).getFirst_name());
        isim.setMiddle_name(this.SetNameFromSelectedItem(table_Sozlesmeli.getSelectionModel().getSelectedItem().getIsim()).getMiddle_name());
        isim.setLast_name(this.SetNameFromSelectedItem(table_Sozlesmeli.getSelectionModel().getSelectedItem().getIsim()).getLast_name());
        
        tmpSozlesmeli = new SozlesmeliBuilder();
        tmpSozlesmeli.SetEleman(sozlesmeli.FindFromIsim(isim.toString()).getEleman());
        
        sozlesmeliRemove.delete(tmpSozlesmeli.build());
        this.ActionSozlesmeli(event);
    }
    
    @FXML
    public void SozlesmeliArama(ActionEvent event){
        Name isim = new Name();
        isim.setFirst_name(this.SetNameFromTextField(this.text_SozlesmeliIsim.getText()).getFirst_name());
        isim.setMiddle_name(this.SetNameFromTextField(this.text_SozlesmeliIsim.getText()).getMiddle_name());
        isim.setLast_name(this.SetNameFromTextField(this.text_SozlesmeliIsim.getText()).getLast_name());
        
        tmpSozlesmeli = new SozlesmeliBuilder();
        tmpSozlesmeli.SetEleman(sozlesmeli.FindFromIsim(isim.toString()).getEleman());
        tmpSozlesmeli.SetBitis_tarihi(sozlesmeli.FindFromIsim(isim.toString()).getBitis_tarihi());
        tmpSozlesmeli.SetUzmanlik(sozlesmeli.FindFromIsim(isim.toString()).getUzmanlik());
        
        sozlesmeliList = FXCollections.observableArrayList(sozlesmeli.FindFromIsim(isim.toString()));
        
        col_SozlesmeliId.setCellValueFactory(new PropertyValueFactory("eleman_id"));
        col_SozlesmeliIsmi.setCellValueFactory(new PropertyValueFactory("isim"));
        col_SozlesmeliCinsyeti.setCellValueFactory(new PropertyValueFactory("cinsyet"));
        col_SozlesmeliTelefonNo.setCellValueFactory(new PropertyValueFactory("telefon_no"));
        col_SozlesmelinBitisTarihi.setCellValueFactory(new PropertyValueFactory("bitis_tarihi"));
        col_SozlesmeliUzmanlik.setCellValueFactory(new PropertyValueFactory("uzmanlik"));
        
        table_Sozlesmeli.setItems(sozlesmeliList);
    }
    
    
    @FXML
    private TextField text_KasaId;
    @FXML
    private TextField text_KasaMuhasipIsmi;
    @FXML
    private TextField text_KasaProjeId;
    @FXML
    private TextField text_KasaSifre;
    
    
    KasaInsertion kasaInsert = new KasaInsertion();
    KasaBuilder tmpKasa = new KasaBuilder();
    
    @FXML
    public void KasaInsert(ActionEvent event){
        tmpKasa = new KasaBuilder();
        
        SozlesmeliSelection muhasip = new SozlesmeliSelection();
        tmpKasa.SetMuhasip(muhasip.FindFromIsim(this.SetNameFromTextField(this.text_KasaMuhasipIsmi.getText()).toString()));
        
        proje = new ProjeSelection();
        if(this.text_KasaProjeId.getText().equals("0") || this.text_KasaProjeId.getText().equals("proje id")){
            tmpKasa.SetProje(null);
        }
        else{
            BolumSelection bolum = new BolumSelection();
            tmpKasa.SetBolum(bolum.FindID(4));
            tmpKasa.SetProje(proje.Find(Integer.valueOf(this.text_KasaProjeId.getText())));
        }
        
        tmpKasa.SetSifre(this.text_KasaSifre.getText());
        
        
        kasaInsert.insert(tmpKasa.build());
        this.ActionKasa(event);
    }
    
    KasaRemove kasaRemove = new KasaRemove();
    
    @FXML
    public void KasaRemove(ActionEvent event){
        
        tmpKasa = new KasaBuilder();
        tmpKasa.setKasa_id(kasa.Find(table_Kasa.getSelectionModel().getSelectedItem().getKasa_id()).getKasa_id());
        
        kasaRemove.delete(tmpKasa.build());
        this.ActionKasa(event);
    }
    
    @FXML
    public void KasaArama(ActionEvent event){
        tmpKasa = new KasaBuilder();
        tmpKasa.setKasa_id(kasa.FindFromMuhasip_id(sozlesmeli.FindFromIsim(this.SetNameFromTextField(this.text_KasaMuhasipIsmi.getText()).toString()).getEleman_id()).getKasa_id());
        tmpKasa.SetBolum(kasa.FindFromMuhasip_id(sozlesmeli.FindFromIsim(this.SetNameFromTextField(this.text_KasaMuhasipIsmi.getText()).toString()).getEleman_id()).getBolum());
        tmpKasa.SetMuhasip(kasa.FindFromMuhasip_id(sozlesmeli.FindFromIsim(this.SetNameFromTextField(this.text_KasaMuhasipIsmi.getText()).toString()).getEleman_id()).getMuhasip());
        tmpKasa.SetProje(kasa.FindFromMuhasip_id(sozlesmeli.FindFromIsim(this.SetNameFromTextField(this.text_KasaMuhasipIsmi.getText()).toString()).getEleman_id()).getProje());
        tmpKasa.SetSifre(kasa.FindFromMuhasip_id(sozlesmeli.FindFromIsim(this.SetNameFromTextField(this.text_KasaMuhasipIsmi.getText()).toString()).getEleman_id()).getSifre());
        
        
        kasaList = FXCollections.observableArrayList(tmpKasa.build());
        
        col_KasaId.setCellValueFactory(new PropertyValueFactory("kasa_id"));
        col_KasaIsmi.setCellValueFactory(new PropertyValueFactory("isim"));
        col_Kasasifresi.setCellValueFactory(new PropertyValueFactory("sifre"));
        
        table_Kasa.setItems(kasaList);
    }
    
    
    @FXML
    private TextField text_ProjeId;
    @FXML
    private TextField text_ProjeBolumIsmi;
    @FXML
    private TextField text_ProjeIsmi;
    @FXML
    private TextField text_ProjeKonum;
    
    
    ProjeInsertion projeInsert = new ProjeInsertion();
    ProjeBuilder tmpProje = new ProjeBuilder();
    
    @FXML
    public void ProjeInsert(ActionEvent event){
        BolumSelection bolum = new BolumSelection();
        
        tmpProje.SetBolum(bolum.FindName(this.text_ProjeBolumIsmi.getText()));
        
        tmpProje.SetIsim(this.text_ProjeIsmi.getText());
        
        Address konum = new Address();
        konum.setAddress(this.text_ProjeKonum.getText());
        
        
        ProjeInsertion insert = new ProjeInsertion();
        insert.insert(tmpProje.build(),konum);
        this.ActionProje(event);
    }
    
    ProjeRemove projeRemove = new ProjeRemove();
    
    @FXML
    public void ProjeRemove(ActionEvent event){
        
        projeRemove.delete(proje.Find(table_Proje.getSelectionModel().getSelectedItem().getProje_id()));
        this.ActionProje(event);
    }
    
    @FXML
    public void ProjeArama(ActionEvent event){
        tmpProje = new ProjeBuilder();
        if(proje.FindFromisim(this.text_ProjeIsmi.getText()) != null){
            tmpProje.SetProje(proje.FindFromisim(this.text_ProjeIsmi.getText()));
            projeList = FXCollections.observableArrayList(tmpProje.build());
        }
        else{
            projeList = FXCollections.observableArrayList();
        }
        
        
        
        
        col_ProjeId.setCellValueFactory(new PropertyValueFactory("proje_id"));
        col_ProjeIsmi.setCellValueFactory(new PropertyValueFactory("isim"));
        col_ProjeBolumIsmi.setCellValueFactory(new PropertyValueFactory("bolum_ismi"));
        col_ProjeKonum.setCellValueFactory(new PropertyValueFactory("konum"));
        
        table_Proje.setItems(projeList);
    }
    
    
    
    
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.getIsciler();
        this.getMuteahhitler();
        this.getSozlesmeli();
        this.getKasa();
        this.getProje();
    }
    
    
    @FXML
    public void ActionIsci(ActionEvent event){
        this.getIsciler();
        this.goterIsci(event);
    }
    
    @FXML
    public void ActionMuteahhit(ActionEvent event){
        this.getMuteahhitler();
        this.goterMuteahhit(event);
    }
    
    @FXML
    public void ActionSozlesmeli(ActionEvent event){
        this.getSozlesmeli();
        this.goterSozlesmeli(event);
    }
    
    @FXML
    public void ActionKasa(ActionEvent event){
        this.getKasa();
        this.goterKasa(event);
    }
    
    @FXML
    public void ActionProje(ActionEvent event){
        this.getProje();
        this.goterProje(event);
    }
    
    private void goterIsci(ActionEvent event){
        pane_isci.setVisible(true);
        pane_muteahhit.setVisible(false);
        pane_sozlesmeli.setVisible(false);
        pane_kasa.setVisible(false);
        pane_proje.setVisible(false);
    }
    
    private void goterMuteahhit(ActionEvent event){
        pane_isci.setVisible(false);
        pane_muteahhit.setVisible(true);
        pane_sozlesmeli.setVisible(false);
        pane_kasa.setVisible(false);
        pane_proje.setVisible(false);
    }
    
    private void goterSozlesmeli(ActionEvent event){
        pane_isci.setVisible(false);
        pane_muteahhit.setVisible(false);
        pane_sozlesmeli.setVisible(true);
        pane_kasa.setVisible(false);
        pane_proje.setVisible(false);
    }
    
    private void goterKasa(ActionEvent event){
        pane_isci.setVisible(false);
        pane_muteahhit.setVisible(false);
        pane_sozlesmeli.setVisible(false);
        pane_kasa.setVisible(true);
        pane_proje.setVisible(false);
    }
    
    private void goterProje(ActionEvent event){
        pane_isci.setVisible(false);
        pane_muteahhit.setVisible(false);
        pane_sozlesmeli.setVisible(false);
        pane_kasa.setVisible(false);
        pane_proje.setVisible(true);
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
        System.out.println(name.indexOf(" ")+1);
        System.out.println(name.lastIndexOf(" "));
        if( (name.indexOf(" ")+1) > (name.lastIndexOf(" ")) ){
            tmpName.setMiddle_name(" ");
        }
        else{
            tmpName.setMiddle_name(name.substring(name.indexOf(" ")+1, name.lastIndexOf(" ")));
        }
        tmpName.setLast_name(name.substring(name.lastIndexOf(" ")+1));
        
        return tmpName;
    }
    
    private Address SetKonumFromTextField(String konum){
        System.out.println(konum);
        Address adres = new Address();
        
        adres.setAddress(konum);
        
        return adres;
    }
    
    
}
