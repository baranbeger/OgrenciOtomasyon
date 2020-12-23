package com.baran.main;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.baran.veritabanibaglantisi.Baglanti;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Baglantilar {
Connection con;
Statement std;
ResultSet rs;
		Baglanti baglan=new Baglanti();
		public Statement islemler(String sorgu) {
			
			try {
				con=baglan.bagla();
				std=(Statement) con.createStatement();
				std.executeUpdate(sorgu);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return std;
		}
		public ResultSet sorgular(String sorgu) {
			con=baglan.bagla();
			try {
				std=(Statement)con.createStatement();
				rs=std.executeQuery(sorgu);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return rs;
		}
}
