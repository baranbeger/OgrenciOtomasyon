package com.baran.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.baran.main.Baglantilar;
import com.baran.view.OgrenciBilgileri;

import com.jfoenix.controls.JFXComboBox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class OgrtmnOgrenciBilgileriController implements Initializable {
	public static int cevapId;

    @FXML
    private JFXComboBox<String> cmbderslbl;

    @FXML
    private JFXComboBox<String> cmbsorulbl;

    @FXML
    private Label ogrNo;

    @FXML
    private TableView<OgrenciBilgileri> tblvw;

    @FXML
    private TableColumn<OgrenciBilgileri, Integer> tblvwno;

    @FXML
    private TableColumn<OgrenciBilgileri, String> tblvwisim;

    @FXML
    private TableColumn<OgrenciBilgileri, String> tblvwsoyisim;

    @FXML
    private TableColumn<OgrenciBilgileri, String> tblvwcevaplar;

    @FXML
    private TableColumn<OgrenciBilgileri, String> tblvwCevaptarih;

    @FXML
    private TableColumn<OgrenciBilgileri, Integer> tblvwnotlar;
    @FXML
    private TableColumn<OgrenciBilgileri, String> tblvwnotlandirildi;
    int dersno=0,soruno=0;
    String output=null;
   
    ObservableList<OgrenciBilgileri> liste=FXCollections.observableArrayList();
    Alert mesaj=new Alert(AlertType.ERROR);
    Baglantilar iliski=new Baglantilar();
    @FXML
    void cmbderssec(ActionEvent event) {
    	output = cmbderslbl.getSelectionModel().getSelectedItem().toString();
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
    	if(dersno!=0) {
    		String sorgu2="select *from sinavsoru where dersid="+dersno+";";
    		ResultSet rss=iliski.sorgular(sorgu2);
    		try {
    			while(rss.next()) {
    				String b=rss.getString("soruisim");
    				cmbsorulbl.getItems().add(b);
    			}
    		} catch (SQLException e) {
    			
    			e.printStackTrace();
    		}
    	}
    	else {
    		System.out.println("hata");
    	}
    }

    @FXML
    void cmbsorusec(ActionEvent event) {
    	String isimler=cmbsorulbl.getSelectionModel().getSelectedItem().toString();
    	String sorgu="select *from sinavsoru where soruisim='"+isimler+"';";
    	ResultSet rs=iliski.sorgular(sorgu);
    	try {
			while(rs.next()) {
				soruno=rs.getInt("soruid");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    @FXML
    void kaydetBtn(ActionEvent event) {
    	final Node source=(Node) event.getSource();
        final Stage stage=(Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void listeleBtn(ActionEvent event) {
    	tblvw.setVisible(true);
    	tblvw.getItems().clear();
    	String sorgu="select *from sinif where sourid="+soruno+";";
    	ResultSet rs=iliski.sorgular(sorgu);
    	System.out.println("dersno="+dersno+"  soruno="+soruno);
    	if(dersno!=0) {
        	try {
    			while(rs.next()) {
    				int a=rs.getInt("ogrno");
    				String d=rs.getString("ogrisim");
    				String e=rs.getString("ogrsoyisim");
    				String j=rs.getString("girildimi");
    				String b=rs.getString("cevaptarih");
    				int c=rs.getInt("notlar");
    				String k=rs.getString("notgirilme");
    				
    				OgrenciBilgileri kk=new OgrenciBilgileri(a, d, e, j, b, c,k);
    				tblvw.getItems().add(kk);
    				if(soruno!=0) {
    					
    				    
    				}
    			}
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}}
        	else {
        		mesaj.setTitle("hata");
    			mesaj.setHeaderText("Seçim Hatasý");
    			mesaj.setContentText("Lütfen ders seçimi yapýnýz");
    			mesaj.show();
        	}    	
    }

    @FXML
    void tblSec(MouseEvent event) {
    	
    	
    	OgrenciBilgileri kk=null;  	
    	kk=tblvw.getSelectionModel().getSelectedItem();
    	if(kk==null) {
    		mesaj.setTitle("hata");
			mesaj.setHeaderText("Seçim Hatasý");
			mesaj.setContentText("Lütfen ogrenci seciniz:");
			mesaj.show();
    		
    	}
    	else {
    		String sorgu="select cevapid from cevaplar where ogrno="+kk.getOGRNO()+" and soruid="+soruno+";";
    		ResultSet rs=iliski.sorgular(sorgu);
    		try {
				while(rs.next()) {
					cevapId=rs.getInt("cevapid");
				}
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
    		
    	try {
    		Stage stage=new Stage();
			AnchorPane root=(AnchorPane)FXMLLoader.load(getClass().getResource("/com/baran/view/OgrtmnCevapNot.fxml"));
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tblvw.setVisible(false);
		String sorgu="select *from dersler where hocaid="+GirisEkraniController.HocaGirenid+";";
		ResultSet rs=iliski.sorgular(sorgu);
		try {
			while(rs.next()) {
				String a=rs.getString("dersisim");
				cmbderslbl.getItems().add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tblvwno.setCellValueFactory(new PropertyValueFactory<>("OGRNO"));
		tblvwisim.setCellValueFactory(new PropertyValueFactory<>("OGRISIM"));
		tblvwsoyisim.setCellValueFactory(new PropertyValueFactory<>("OGRSOYISIM"));
		tblvwcevaplar.setCellValueFactory(new PropertyValueFactory<>("OGRCEVAPLAR"));
		tblvwCevaptarih.setCellValueFactory(new PropertyValueFactory<>("CEVAPTARÝH"));
		tblvwnotlar.setCellValueFactory(new PropertyValueFactory<>("NOTLAR"));
		tblvwnotlandirildi.setCellValueFactory(new PropertyValueFactory<>("NOTGIRILDI"));
		
	}

}
