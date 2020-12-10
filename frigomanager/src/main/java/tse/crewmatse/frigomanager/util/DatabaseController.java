package tse.crewmatse.frigomanager.util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.sql.ResultSet;

public class DatabaseController {
	private static Connection connection;
	private static Statement statement;
	
	
	public static ResultSet selectAllRows() {
		ResultSet rs = null;
		try {
			try {
				if(connection != null)
					connection.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
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
			try {
				if(connection != null)
					connection.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
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
		}
	}
	
	public static void addItemInTable(String string, String foodName, String expirationdate, String quantity) {
		try {
			try {
				if(connection != null)
					connection.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
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
		}
	}
	
	public static void saveUserInfo(String username ,String FirstName, String LastName,String userGender, Double Height, Double Weight, Boolean HealthyMode, Date birthDate ) {
		try {
			try {
				if(connection != null)
					connection.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:pantry.db");
			PreparedStatement pS = connection.prepareStatement("insert into User values (?,?,?,?,?,?,?,?,?,?)");
			pS.setQueryTimeout(30);
			pS.setNull(1,Types.INTEGER);
			pS.setString(2, username);
			pS.setString(3, FirstName); // HERE  replace 12 with the id returned by a http request to api
			pS.setString(4, LastName); 
			pS.setString(5, userGender);
			pS.setDouble(6,Height);
			pS.setDouble(7,Weight);
			pS.setBoolean(8, HealthyMode);
			pS.setDate(9, birthDate);
			pS.setBoolean(10, true);
			pS.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static ResultSet getUserProfile() {
		ResultSet rs = null;
		try {
			try {
				if(connection != null)
					connection.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:pantry.db");
		    statement = connection.createStatement();
			statement.setQueryTimeout(30);
			rs = statement.executeQuery("select username from User");
			
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
	
	public static ResultSet getUserInfo(String username) {
		ResultSet rs = null;
		try {
			try {
				if(connection != null)
					connection.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:pantry.db");
			Statement statementa = connection.createStatement();
			statementa.setQueryTimeout(30);
			PreparedStatement pS = connection.prepareStatement("select * from User where username = ?");
			pS.setString(1, username);
			rs = pS.executeQuery();
			
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
	
	public static void modifUserInfo(String username ,String FirstName, String LastName,String userGender, Double Height, Double Weight, Boolean HealthyMode, Date birthDate, Integer userId ) {
		try {
			try {
				if(connection != null)
					connection.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:pantry.db");
			Statement statementa = connection.createStatement();
			statementa.setQueryTimeout(30);
			PreparedStatement pS = connection.prepareStatement("update User set username=?, FirstName=?, LastName=?, Gender=?,Height = ?, Weight=?, HealthyMode=?,BirthDate=? where UserID=?  ");
			pS.setQueryTimeout(30);
			
			pS.setString(1, username);
			pS.setString(2, FirstName); // HERE  replace 12 with the id returned by a http request to api
			pS.setString(3, LastName); 
			pS.setString(4, userGender);
			pS.setDouble(5,Height);
			pS.setDouble(6,Weight);
			pS.setBoolean(7, HealthyMode);
			pS.setDate(8, birthDate);
			pS.setInt(9, userId);
			pS.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void updateLoadedState(Integer userId   ) {
		try {
			try {
				if(connection != null)
					connection.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:pantry.db");
			Statement statementa = connection.createStatement();
			statementa.setQueryTimeout(30);
			PreparedStatement pS = connection.prepareStatement("update User set ProfileLoaded=1 where UserID=?");
			pS.setQueryTimeout(30);
			pS.setInt(1, userId);
			pS.executeUpdate();
			Statement statementa2 = connection.createStatement();
			statementa2.setQueryTimeout(30);
			PreparedStatement pS2 = connection.prepareStatement("update User set ProfileLoaded=0 where NOT UserID=?");
			pS2.setQueryTimeout(30);
			pS2.setInt(1, userId);
			pS2.executeUpdate();
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static ResultSet loadUserWithLoadedState() {
		ResultSet rs = null;
		try {
			try {
				if(connection != null)
					connection.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:pantry.db");
		    statement = connection.createStatement();
			statement.setQueryTimeout(30);
			rs = statement.executeQuery("select username from User where ProfileLoaded=1");
			
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
	public static ResultSet loadUserWithLoadedStateForInitialize() {
		ResultSet rs = null;
		try {
			try {
				if(connection != null)
					connection.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:pantry.db");
		    statement = connection.createStatement();
			statement.setQueryTimeout(30);
			rs = statement.executeQuery("select * from User where ProfileLoaded=1");
			
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
}





