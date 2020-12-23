package com.baran.controller;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.baran.main.Baglantilar;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;
import javafx.scene.control.TableView;
public class OgrMySqlController implements Initializable {
	
    @FXML
    private JFXTextArea txtKod;
    Baglantilar iliski=new Baglantilar();
    Alert mesaj=new Alert(AlertType.ERROR);

    @FXML
    private JFXButton tamam;

    @FXML
    private TableView tblvw;
    ObservableList<ObservableList> data=FXCollections.observableArrayList();
    @FXML
    void tamamBtn(ActionEvent event) {
    	
    	String sorgu=txtKod.getText();
    	boolean goruntuVarmi=sorgu.contains("goruntu");
    	boolean selectVarmi=sorgu.contains("select");
    	
    		if(selectVarmi==true && goruntuVarmi==true) {
    			tamam.setVisible(false);
    			txtKod.setVisible(false);
    			tblvw.setVisible(true);
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
    		else {
    			mesaj.setTitle("Hata");
    			mesaj.setHeaderText("Islem Hatasi");
    			mesaj.setContentText("yaptiginiz sorguyu lutfen dogru giriniz");
    			mesaj.show();
    		}
    	
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tamam.setVisible(true);
		txtKod.setVisible(true);
		tblvw.setVisible(false);
		
	}

}
