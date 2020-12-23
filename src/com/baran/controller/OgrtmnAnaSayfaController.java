package com.baran.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class OgrtmnAnaSayfaController {

    @FXML
    void ogrtmnCalismaSorular(ActionEvent event) {
    	try {
    		Stage stage=new Stage();
			AnchorPane root=(AnchorPane)FXMLLoader.load(getClass().getResource("/com/baran/view/OgrtmnCalismaSorulari.fxml"));
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void ogrtmnCanliYayinAc(ActionEvent event) {
    	
    }

    @FXML
    void ogrtmnDersAcma(ActionEvent event) {
    	try {
    		Stage stage=new Stage();
			AnchorPane root=(AnchorPane)FXMLLoader.load(getClass().getResource("/com/baran/view/OgrtmnDersGir.fxml"));
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void ogrtmnDersBilgileri(ActionEvent event) {
     	try {
    		Stage stage=new Stage();
			AnchorPane root=(AnchorPane)FXMLLoader.load(getClass().getResource("/com/baran/view/OgrtmnDersBilgileri.fxml"));
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void ogrtmnOdevSorular(ActionEvent event) {
     	try {
    		Stage stage=new Stage();
			AnchorPane root=(AnchorPane)FXMLLoader.load(getClass().getResource("/com/baran/view/OgrtmnOdevSorusu.fxml"));
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void ogrtmnOgrenciBilgileri(ActionEvent event) {
     	try {
    		Stage stage=new Stage();
			AnchorPane root=(AnchorPane)FXMLLoader.load(getClass().getResource("/com/baran/view/OgrtmnOgrenciBilgileri.fxml"));
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void ogrtmnMySql(ActionEvent event) {
     	try {
    		Stage stage=new Stage();
			AnchorPane root=(AnchorPane)FXMLLoader.load(getClass().getResource("/com/baran/view/OgrtmnMySql.fxml"));
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

}
