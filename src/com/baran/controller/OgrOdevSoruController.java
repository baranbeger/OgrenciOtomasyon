package com.baran.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.baran.main.Baglantilar;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class OgrOdevSoruController implements Initializable {

    @FXML
    private JFXComboBox<String> lblsorucmb;
    Baglantilar iliski=new Baglantilar();
  static  int soruno=0;
	String output="";
	static int sinavsoru=0;

    @FXML
    private TextArea txtareaSoru;

    @FXML
    private JFXButton cevaplalbl;

   
    @FXML
    void soruSecCmb(ActionEvent event) {
    	txtareaSoru.clear();
    	output = lblsorucmb.getSelectionModel().getSelectedItem().toString();
    	String sorgu3="select * from sinavsoru where soruisim='"+output+"';";
    	ResultSet rs = iliski.sorgular(sorgu3);
    	try {
			while(rs.next()) {
				String soru=rs.getString("soru");
				sinavsoru=rs.getInt("soruid");
				txtareaSoru.setText(soru);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    @FXML
    void cevaplaBtn(ActionEvent event) {
    	try {
    		Stage stage=new Stage();
			AnchorPane root=(AnchorPane)FXMLLoader.load(getClass().getResource("/com/baran/view/OgrOdevSoruCvp.fxml"));
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	final Node source=(Node) event.getSource();
        final Stage stage=(Stage) source.getScene().getWindow();
        stage.close();
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String sorgu="select *from sinavsoru where dersid in(select dersid from dersler where dersid in("+
				"select dersid from ogrders where ogrno in(select ogrno from ogrenci where ogrno ="+GirisEkraniController.OgrGirenid+")));";
					ResultSet rs=iliski.sorgular(sorgu);
					try {
						while(rs.next()) {
							String soru=rs.getString("soruisim");
							lblsorucmb.getItems().add(soru);
							soruno=rs.getInt("soruid");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		
	}


}