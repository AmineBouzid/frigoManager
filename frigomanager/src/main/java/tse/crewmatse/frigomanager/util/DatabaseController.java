package tse.crewmatse.frigomanager.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DatabaseController {
	private static Connection connection;
	private static Statement statement;
	
	
	public static ResultSet selectAllRows() {
		ResultSet rs = null;
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:pantry.db");
		    statement = connection.createStatement();
			statement.setQueryTimeout(30);
			 rs = statement.executeQuery("select * from Pantry");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Connection connection = null;
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return rs;
		
	}
	
	public static void deleteSelectedRow(String apiID) {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:pantry.db");
			Statement statementa = connection.createStatement();
			statementa.setQueryTimeout(30);
			
			PreparedStatement pS = connection.prepareStatement("delete from Pantry where apiID = ?");
			pS.setString(1, apiID);
			
			pS.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(connection != null)
					connection.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}
	
	public static void addItemInTable(String string, String foodName, String expirationdate, String quantity) {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:pantry.db");
			PreparedStatement pS = connection.prepareStatement("insert into Pantry values (?,?,?,?)");
			pS.setQueryTimeout(30);
			pS.setString(1, string); // HERE  replace 12 with the id returned by a http request to api
			pS.setString(2, foodName); 
			pS.setString(3,expirationdate);
			pS.setString(4, quantity);
			
			pS.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(connection != null)
					connection.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}
	
	
}
