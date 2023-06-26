package br.com.jdbcProject;

import java.util.List;

import br.com.jdbcProject.model.dao.DaoMethods;
import br.com.jdbcProject.model.dao.impl.DepartmentDao;
import br.com.jdbcProject.model.dao.impl.SellerDao;
import br.com.jdbcProject.model.entities.Department;
import br.com.jdbcProject.model.entities.Seller;

public class App 
{
    public static void main( String[] args )
    {
    	DaoMethods<Department> departmentDao = new DepartmentDao();
    	SellerDao sellerDao = new SellerDao();
    	
    	Department d = departmentDao.findById(2);
    	List<Department> departments = departmentDao.findAll();
    	
    	Seller seller = sellerDao.findById(3);
    	List<Seller> sellers = sellerDao.findAll();
    	
    	System.out.println(d);
    	System.out.println(seller);
    	
    	System.out.println(departments);
    	System.out.println(sellers);
    	System.out.println(sellerDao.findByDepartment(d));
    	
    	departmentDao.update(d);
    	departmentDao.deleteById(5);
    }
}
