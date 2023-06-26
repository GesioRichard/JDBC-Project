package br.com.jdbcProject.ConnectionFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DbConnection {

	private Connection con = null;

	public Connection startConnection() {
		
		Properties prop = loadProperties();
		
		try {
			Class.forName(prop.getProperty("drive"));

			con = DriverManager.getConnection(prop.getProperty("url"), prop);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Connection Error: " + e.getMessage());
			e.printStackTrace();
		}
		
		return con;
	}

	public void stopConnection() {
		
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Close connection error: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	public void closeStatement(Statement st) {
		if(st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	public void closeResultSet(ResultSet rs) {
		
		if(rs != null) {
			
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	private static Properties loadProperties() {
		try {
			FileInputStream file = new FileInputStream(new File("application.properties"));
			
			Properties prop = new Properties();
			
			prop.load(file);
			
			return prop;
		} catch (FileNotFoundException e) {
			System.out.println("File not found. Error: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return null;
	}
}
