package com.baran.controller;

import com.baran.main.Baglantilar;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class OgrKaydolController {

    @FXML
    private TextField txtOgrNo;

    @FXML
    private TextField txtIsim;

    @FXML
    private TextField txtSoyIsim;

    @FXML
    private TextField txtKullaniciAd;

    @FXML
    private PasswordField txtSifre;

    @FXML
    private PasswordField txtSifreTekrar;
    
    Baglantilar iliski=new Baglantilar();
    
    String isim,soyisim,kulad,sifre,sifre2,ogrno;
    int ogrnoTamSayi;
    Alert mesaj=new Alert(AlertType.ERROR);
    @FXML
    void kaydetBtn(ActionEvent event) {
    	ogrno=txtOgrNo.getText();
    	isim=txtIsim.getText();
    	soyisim=txtSoyIsim.getText();
    	kulad=txtKullaniciAd.getText();
    	sifre=txtSifre.getText();
    	sifre2=txtSifreTekrar.getText();
    	ogrnoTamSayi=Integer.parseInt(ogrno);
  
   
          
        if(sifre.equals(sifre2)) {
		  String sorgu="insert into ogrenci(ogrno,isim,soyisim,kullaniciad,sifre) values("+ogrnoTamSayi+
		    ",'"+isim+"','"+soyisim+"','"+kulad+"','"+sifre+"');";
			 iliski.islemler(sorgu);
			       	final Node source=(Node) event.getSource();
			        final Stage stage=(Stage) source.getScene().getWindow();
			        stage.close();
		                 }
		             else {
		            	 mesaj.setTitle("Hata Mesaji");
							mesaj.setHeaderText("Hatali giris");
							mesaj.setContentText("sifreler uyumsuz");
							mesaj.show();
							txtSifre.clear();
							txtSifreTekrar.clear();
		                                                      }	
   	}
   	
    	
    	
    }


