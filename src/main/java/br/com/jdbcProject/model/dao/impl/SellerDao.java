package br.com.jdbcProject.model.dao.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
			db.closeStatement(ps);
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
			db.closeStatement(ps);
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
			db.closeStatement(ps);
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
			
			if(rs.next()) {
				Department department = instanceDepartment(rs);
				Seller seller = instanceSeller(rs, department);
				
				return seller;
			}
			
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			db.closeStatement(ps);
			db.closeResultSet(rs);
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
		
		try {
			ps = db.startConnection().prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			Map<Integer, Department> departments = new HashMap<Integer, Department>();
			List<Seller> sellers = new ArrayList<Seller>();
			
			while(rs.next()) {
				Department dp = departments.get(rs.getInt("DepartmentId"));
				
				if(dp == null) {
					dp = instanceDepartment(rs);
					departments.put(rs.getInt("DepartmentId"), dp);
				}
				
				Seller seller = instanceSeller(rs, dp);
				
				sellers.add(seller);
			}
			
			return sellers;
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			db.closeStatement(ps);
			db.closeResultSet(rs);
		}
		return null;
	}
	
	public List<Seller> findByDepartment(Department department){
		
		String sql = "SELECT s.*, d.Name "
				+ "AS DepartmentName "
				+ "FROM seller s "
				+ "INNER JOIN department d "
				+ "ON s.DepartmentId = d.Id "
				+ "WHERE d.Id = ? ";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = db.startConnection().prepareStatement(sql);
			
			ps.setInt(1, department.getId());
			
			rs = ps.executeQuery();
			
			Map<Integer, Department> departments = new HashMap<Integer, Department>();
			List<Seller> sellers = new ArrayList<Seller>();
			
			while(rs.next()) {
				
				Department dp = departments.get(rs.getInt("DepartmentId"));
				
				if(dp == null) {
					dp = instanceDepartment(rs);
					departments.put(rs.getInt("DepartmentId"), dp);
				}
				
				Seller seller = instanceSeller(rs, dp);
				
				sellers.add(seller);
			}
			
			return sellers;
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			db.closeStatement(ps);
			db.closeResultSet(rs);
		}
		
		
		return null;
	}
	
	private Department instanceDepartment(ResultSet rs) throws SQLException {
		Department department = new Department();
		department.setId(rs.getInt("DepartmentId"));
		department.setName(rs.getString("DepartmentName"));
		
		return department;
	}
	
	private Seller instanceSeller(ResultSet rs, Department d) throws SQLException {
		Seller seller = new Seller();
		seller.setId(rs.getInt("Id"));
		seller.setName(rs.getString("Name"));
		seller.setEmail(rs.getString("Email"));
		seller.setBirthDate(rs.getDate("BirthDate"));
		seller.setBaseSalary(rs.getDouble("BaseSalary"));
		seller.setDepartment(d);
		
		return seller;
	}
}
