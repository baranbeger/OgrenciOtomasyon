package com.baran.controller;

import java.io.IOException;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class OgrAnaSayfaController {

    @FXML
    void ogrDersBilgileri(ActionEvent event) {
    	try {
    		Stage stage=new Stage();
			AnchorPane root=(AnchorPane)FXMLLoader.load(getClass().getResource("/com/baran/view/OgrDersBilgileri.fxml"));
			stage.setScene(new Scene(root));
			stage.show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    @FXML
    void ogrDersKaydol(ActionEvent event) {
    	try {
    		Stage stage=new Stage();
			AnchorPane root=(AnchorPane)FXMLLoader.load(getClass().getResource("/com/baran/view/OgrDersSec.fxml"));
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void ogrOdevSoru(ActionEvent event) {
    	try {
    		Stage stage=new Stage();
			AnchorPane root=(AnchorPane)FXMLLoader.load(getClass().getResource("/com/baran/view/OgrOdevSoru.fxml"));
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void ogrOnlineIzle(ActionEvent event) {

    }
    @FXML
    void ogrMySql(ActionEvent event) {
    	try {
    		Stage stage=new Stage();
			AnchorPane root=(AnchorPane)FXMLLoader.load(getClass().getResource("/com/baran/view/OgrMySql.fxml"));
			stage.setScene(new Scene(root));
			stage.show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

}
