package br.com.jdbcProject.model.dao.impl;

import java.util.List;

import br.com.jdbcProject.ConnectionFactory.DbConnection;
import br.com.jdbcProject.model.dao.DaoMethods;
import br.com.jdbcProject.model.entities.Seller;

public class SellerDao implements DaoMethods<Seller> {
	
	private DbConnection db = null;
	
	private SellerDao(){
		db = new DbConnection();
	}

	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	public Seller findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
