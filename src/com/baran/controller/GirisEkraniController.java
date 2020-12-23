package com.baran.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.baran.main.Baglantilar;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GirisEkraniController {
	public static int HocaGirenid;
	public static int OgrGirenid;
    @FXML
    private AnchorPane ogretmenGiris;

    @FXML
    private JFXTextField txtogrtmnad;

    @FXML
    private JFXTextField txtogrtmnsifre;

    @FXML
    private AnchorPane OgrGirisSayfa;

    @FXML
    private JFXTextField txtogrkullaniciad;

    @FXML
    private JFXTextField txtogrsifre;
    Alert mesaj=new Alert(AlertType.ERROR);
    Baglantilar iliski=new Baglantilar();
    

    @FXML
    void OgrtmnGirisAcbtn(ActionEvent event) {
    	ogretmenGiris.setVisible(true);
    }

    @FXML
    void ogrGirisbtn(ActionEvent event) {
    	OgrGirisSayfa.setVisible(false);
    	String ograd=txtogrkullaniciad.getText();
    	String sifre=txtogrsifre.getText();
    	String sorgu="select count(ogrno) as giris from ogrenci where kullaniciad='"+ograd+"' and sifre='"+sifre+"';";
    	ResultSet rss;
    	rss=iliski.sorgular(sorgu);
    	try {
			while(rss.next()) {
				if(rss.getInt("giris")==1) {
					try {
			    		Stage stage=new Stage();
						AnchorPane root=(AnchorPane)FXMLLoader.load(getClass().getResource("/com/baran/view/OgrAnaSayfa.fxml"));
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
				else {
					mesaj.setTitle("Hata Mesaji");
					mesaj.setHeaderText("Hatali giris");
					mesaj.setContentText("lütfen doðru dürüst gir");
					mesaj.show();
					txtogrkullaniciad.clear();
					txtogrsifre.clear();
				}
			}
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
    	ResultSet rs;
    	String sorgu2="select * from ogrenci where kullaniciad='"+ograd+"';";
    	rs=iliski.sorgular(sorgu2);
    	try {
			while(rs.next()) {
				int a=rs.getInt("ogrno");
				System.out.println(a);
				OgrGirenid=a;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    @FXML
    void ogrKaydolbtn(ActionEvent event) {
    	try {
    		Stage stage=new Stage();
			AnchorPane root=(AnchorPane)FXMLLoader.load(getClass().getResource("/com/baran/view/OgrKaydol.fxml"));
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void ogrSayfasiniAcbtn(ActionEvent event) {
    	OgrGirisSayfa.setVisible(true);
    }

    @FXML
    void ogrtmnGirisbtn(ActionEvent event) {
    	ogretmenGiris.setVisible(false);
    	String TakmaAd=txtogrtmnad.getText();
    	String Sifre=txtogrtmnsifre.getText();
    	String sorgu="select count(hocaid) as giris from hoca where kullaniciad='"+TakmaAd+"' and sifre='"+Sifre+"';";
    	ResultSet rss;
		rss=iliski.sorgular(sorgu);
		try {
			while(rss.next()) {
				if(rss.getInt("giris")==1) {
					try {
			    		Stage stage=new Stage();
						AnchorPane root=(AnchorPane)FXMLLoader.load(getClass().getResource("/com/baran/view/OgrtmnAnaSayfa.fxml"));
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
				
				else {
					try {
			    		Stage stage=new Stage();
						AnchorPane root=(AnchorPane)FXMLLoader.load(getClass().getResource("/com/baran/view/GirisEkrani.fxml"));
						stage.setScene(new Scene(root));
						stage.show();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					mesaj.setTitle("Hata Mesaji");
					mesaj.setHeaderText("Hatali giris");
					mesaj.setContentText("lütfen doðru dürüst gir");
					mesaj.show();
					txtogrtmnad.clear();
					txtogrtmnsifre.clear();
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResultSet rs;
    	String sorgu2="select * from hoca where kullaniciad='"+TakmaAd+"';";
    	rs=iliski.sorgular(sorgu2);
    	try {
			while(rs.next()) {
				int a=rs.getInt("hocaid");
				HocaGirenid=a;
				System.out.println(HocaGirenid);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    @FXML
    void ogrtmnKaydolbtn(ActionEvent event) {
    	try {
    		Stage stage=new Stage();
			AnchorPane root=(AnchorPane)FXMLLoader.load(getClass().getResource("/com/baran/view/OgrtmnKaydol.fxml"));
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

}
