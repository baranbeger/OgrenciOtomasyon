package com.baran.controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.baran.main.Baglantilar;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class OgrtmnOdevSorusuController implements Initializable{

    @FXML
    private JFXComboBox<String> lblcmc;

    @FXML
    private JFXTextField soruIsmitxt;

    @FXML
    private JFXTextArea sorutxtarea;
    int dersno=0;
    String output="";
    Alert mesaj=new Alert(AlertType.ERROR);
    Baglantilar iliski=new Baglantilar();

    @FXML
    void dersSecCmb(ActionEvent event) {
    	output = lblcmc.getSelectionModel().getSelectedItem().toString();
    	String sorgu3="select * from dersler where dersisim='"+output+"';";
    	ResultSet rs = iliski.sorgular(sorgu3);
    	try {
			while(rs.next()) {
				dersno=rs.getInt("dersid");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void kaydetBtn(ActionEvent event) {
    	
    	String soruisim=soruIsmitxt.getText();
    	String soru=sorutxtarea.getText();
    	String sorgu="insert into sinavsoru(soruisim,soru,dersid) values ('"+soruisim+"','"+soru+"',"+dersno+");";
    	if(!soruisim.equals("") && !soru.equals("")) {
    		iliski.islemler(sorgu);
    		
    	}
    	else {
    		mesaj.setTitle("hata");
			mesaj.setHeaderText("Seçim Hatasý");
			mesaj.setContentText("Lütfen soru ismi vesoruyu giriniz");
			mesaj.show();
    	}
    	final Node source=(Node) event.getSource();
        final Stage stage=(Stage) source.getScene().getWindow();
        stage.close();
   
    }

    @FXML
    void vazgecBtn(ActionEvent event) {
    	final Node source=(Node) event.getSource();
        final Stage stage=(Stage) source.getScene().getWindow();
        stage.close();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String sorgu="select *from dersler where hocaid="+GirisEkraniController.HocaGirenid+";";
		ResultSet rs=iliski.sorgular(sorgu);
		try {
			while(rs.next()) {
				String konuIsimler=rs.getString("dersisim");
				lblcmc.getItems().add(konuIsimler);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

}
