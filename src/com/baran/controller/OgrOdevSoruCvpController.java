package com.baran.controller;

import com.baran.main.Baglantilar;

import com.jfoenix.controls.JFXTextArea;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class OgrOdevSoruCvpController {

    @FXML
    private JFXTextArea cevapTxtArea;
    Baglantilar iliski=new Baglantilar();
    int cevapno=0;
    Alert mesaj=new Alert(AlertType.ERROR);

    @FXML
    void cevapKaydetBtn(ActionEvent event) {
    	String cevaplar=cevapTxtArea.getText();
    	String sorgu="insert into cevaplar(cevap,cevaptarih,soruid,ogrno) values('"+cevaplar+
    			"', curtime(),"+OgrOdevSoruController.sinavsoru+","+GirisEkraniController.OgrGirenid+");";
    	
    		iliski.islemler(sorgu);
    		
    		
    	
    	
    	final Node source=(Node) event.getSource();
        final Stage stage=(Stage) source.getScene().getWindow();
        stage.close();
    }

}
