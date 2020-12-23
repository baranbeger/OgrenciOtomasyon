package com.baran.view;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class OgrDataIki {
	private final SimpleIntegerProperty NO;
	private final SimpleStringProperty ISIM;
	private final SimpleStringProperty SOYISIM;
	
	public OgrDataIki(int NO,String ISIM,String SOYISIM) {
		this.NO=new SimpleIntegerProperty(NO);
		this.ISIM=new SimpleStringProperty(ISIM);
		this.SOYISIM=new SimpleStringProperty(SOYISIM);
	}

	public int getNO() {
		return NO.get();
	}

	public String getISIM() {
		return ISIM.get();
	}

	public String getSOYISIM() {
		return SOYISIM.get();
	}
}
