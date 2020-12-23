package com.baran.controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.baran.main.Baglantilar;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;



public class OgrtmnMySqlController implements Initializable {
	
    @FXML
    private JFXTextArea sqlSorguYaz;
    @FXML
    private JFXButton tamambtn;
    @FXML
    private TableView tblvw;   
    @FXML
    private Label lbltblid;

    @FXML
    private JFXTextField txtBaslik;
    @FXML
    private JFXButton btnid;

    @FXML
    private JFXTextArea txtareadegiskenler;
    Baglantilar iliski=new Baglantilar();
    ObservableList<ObservableList> data=FXCollections.observableArrayList();
    Alert mesaj=new Alert(AlertType.INFORMATION);
  
    @FXML
    void tamamBtnAction(ActionEvent event) {
    	String sorgu=sqlSorguYaz.getText().toLowerCase();
    	boolean selectVarmi=sorgu.contains("select");
    	boolean createVarmi=sorgu.contains("create");	
    	if(selectVarmi==true) {
    		tblvw.setVisible(true);
    		sqlSorguYaz.setVisible(false);
    		tamambtn.setVisible(false);
    		ResultSet rs=iliski.sorgular(sorgu);
    		try {
				for(int i=0;i<rs.getMetaData().getColumnCount();i++) {
					final int j=i;
					TableColumn col=new TableColumn(rs.getMetaData().getColumnName(i+1));
					col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {

						@Override
						public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
							return new SimpleStringProperty(param.getValue().get(j).toString());
						}
					});
					tblvw.getColumns().add(col);
					System.out.println("Column["+i+"] ");}
				while(rs.next()) {
	    			ObservableList<String> row=FXCollections.observableArrayList();
	    			for(int i=1;i<rs.getMetaData().getColumnCount()+1;i++) {
	    				row.add(rs.getString(i));
	    				
	    			}
	    			System.out.println("Row [1] adet "+row);
	    			data.add(row);
	    		}
					
				}
			 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
    	}
    		tblvw.setItems(data);
    		}
    	else if(createVarmi==true) {
    		mesaj.setTitle("Bilgi");
			mesaj.setHeaderText("Islem Bilgisi");
			mesaj.setContentText("create iþlemi için butonu týklayýnýz");
			mesaj.show();
    	}
    	else {
    		iliski.islemler(sorgu);
    		mesaj.setTitle("Bilgi");
			mesaj.setHeaderText("Islem Bilgisi");
			mesaj.setContentText("Yaptiginiz islem basarili");
			mesaj.show();
			sqlSorguYaz.clear();
    		
    	}
    	
    
    }

    @FXML
    void lbltbl(MouseEvent event) {
    	txtareadegiskenler.setVisible(true);
		txtBaslik.setVisible(true);
		tblvw.setVisible(false);
		sqlSorguYaz.setVisible(false);
		tamambtn.setVisible(false);
		btnid.setVisible(true);
		lbltblid.setVisible(false);
    }
    @FXML
    void tabloYaratBtn(ActionEvent event) {
    	String baslil=txtBaslik.getText();
    	String degiskenler=txtareadegiskenler.getText();
    	if(baslil!= null && degiskenler!= null) {
    	String sorgu="create table "+baslil+"( "+degiskenler+");";
    	iliski.islemler(sorgu);
    	String viewBaslik="goruntu"+baslil;
    	String sorgu2="create view "+viewBaslik+" as select *from "+baslil+";";
    	iliski.islemler(sorgu2);
    	mesaj.setTitle("Bilgi");
		mesaj.setHeaderText("Islem Bilgisi");
		mesaj.setContentText("Ogrencinin kullanabilecegi tablo adi: "+viewBaslik);
		mesaj.show();
    	
    	
    	}
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tblvw.setVisible(false);
		sqlSorguYaz.setVisible(true);
		tamambtn.setVisible(true);
		txtareadegiskenler.setVisible(false);
		txtBaslik.setVisible(false);
		
	
	}

}
