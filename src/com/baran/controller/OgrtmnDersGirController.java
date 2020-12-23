package com.baran.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.baran.main.Baglantilar;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class OgrtmnDersGirController {

    @FXML
    private JFXTextField txtdersgir;

    @FXML
    private JFXTextField txticerikgir;

    @FXML
    private ListView<String> listvw;
    ObservableList<String>list=FXCollections.observableArrayList();
    Baglantilar iliski=new Baglantilar();
    @FXML
    void icerikEkleBtn(ActionEvent event) {
    	String icerik;
    	icerik=txticerikgir.getText();
    	list.add(icerik);
    	listvw.getItems().add(icerik);
    	txticerikgir.clear();
    }

    @FXML
    void icerikKaydetBtn(ActionEvent event) {
    	int dersno=0;
    	String dersismi;
    	dersismi=txtdersgir.getText();
    	String sorgu="insert into dersler(dersisim,hocaid) values ('"+dersismi+"','"+
    	GirisEkraniController.HocaGirenid+"');";
    	iliski.islemler(sorgu);
    	
    	String sorgu3="select * from dersler where dersisim='"+dersismi+"';";
    	ResultSet rs=(ResultSet) iliski.sorgular(sorgu3);
    	try {
			while(rs.next()) {
				dersno=rs.getInt("dersid");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	for(String gecici:list) {
    		String sorgu2="insert into dersicerik(icerik,dersid) values('"+gecici+"',"+dersno+");";
    		iliski.islemler(sorgu2);
    	}
    	final Node source=(Node) event.getSource();
        final Stage stage=(Stage) source.getScene().getWindow();
        stage.close();
    }

}
