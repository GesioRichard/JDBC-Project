package br.com.jdbcProject;

import java.util.List;

import br.com.jdbcProject.model.dao.DaoMethods;
import br.com.jdbcProject.model.dao.impl.DepartmentDao;
import br.com.jdbcProject.model.entities.Department;

public class App 
{
    public static void main( String[] args )
    {
    	DaoMethods<Department> dao = new DepartmentDao();
    	
    	Department d = dao.findById(3);
    	List<Department> departments = dao.findAll();
    	
    	System.out.println(d);
    	
    	System.out.println(departments);
    	//dao.insert(d);
    	//dao.update(d);
    }
}
