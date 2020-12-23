package com.baran.controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.baran.main.Baglantilar;
import com.baran.view.OgrDataIki;

import com.jfoenix.controls.JFXComboBox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class OgrtmnDersBilgileriController implements Initializable {

    @FXML
    private JFXComboBox<String> cmbDers;

    @FXML
    private ListView<String> listvwicerik;

    @FXML
    private Label lblicerik;

    @FXML
    private TableView<OgrDataIki> tblvw;

    @FXML
    private TableColumn<OgrDataIki, Integer> tblvwno;

    @FXML
    private TableColumn<OgrDataIki, String> tblvwisim;

    @FXML
    private TableColumn<OgrDataIki, String> tblvwsoyisim;
    Baglantilar iliski=new Baglantilar();
    Alert mesaj=new Alert(AlertType.INFORMATION);
    Alert mesaj2=new Alert(AlertType.ERROR);
    ObservableList<OgrDataIki> list=FXCollections.observableArrayList();
    ObservableList<String> list1=FXCollections.observableArrayList();
    int dersnumarasý=0;
    String output=null;

    @FXML
    void cmbderssec(ActionEvent event) {
    	output = cmbDers.getSelectionModel().getSelectedItem().toString();
    	String sorgu3="select * from dersler where dersisim='"+output+"';";
    	ResultSet rs = iliski.sorgular(sorgu3);
    	try {
			while(rs.next()) {
				dersnumarasý=rs.getInt("dersid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void dersÝcerikler(ActionEvent event) {
    	String icerik = null;
    	listvwicerik.setVisible(true);
    	tblvw.setVisible(false);
    	listvwicerik.getItems().clear();
    	String sorgu="select *from dersicerik where dersid="+dersnumarasý+";";
    	ResultSet rs=iliski.sorgular(sorgu);
    	if(dersnumarasý==0) {
    		mesaj2.setTitle("hata");
			mesaj2.setHeaderText("Seçim Hatasý");
			mesaj2.setContentText("Lütfen ders seçimi yapýnýz");
			mesaj2.show();
			listvwicerik.setVisible(false);
    	}
    	else {
    		try {
    			while(rs.next()) {
    				icerik=rs.getString("icerik");
    				listvwicerik.getItems().add(icerik);
    			}
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    	    
    }

    @FXML
    void kayitliOgrenciler(ActionEvent event) {
    	tblvw.getItems().clear();
    	if(dersnumarasý==0) {
    		mesaj2.setTitle("hata");
			mesaj2.setHeaderText("Seçim Hatasý");
			mesaj2.setContentText("Lütfen ders seçimi yapýnýz");
			mesaj2.show();
    	}
    	else {
    	String sorgu="select *from ogrenci where ogrno in"+
    			"(select ogrno from ogrders where dersid in"+
    			"(select dersid from dersler where dersid="+dersnumarasý+"));";
    	ResultSet rs =iliski.sorgular(sorgu);
    	try {
			while(rs.next()) {
				int no=rs.getInt("ogrno");
				String isim=rs.getString("isim");
				String soyisim=rs.getString("soyisim");
				OgrDataIki kk=new OgrDataIki(no, isim, soyisim);
				tblvw.getItems().add(kk);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	tblvw.setVisible(true);
    	listvwicerik.setVisible(false);

    }

    }

    @FXML
    void ogrSayisi(ActionEvent event) {
    	tblvw.setVisible(false);
    	listvwicerik.setVisible(false);
    	String sorgu="select count(isim) as 'ogrsayi' from ogrenci where ogrno in"+
    	"(select ogrno from ogrders where dersid in(select dersid from dersler where dersid="+dersnumarasý+"));";
    	ResultSet rs=iliski.sorgular(sorgu);
    	
    	if(dersnumarasý ==0) {
    		mesaj2.setTitle("hata");
			mesaj2.setHeaderText("Seçim Hatasý");
			mesaj2.setContentText("Lütfen ders seçimi yapýnýz");
			mesaj2.show();
    	}
    	else {
    		try {
    			while(rs.next()) {
    				int no=rs.getInt("ogrsayi");
    				mesaj.setTitle("Bilgi Mesajý");
    				mesaj.setHeaderText("Öðrenci Sayýsý");
    				mesaj.setContentText(output+" dersine kayýtlý öðrenci sayýsý:"+no);
    				mesaj.show();
    			}
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		list1.clear();
		String sorgu="select * from dersler where hocaid="+GirisEkraniController.HocaGirenid+";";
		ResultSet rs=iliski.sorgular(sorgu);
		try {
			while(rs.next()) {
				String isim=rs.getString("dersisim");
				list1.add(isim);
				cmbDers.getItems().addAll(list1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tblvwno.setCellValueFactory(new PropertyValueFactory<>("NO"));
		tblvwisim.setCellValueFactory(new PropertyValueFactory<>("ISIM"));
		tblvwsoyisim.setCellValueFactory(new PropertyValueFactory<>("SOYISIM"));
		tblvw.setItems(list);
		
	}

}
