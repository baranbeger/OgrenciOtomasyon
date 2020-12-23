package com.baran.controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.baran.main.Baglantilar;

import com.jfoenix.controls.JFXComboBox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class OgrDersSecController implements Initializable {
	Baglantilar iliski=new Baglantilar();
    @FXML
    private JFXComboBox<String> lblcmb;

    @FXML
    private ListView<String> lstvw;
    String output="";
    int dersno=0;

    @FXML
    void cikisBtn(ActionEvent event) {
    	final Node source=(Node) event.getSource();
        final Stage stage=(Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void cmbDersSec(ActionEvent event) {
    	output = lblcmb.getSelectionModel().getSelectedItem().toString();
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
    void ekleBtn(ActionEvent event) {
      	String sorgu="insert into ogrders(ogrno,dersid) values("+GirisEkraniController.OgrGirenid+","+dersno+");";
    	iliski.islemler(sorgu);
    	lstvw.getItems().add(output);
    
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String sorgu="Select *from dersler";
		ResultSet rs=iliski.sorgular(sorgu);
		try {
			while(rs.next()) {
				String isimler=rs.getString("dersisim");
				lblcmb.getItems().add(isimler);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

}
