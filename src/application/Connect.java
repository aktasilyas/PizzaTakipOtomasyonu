package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
static Connection connection=null;
	
	public static Connection Baglan() {
		
		try {
			
			connection=DriverManager.getConnection("jdbc:mysql://localhost/Pizzatakipotomasyonu", "root", "aktas518");
			
			return connection;
			
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
			return null;
		}
		
	}

}
