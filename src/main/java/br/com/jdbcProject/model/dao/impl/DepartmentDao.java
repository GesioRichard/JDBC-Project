package br.com.jdbcProject.model.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.jdbcProject.ConnectionFactory.DbConnection;
import br.com.jdbcProject.model.dao.DaoMethods;
import br.com.jdbcProject.model.entities.Department;

public class DepartmentDao implements DaoMethods<Department> {
	
	private DbConnection db = null;
	
	public DepartmentDao() {
		db = new DbConnection();
	}

	public void insert(Department obj) {
		
		String sql = "INSERT INTO department (Name) values (?)";
		
		PreparedStatement ps;
		
		try {
			ps = db.startConnection().prepareStatement(sql);
			
			ps.setString(1, obj.getName());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			db.stopConnection();
		}
		
	}

	public void update(Department obj) {
		String sql = "UPDATE department SET Name = ? WHERE Id = ?";
		
		PreparedStatement ps;
		
		try {
			ps = db.startConnection().prepareStatement(sql);
			
			ps.setString(1, obj.getName());
			ps.setInt(2, obj.getId());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			db.stopConnection();
		}
		
	}

	public void deleteById(Integer id) {
		String sql = "DELETE FROM department WHERE Id = ?";
		
		PreparedStatement ps;
		
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

	public Department findById(Integer id) {
		String sql = "SELECT * FROM department WHERE Id = ?";
		
		PreparedStatement ps = null;
		
		try {
			ps = db.startConnection().prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			Department department = null;
			
			if(rs.next()) {
				department = new Department();
				
				department.setId(rs.getInt("Id"));
				department.setName(rs.getString("Name"));

				return department;
			}
			
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			db.stopConnection();
		}
		return null;
	}

	public List<Department> findAll() {
		String sql = "SELECT * FROM department";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Department> departments;
		
		try {
			ps = db.startConnection().prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			departments = new ArrayList<Department>();
			Department dp = null;
			
			while(rs.next()) {
				dp = new Department();
				
				dp.setId(rs.getInt("Id"));
				dp.setName(rs.getString("Name"));
				
				departments.add(dp);
			}
			
			return departments;
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			db.stopConnection();
		}
		
		return null;
	}
	
	
}
