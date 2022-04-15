package com.projfinal.conexaoJdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
    private static String url = "jdbc:postgresql://localhost:5432/Estante";
	private static String password = "1313";
	private static String user = "postgres";
	private static Connection connection = null;
	
	public SingleConnection() {
		conectar();
	}
	
	static {
		conectar();
	}
	
	private static void conectar() {
		try {
			if(connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);
				System.out.println("--->>> CONEXÃO ESTABELECIDA <<<---");
						
			}	
			
		}catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public static Connection getConnection() {
		return connection;
		
	}
}
