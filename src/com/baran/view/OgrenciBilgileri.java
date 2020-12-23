package com.baran.view;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class OgrenciBilgileri {
	private final SimpleIntegerProperty OGRNO;
	private final SimpleStringProperty OGRISIM;
	private final SimpleStringProperty OGRSOYISIM;
	private final SimpleStringProperty OGRCEVAPLAR;
	private final SimpleStringProperty CEVAPTAR�H;
	private final SimpleIntegerProperty NOTLAR;
	private final SimpleStringProperty NOTGIRILDI;
	public OgrenciBilgileri(int OGRNO, String OGRISIM, String OGRSOYISIM,
			String OGRCEVAPLAR, String CEVAPTAR�H, int NOTLAR,
			String NOTGIRILDI) {
		
		this.OGRNO=new SimpleIntegerProperty(OGRNO);
		this.NOTLAR=new SimpleIntegerProperty(NOTLAR);
		this.OGRISIM=new SimpleStringProperty(OGRISIM);
		this.OGRSOYISIM=new SimpleStringProperty(OGRSOYISIM);
		this.OGRCEVAPLAR=new SimpleStringProperty(OGRCEVAPLAR);
		this.CEVAPTAR�H=new SimpleStringProperty(CEVAPTAR�H);
		this.NOTGIRILDI=new SimpleStringProperty(NOTGIRILDI);
	}
	
	public int getOGRNO() {
		return OGRNO.get();
	}
	public String getOGRISIM() {
		return OGRISIM.get();
	}
	public String getOGRSOYISIM() {
		return OGRSOYISIM.get();
	}
	public String getOGRCEVAPLAR() {
		return OGRCEVAPLAR.get();
	}
	public String getCEVAPTAR�H() {
		return CEVAPTAR�H.get();
	}
	public int getNOTLAR() {
		return NOTLAR.get();
	}
	public String getNOTGIRILDI() {
		return NOTGIRILDI.get();
	}
	
	
	
}
