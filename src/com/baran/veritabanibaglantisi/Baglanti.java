package com.baran.veritabanibaglantisi;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class Baglanti {
	Connection con;
	public Connection bagla() {
		try {
			con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/otomasyoniki"+"?useSSL=false","root","123456789.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
}
