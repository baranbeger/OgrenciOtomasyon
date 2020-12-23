package com.baran.controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.baran.main.Baglantilar;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class OgrDersBilgileriController implements Initializable {

    @FXML
    private JFXComboBox<String> lblderscmb;

    @FXML
    private JFXButton iceriklbl;

    @FXML
    private JFXButton Orneklbl;

    @FXML
    private ListView<String> icerilLstvw;

    @FXML
    private JFXListView<Integer> listCozumNo;

    @FXML
    private JFXListView<String> listCozumler;

    @FXML
    private JFXComboBox<String> konusecCmb;
    String output="";
    Baglantilar iliski=new Baglantilar();
    int dersno=0;
    int konuno=0;
    int cozumno=0;

    @FXML
    void dersSecCmb(ActionEvent event) {
    	output = lblderscmb.getSelectionModel().getSelectedItem().toString();
    	String sorgu3="select * from dersler where dersisim='"+output+"';";
    	ResultSet rs = iliski.sorgular(sorgu3);
    	try {
			while(rs.next()) {
				dersno=rs.getInt("dersid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void iceriklerBtn(ActionEvent event) {
    	icerilLstvw.getItems().clear();
    	icerilLstvw.setVisible(true);
    	listCozumNo.setVisible(false);
    	listCozumler.setVisible(false);
    	Orneklbl.setVisible(false);
    	iceriklbl.setVisible(true);
    	String sorgu="select *from dersicerik where dersid="+dersno+";";
    	ResultSet rs=iliski.sorgular(sorgu);
    	try {
			while(rs.next()) {
				String isimler=rs.getString("icerik");
				icerilLstvw.getItems().add(isimler);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void konuActionCmb(ActionEvent event) {
    	listCozumNo.getItems().clear();
    	output=konusecCmb.getSelectionModel().getSelectedItem();
    	String sorgu="select *from konular where konu='"+output+"';";
    	ResultSet rs=iliski.sorgular(sorgu);
    	try {
			while(rs.next()) {
				konuno=rs.getInt("konuid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	String sorgu2="select *from konuornekler where konuid="+konuno+";";
    	ResultSet rss=iliski.sorgular(sorgu2);		
    	try {
			while(rss.next()) {
				int a=rss.getInt("ornekid");
				listCozumNo.getItems().add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    @FXML
    void orneklerBtn(ActionEvent event) {
    	icerilLstvw.setVisible(false);
    	listCozumler.setVisible(true);
    	listCozumNo.setVisible(true);
    	Orneklbl.setVisible(true);
    	iceriklbl.setVisible(false);
    	konusecCmb.setVisible(true);
    	String sorgu="select *from konular where dersid="+dersno+";";
    	ResultSet rs=iliski.sorgular(sorgu);
    	try {
			while(rs.next()) {
				String isimler=rs.getString("konu");
				konusecCmb.getItems().add(isimler);}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void listTikla(MouseEvent event) {
    	listCozumler.getItems().clear();
    	int a=listCozumNo.getSelectionModel().getSelectedItem();
    	String sorgu="select *from konuornekler where ornekid="+a+";";
    	ResultSet rs=iliski.sorgular(sorgu);
    	try {
			while(rs.next()) {
				String isim=rs.getString("ornek");
				listCozumler.getItems().add(isim);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String sorgu="select dersisim from dersler where dersid in"+
                "(select dersid from ogrders where ogrno in"+
			      "(select ogrno from ogrenci where ogrno ="+GirisEkraniController.OgrGirenid+"));";
	ResultSet rs=iliski.sorgular(sorgu);
	try {
		while(rs.next()) {
			String isimler=rs.getString("dersisim");
			lblderscmb.getItems().add(isimler);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
		
	}



}
