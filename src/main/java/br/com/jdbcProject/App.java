package br.com.jdbcProject;

import br.com.jdbcProject.ConnectionFactory.DbConnection;

public class App 
{
    public static void main( String[] args )
    {
    	
    	DbConnection.startConnection();
    	DbConnection.stopConnection();
    }
}
