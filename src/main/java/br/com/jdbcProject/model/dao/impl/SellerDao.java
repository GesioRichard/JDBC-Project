package br.com.jdbcProject.model.dao.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.jdbcProject.ConnectionFactory.DbConnection;
import br.com.jdbcProject.model.dao.DaoMethods;
import br.com.jdbcProject.model.entities.Department;
import br.com.jdbcProject.model.entities.Seller;

public class SellerDao implements DaoMethods<Seller> {
	
	private DbConnection db = null;
	
	public SellerDao(){
		db = new DbConnection();
	}

	//detail use java.sql.Date for dates when entering any data 
	//and use method getDate to return a date from package java.util.Date
	
	public void insert(Seller obj) {
		String sql = "INSERT INTO seller "
				+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
				+ "VALUES "
				+ "(?, ?, ?, ?, ?)";
		
		PreparedStatement ps = null;
		
		try {
			ps = db.startConnection().prepareStatement(sql);
			
			ps.setString(1, obj.getName());
			ps.setString(2, obj.getEmail());
			ps.setDate(3, new Date(obj.getBirthDate().getTime()));
			ps.setDouble(4, obj.getBaseSalary());
			ps.setInt(5, obj.getDepartment().getId());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			db.stopConnection();
		}
		
		
	}

	public void update(Seller obj) {
		String sql = "UPDATE seller SET "
				+ "Name = ? , "
				+ "Email = ? , "
				+ "BirthDate = ? ,"
				+ "BaseSalary = ? ,"
				+ "DepartmentId = ? "
				+ "WHERE Id = ?";
		
		PreparedStatement ps = null;
		
		try {
			ps = db.startConnection().prepareStatement(sql);
			
			ps.setString(1, obj.getName());
			ps.setString(2, obj.getEmail());
			ps.setDate(3, new Date(obj.getBirthDate().getTime()));
			ps.setDouble(4, obj.getBaseSalary());
			ps.setInt(5, obj.getDepartment().getId());
			
			ps.setInt(6, obj.getId());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			db.stopConnection();
		}
		
	}

	public void deleteById(Integer id) {
		String sql = " DELETE FROM seller WHERE Id = ?";
		
		PreparedStatement ps = null;
		
		try {
			ps = db.startConnection().prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			db.stopConnection();
		}
		
	}

	public Seller findById(Integer id) {
		String sql = "SELECT s.* , d.Name "
				+ "AS DepartmentName "
				+ "FROM seller s "
				+ "INNER JOIN department d "
				+ "ON s.DepartmentId = d.Id "
				+ "WHERE s.Id = ?";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = db.startConnection().prepareStatement(sql);
			
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			Seller seller = null;
			
			if(rs.next()) {
				seller = new Seller();
				
				seller.setId(rs.getInt("Id"));
				seller.setName(rs.getString("Name"));
				seller.setEmail(rs.getString("Email"));
				seller.setBirthDate(rs.getDate("BirthDate"));
				seller.setBaseSalary(rs.getDouble("BaseSalary"));
				seller.setDepartment(new Department(rs.getInt("DepartmentId"), rs.getString("DepartmentName")));
			}
			
			return seller;
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			db.stopConnection();
		}
		
		return null;
	}

	public List<Seller> findAll() {
		String sql = "SELECT s.* , d.Name "
				+ "AS DepartmentName "
				+ "FROM seller s "
				+ "INNER JOIN department d "
				+ "ON s.DepartmentId = d.Id";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Seller> sellers;
		
		try {
			ps = db.startConnection().prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			sellers = new ArrayList<Seller>();
			Seller seller = null;
			
			while(rs.next()) {
				seller = new Seller();
				
				seller.setId(rs.getInt("Id"));
				seller.setName(rs.getString("Name"));
				seller.setEmail(rs.getString("Email"));
				seller.setBirthDate(rs.getDate("BirthDate"));
				seller.setBaseSalary(rs.getDouble("BaseSalary"));
				seller.setDepartment(new Department(rs.getInt("DepartmentId"), rs.getString("DepartmentName")));
				
				sellers.add(seller);
			}
			
			return sellers;
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			db.stopConnection();
		}
		return null;
	}
	
	
}
