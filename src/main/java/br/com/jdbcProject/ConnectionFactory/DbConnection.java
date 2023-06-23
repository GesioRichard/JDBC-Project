package br.com.jdbcProject.ConnectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

	private static final String DRIVE = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/jdbcProject";
	private static final String USER = "gesio";
	private static final String PASSWORD = "gesio1032";

	private static Connection con = null;

	public static Connection startConnection() {

		try {
			Class.forName(DRIVE);

			con = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Connection Error: " + e.getMessage());
			e.printStackTrace();
		}

		return con;
	}

	public static void stopConnection() {
		
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Close connection error: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
}
