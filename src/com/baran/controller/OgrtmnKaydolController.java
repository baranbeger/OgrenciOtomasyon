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

public class OgrtmnKaydolController {

    @FXML
    private TextField txtIsim;

    @FXML
    private TextField txtSoyIsim;

    @FXML
    private TextField txtKullaniciAd;

    @FXML
    private PasswordField txtSifre;

    @FXML
    private PasswordField txtSfireTekrar;

    @FXML
    void kaydetBtn(ActionEvent event) {
    	Alert mesaj=new Alert(AlertType.ERROR);
    	Baglantilar iliski=new Baglantilar();
    	String isim,soyisim,kullaniciad,sifre,sifre2;
    	isim=txtIsim.getText();
    	soyisim=txtSoyIsim.getText();
    	kullaniciad=txtKullaniciAd.getText();
    	sifre=txtSifre.getText();
    	sifre2=txtSfireTekrar.getText();
    	String sorgu="insert into hoca(hocaisim,hocasoyisim,kullaniciad,sifre) values('"+isim+"','"+soyisim+
    			"','"+kullaniciad+"','"+sifre+"');";
    	if(sifre.equals(sifre2)) {
    		try {
				iliski.islemler(sorgu);
			} catch (Exception e) {
				mesaj.setTitle("Hata Mesaji");
				mesaj.setHeaderText("Hatali kaydediþ");
				mesaj.setContentText("Kayýt iþleminizde bir hata Olmuþtur.Lütfen daha sonra bir kere daha deneyiniz");
				mesaj.show();
				throw(e);
			}
    		
    	}
    	else {
    		mesaj.setTitle("Hata Mesaji");
			mesaj.setHeaderText("Þifre Hatasý");
			mesaj.setContentText("Þifreleriniz uyumsuz.Lütfen daha sonra tekrar deneyiniz");
			mesaj.show();
    		
    	}
    	
    	final Node source=(Node) event.getSource();
        final Stage stage=(Stage) source.getScene().getWindow();
        stage.close();
    }

}
