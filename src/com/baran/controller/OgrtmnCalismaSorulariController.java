package com.baran.controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.baran.main.Baglantilar;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class OgrtmnCalismaSorulariController implements Initializable {
	Baglantilar iliski=new Baglantilar();
    @FXML
    private JFXComboBox<String> cmbders;

    @FXML
    private JFXTextField txtKonu;

    @FXML
    private JFXTextArea txtarea;

    @FXML
    private JFXButton btnEkle;

    @FXML
    private JFXButton btnCikis;

    @FXML
    private Label lblKonuIsim;

    @FXML
    private JFXButton lblkaydetBtn;
    int dersno=0;
    String output=null;
    Alert mesaj=new Alert(AlertType.ERROR);

    @FXML
    void cikisBtn(ActionEvent event) {
    	final Node source=(Node) event.getSource();
        final Stage stage=(Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void cmbDersSec(ActionEvent event) {
    	output = cmbders.getSelectionModel().getSelectedItem().toString();
    	String sorgu3="select * from dersler where dersisim='"+output+"';";
    	ResultSet rs = iliski.sorgular(sorgu3);
    	try {
			while(rs.next()) {
				dersno=rs.getInt("dersid");
				System.out.println(dersno);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	if(!output.equals("")) {
    		lblkaydetBtn.setVisible(true);
    		txtKonu.setVisible(true);
    	}
    }
    int i=1;
    int ornekid=0;
    String konuIsmi="";
    @FXML
    void konuKaydetBtn(ActionEvent event) {
    	konuIsmi=txtKonu.getText();
        String sorgu="insert into konular(konu,dersid) values ('"+konuIsmi+"',"+dersno+");";
        if(!konuIsmi.equals("")) {
        	iliski.islemler(sorgu);
        	lblKonuIsim.setVisible(true);
        	lblKonuIsim.setText(i+". örneði giriniz");
        	txtarea.setVisible(true);
        	btnEkle.setVisible(true);
        	btnCikis.setVisible(true);
        	lblkaydetBtn.setVisible(false);
        	txtKonu.setVisible(false);
        	txtKonu.clear();
        }
        else {
        	mesaj.setTitle("hata");
			mesaj.setHeaderText("Seçim Hatasý");
			mesaj.setContentText("Lütfen konu ismi giriniz");
			mesaj.show();
        }
    }
    @FXML
    void ekleBtn(ActionEvent event) {
String cozum=txtarea.getText();
    	
    	String sorgu="select *from konular where konu='"+konuIsmi+"';";
    	ResultSet rs=iliski.sorgular(sorgu);
    	try {
			while(rs.next()) {
				ornekid=rs.getInt("konuid");
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
    	String sorgu2="insert into konuornekler(ornek,konuid) values('"+cozum+"',"+ornekid+");";
    	if(!cozum.equals("")) {
    		iliski.islemler(sorgu2);
    		++i;
    		lblKonuIsim.setText(konuIsmi+"  ne "+i+". örneði giriniz  ");
    		txtarea.clear();
    	}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String sorgu="select *from dersler where hocaid="+GirisEkraniController.HocaGirenid+";";
		ResultSet rs=iliski.sorgular(sorgu);
		try {
			while(rs.next()) {
				String isim=rs.getString("dersisim");
				cmbders.getItems().add(isim);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lblKonuIsim.setVisible(false);
		txtarea.setVisible(false);
		btnEkle.setVisible(false);
		btnCikis.setVisible(false);
		txtKonu.setVisible(false);
		lblkaydetBtn.setVisible(false);
		
		
	}


}
