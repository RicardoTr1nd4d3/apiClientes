package br.com.cotiinformatica.factories;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	private static String host = "jdbc:mysql://localhost:3306/bd_apiclientes";
	private static String user = "root";
	private static String pass = "coti";
	
	public static Connection getConnection() throws Exception {
		return DriverManager.getConnection(host, user, pass);	
	}

}
