package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private String url;
	private String user;
	private String password;
	
	public Connection getConnection() {
		url = "jdbc:postgresql://localhost:5432/db-contabancaria";
		user = "postgres";
		password = "0000";
		
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
