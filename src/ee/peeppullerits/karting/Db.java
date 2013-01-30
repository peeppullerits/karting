package ee.peeppullerits.karting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Db {
	private static Connection conn;

	public static void connect() {
        try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.
		            getConnection("jdbc:h2:karting", "sa", "");
		        // add application code here
		        
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	public static void createTables() {
		query("CREATE TABLE IF NOT EXISTS lap_times (track INT, time DECIMAL)");
		query("CREATE TABLE IF NOT EXISTS launches "
		+ " (dt DATETIME NOT NULL)");
	}
	
	public static void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	public static void query(String sql) {
		Statement stmt;
		try {
			stmt = conn.createStatement();
			stmt.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void addLaunchTime() {
		query("INSERT INTO launches VALUES (NOW())");
		
	}
	
}
