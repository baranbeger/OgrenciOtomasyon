package com.baran.controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.baran.main.Baglantilar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class OgrtmnCevapNotController implements Initializable {
	Baglantilar iliski=new Baglantilar();

    @FXML
    private ListView<String> lstvwcevap;

    @FXML
    private TextField txtnot;

    @FXML
    private Label lblogrencino;

    @FXML
    void kaydetNotBtn(ActionEvent event) {
    	String not=txtnot.getText();
    	int notlar=Integer.parseInt(not);
    	
    	String sorgu2="insert into notlar(notlar,cevapid) values ("+notlar+","+OgrtmnOgrenciBilgileriController.cevapId+");";

    	iliski.islemler(sorgu2);
    	
    	final Node source=(Node) event.getSource();
        final Stage stage=(Stage) source.getScene().getWindow();
        stage.close();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String sorgu="select *from cevaplar where cevapid="+OgrtmnOgrenciBilgileriController.cevapId+";";
		ResultSet rs=iliski.sorgular(sorgu);
		try {
			while(rs.next()) {
				String cevap=rs.getString("cevap");
				lstvwcevap.getItems().add(cevap);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
    	
		
	}

}
