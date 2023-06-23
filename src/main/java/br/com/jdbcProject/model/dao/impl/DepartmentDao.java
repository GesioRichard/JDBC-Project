package br.com.jdbcProject.model.dao.impl;

import java.util.List;

import br.com.jdbcProject.ConnectionFactory.DbConnection;
import br.com.jdbcProject.model.dao.DaoMethods;
import br.com.jdbcProject.model.entities.Department;

public class DepartmentDao implements DaoMethods<Department> {
	
	private DbConnection db = null;
	
	private DepartmentDao() {
		db = new DbConnection();
	}

	public void insert(Department obj) {
		// TODO Auto-generated method stub
		
	}

	public void update(Department obj) {
		// TODO Auto-generated method stub
		
	}

	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	public Department findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Department> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
