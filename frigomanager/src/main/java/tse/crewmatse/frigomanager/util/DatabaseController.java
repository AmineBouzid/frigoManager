package tse.crewmatse.frigomanager.util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

import java.sql.ResultSet;

public class DatabaseController {
	private static Connection connection;
	private static Statement statement;
	
	
	public static CachedRowSet selectAllRows() throws SQLException {
		ResultSet rs = null;
		CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();
		
		try {
			try {
				if(connection != null)
					connection.close();
				if(statement != null)
					statement.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:pantry.db");
		    statement = connection.createStatement();
			statement.setQueryTimeout(30);
			 rs = statement.executeQuery("select * from Pantry");
			 crs.populate(rs);
			 rs.close();
			connection.close();
			statement.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Connection connection = null;
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally {
			if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
	        if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
	        if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
		}
		return crs;
		
		
	}
	
	public static void deleteSelectedRow(String apiID) {
		try {
			try {
				if(connection != null)
					connection.close();
				if(statement != null)
					statement.close();
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
			pS.close();
			connection.close();
			statementa.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
	        if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
	        if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
		}
	}
	
	public static void addItemInTable(String string, String foodName, String expirationdate, String quantity) {
		try {
			try {
				if(connection != null)
					connection.close();
				if(statement != null)
					statement.close();
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
			pS.close();
			connection.close();
			statement.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
	        if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
	        if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
		}
	}
	
	public static void updateItemInTable(String apiID, String quantity)
	{
		try {
			try {
				if(connection != null)
					connection.close();
				if(statement != null)
					statement.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:pantry.db");
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			PreparedStatement pS = connection.prepareStatement("Update Pantry set quantity = ? WHERE apiID = ? ");
			pS.setQueryTimeout(30);
			
			pS.setString(1, quantity);
			pS.setString(2, apiID); // HERE  replace 12 with the id returned by a http request to api
			pS.executeUpdate();
			pS.close();
			connection.close();
			statement.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
	        if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
	        if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
		}
	}
	
	public static CachedRowSet checkIfItemExists(String apiID) throws SQLException {
		ResultSet rs = null;
		CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();
		try {
			try {
				if(connection != null)
					connection.close();
				if(statement != null)
					statement.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:pantry.db");
		    statement = connection.createStatement();
			statement.setQueryTimeout(30);
			PreparedStatement pS = connection.prepareStatement("select * from Pantry where apiID = ?");
			pS.setString(1, apiID);
			rs = pS.executeQuery();
			crs.populate(rs);
			pS.close();
			connection.close();
			statement.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Connection connection = null;
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally {
			if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
	        if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
	        if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
		}
		return crs;
	}
	
	public static void saveUserInfo(String username ,String FirstName, String LastName,String userGender, Double Height, Double Weight, Boolean HealthyMode, String birthDate ) {
		try {
			try {
				if(connection != null)
					connection.close();
				if(statement != null)
					statement.close();
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
			pS.setString(9, birthDate);
			pS.setBoolean(10, true);
			pS.executeUpdate();
			pS.close();
			connection.close();
			statement.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
	        if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
	        if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
		}
		
	}
	
	public static CachedRowSet getUserProfile() throws SQLException {
		ResultSet rs = null;
		CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();
		try {
			try {
				if(connection != null)
					connection.close();
				if(statement != null)
					statement.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:pantry.db");
		    statement = connection.createStatement();
			statement.setQueryTimeout(30);
			rs = statement.executeQuery("select username from User");
			crs.populate(rs);
			rs.close();
			connection.close();
			statement.close();

			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Connection connection = null;
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally {
			if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
	        if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
	        if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
		}
		return crs;
	}
	
	public static CachedRowSet getUserInfo(String username) throws SQLException {
		ResultSet rs = null;
		CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();
		try {
			try {
				if(connection != null)
					connection.close();
				if(statement != null)
					statement.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:pantry.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			PreparedStatement pS = connection.prepareStatement("select * from User where username = ?");
			pS.setString(1, username);
			rs = pS.executeQuery();
			crs.populate(rs);
			pS.close();
			rs.close();
			connection.close();
			statement.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Connection connection = null;
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally {
			if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
	        if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
	        if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
		}
		return crs;
		
	}
	
	public static void modifUserInfo(String username ,String FirstName, String LastName,String userGender, Double Height, Double Weight, Boolean HealthyMode, String birthDate, Integer userId ) {
		try {
			try {
				if(connection != null)
					connection.close();
				if(statement != null)
					statement.close();
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
			pS.setString(8, birthDate);
			pS.setInt(9, userId);
			pS.executeUpdate();
			pS.close();
			connection.close();
			statement.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
	        if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
	        if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
		}
		
	}
	
	public static void updateLoadedState(Integer userId   ) {
		try {
			try {
				if(connection != null)
					connection.close();
				if(statement != null)
					statement.close();
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
			pS.close();
			pS2.close();
			connection.close();
			statementa.close();
			statementa2.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
	        if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
	        if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
		}
		
	}
	
	public static CachedRowSet loadUserWithLoadedState() throws SQLException {
		ResultSet rs = null;
		CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();
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
			crs.populate(rs);
			rs.close();
			connection.close();
			statement.close();

			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Connection connection = null;
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally {
			if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
	        if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
	        if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
		}
		return crs;
		
	}
	public static CachedRowSet loadUserWithLoadedStateForInitialize() throws SQLException {
		ResultSet rs = null;
		CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();
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
			crs.populate(rs);
			rs.close();
			connection.close();
			statement.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Connection connection = null;
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally {
			if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
	        if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
	        if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
		}
		return crs;
		
	}
	
	public static void storeLastViewedRecipe(int id, int user, String name) {
		ResultSet rs = null;
		try {
			try {
				if(connection != null)
					connection.close();
				if(statement != null)
					statement.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:pantry.db");
			PreparedStatement pS = connection.prepareStatement("select count(*) from lastRecipes where user = ?");
			pS.setQueryTimeout(30);
			pS.setInt(1, user);
			rs = pS.executeQuery();
			rs.next();				
			
			if ( rs.getInt("count(*)") == 5 ) {
				PreparedStatement delPS = connection.prepareStatement("DELETE FROM lastRecipes WHERE pKey = ( SELECT MIN(pKey) from lastRecipes WHERE user = ?)");
				delPS.setQueryTimeout(30);
				delPS.setInt(1,user);
				delPS.execute();
			
				PreparedStatement insertPS = connection.prepareStatement("insert into lastRecipes(recipeId, user, recipeName) values (?,?,?)");
				insertPS.setQueryTimeout(30);
				insertPS.setInt(1, id);
				insertPS.setInt(2, user);
				insertPS.setString(3, name);
				insertPS.executeUpdate();
				insertPS.close();
				delPS.close();
				
			}
			else {
				PreparedStatement insertPS = connection.prepareStatement("insert into lastRecipes(recipeId, user, recipeName) values (?,?,?)");
				insertPS.setQueryTimeout(30);
				insertPS.setInt(1, id);
				insertPS.setInt(2, user);
				insertPS.setString(3, name);
				insertPS.executeUpdate();
				insertPS.close();
			}
			pS.close();
			connection.close();
			statement.close();
			rs.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
	        if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
	        if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
	        if (rs!=null) try {rs.close();} catch (SQLException ignore) {}
		}
		
	}
	
	public static CachedRowSet getLastViewedRecipes(int user) throws SQLException {
		CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();
		ResultSet rs = null;
		try {
			try {
				if(connection != null)
					connection.close();
				if(statement != null)
					statement.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:pantry.db");
			PreparedStatement pS = connection.prepareStatement("select * from lastRecipes where user = ?");	
			pS.setQueryTimeout(30);
			pS.setInt(1, user);
			rs = pS.executeQuery();
			crs.populate(rs);
			rs.close();
			connection.close();
			statement.close();
			pS.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Connection connection = null;
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally {
			if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
	        if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
	        if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
	       
		}
		
		return crs;
	}
}





