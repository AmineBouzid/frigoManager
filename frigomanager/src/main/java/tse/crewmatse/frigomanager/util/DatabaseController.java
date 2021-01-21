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

/**
 * This class contains methods to interact with the database
 * @author Guillaume Bayon, Amine Bouzid, Mohamed Lekmad
 *
 */
public class DatabaseController {
	private static Connection connection;
	private static Statement statement;
	
	
	/**
	 * This method is used to get all the ingredients stored in the pantry
	 * @return A CachedRowSet containing the ingredients in the pantry, each ingredient having in this order a field apiID, a field foodName, a field
	 *  expirationDate and a field quantity
	 * @throws SQLException
	 */
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
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
	        if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
	        if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
		}
		return crs;
		
		
	}
	
	/**
	 * Delete a certain ingredient from pantry.db
	 * @param apiID a string that is obtained when selecting a row in the pantry view
	 */
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
	
	/**
	 * Method used to store ingredient one at a time in the table Pantry in the database
	 * @param string The apiID, obtained with a edamam api search
	 * @param foodName The name of the selected food
	 * @param expirationdate The expiration date selected in the calendar
	 * @param quantity The quantity to add in the pantry, with it's unit
	 */
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
			pS.setString(1, string); 
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
	
	/**
	 * Updates the Pantry table if the user wants to insert an ingredient already existing in the pantry
	 * @param apiID The apiID given by edamam api
	 * @param quantity The quantity the user wants to insert in the pantry
	 * @param expirationdate The expiration date, which will be used to update the current expiration date
	 */
	public static void updateItemInTable(String apiID, String quantity,String expirationdate)
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
			PreparedStatement pS = connection.prepareStatement("Update Pantry set quantity = ?, expirationDate= ? WHERE apiID = ? ");
			pS.setQueryTimeout(30);
			
			pS.setString(1, quantity);
			pS.setString(2,expirationdate);
			pS.setString(3, apiID); 
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
	
	/**
	 * Search if an ingredient is already present in the pantry before inserting it
	 * @param apiID The apiID return by edamam api
	 * @return A CachedRowSet, containing 1 to 0 elements, depending if the ingredient is already in the pantry or not
	 * @throws SQLException
	 */
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
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
	        if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
	        if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
		}
		return crs;
	}
	
	/**
	 * Saves the user's info in the database
	 * @param username The name which will be displayed on the home page
	 * @param FirstName User's first name
	 * @param LastName User's last name
	 * @param userGender User's gender
	 * @param Height User's height
	 * @param Weight User's weight
	 * @param HealthyMode User's preference about seeing calories information or not
	 * @param birthDate User's birth date
	 */
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
			pS.setString(3, FirstName); 
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
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
	        if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
	        if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
		}
		
	}
	
	/**
	 * Used to get the usernames to populate the combobox
	 * @return A CachedRowSet containing all the usernames from the table User
	 * @throws SQLException
	 */
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
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
	        if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
	        if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
		}
		return crs;
	}
	
	/**
	 * Used to get all the informations about a certain user
	 * @param username The username of the user we want to get info
	 * @return The selected user's information
	 * @throws SQLException
	 */
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
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
	        if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
	        if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
		}
		return crs;
		
	}
	
	/**
	 * Used to update the informations about a certain user, referenced by his auto generated userID
	 * @param username The username to update
	 * @param FirstName The first name to update
	 * @param LastName The last name to update
	 * @param userGender The gender to update
	 * @param Height The height to update
	 * @param Weight The weight to update
	 * @param HealthyMode The healthy mode to update
	 * @param birthDate The birht date to update
	 * @param userId The userId identifying the user to update
	 */
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
			pS.setString(2, FirstName); 
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
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
	        if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
	        if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
		}
		
	}
	
	/**
	 * Used to change the loaded user
	 * @param userId The id of the user to be loaded
	 */
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
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
	        if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
	        if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
		}
		
	}
	
	/**
	 * This method is used to get the information of the loaded user (the one using the software)
	 * @return A CachedRowSet containing the info of the loaded user
	 * @throws SQLException
	 */
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
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
	        if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
	        if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
		}
		return crs;
		
	}
	/**
	 * This method is used at the startup of the software to load the user 
	 * @return A CachedRowSet containing the last loaded user info
	 * @throws SQLException
	 */
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
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
	        if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
	        if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
		}
		return crs;
		
	}
/**
 * Stores the last recipe viewed by the loaded user on the page recipe
 * @param id The recipe Id
 * @param user The loaded user id
 * @param name The name of the recipe to store
 */
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
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
	        if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
	        if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
	        if (rs!=null) try {rs.close();} catch (SQLException ignore) {}
		}
		
	}
	
	/**
	 * This method returns the last 5 viewed recipes by a certain user
	 * @param user The user id you want to get the last viewed recipes
	 * @return A CachedRowSet containing the last 5 viewed recipes of a certain user
	 * @throws SQLException
	 */
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
			e.printStackTrace();
		}
		//Connection connection = null;
		catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
	        if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
	        if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
	       
		}
		
		return crs;
	}

/**
 * This method is used to check if the ingredients in the pantry are close to their expiration date
 * @return A CachedRowSet containing the information of the close to expire ingredients
 * @throws SQLException
 */
public static CachedRowSet getIngredientsClostoBeExpired() throws SQLException {
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
				rs = statement.executeQuery("select * from Pantry where date('now', '+4 days') > date(\"expirationDate\");");
			
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

/**
 * Stores a recipe in the favorites table to be seen later
 * @param idRecipe The recipe id (given by spoonacular api) of the recipe to save
 * @param user The user id of the user that added this recipe to his favorites
 * @param name The name of the recipe to add to the favorites
 */
public static void addFavouriteRecipe(int idRecipe, int user, String name) {
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
		PreparedStatement pS = connection.prepareStatement("insert into favouriteRecipes(recipeId, user, recipeName) values (?,?,?)");
		pS.setQueryTimeout(30);
		pS.setInt(1,idRecipe);
		pS.setInt(2, user);
		pS.setString(3, name);
		pS.executeUpdate();
		pS.close();
		connection.close();
		statement.close();

		
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	finally {
        if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
        if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        if (rs!=null) try {rs.close();} catch (SQLException ignore) {}
	}
	
}

/**
 * Deletes the selected favorite recipe
 * @param idRecipe The id of the recipe to delete
 * @param user The user id associated with this favorite
 */
public static void deleteFavouriteRecipe(int idRecipe, int user) {
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
		PreparedStatement pS = connection.prepareStatement("delete from favouriteRecipes where recipeId=? AND user=?");
		pS.setQueryTimeout(30);
		pS.setInt(1,idRecipe);
		pS.setInt(2, user);
		pS.executeUpdate();
		pS.close();
		connection.close();
		statement.close();

		
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	finally {
        if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
        if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        if (rs!=null) try {rs.close();} catch (SQLException ignore) {}
	}
	
}

/**
 * Deletes the selected user 
 * @param username The id of the user to delete
 */
public static void deleteUser(String username) {
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
		PreparedStatement pS = connection.prepareStatement("delete from User where Username=?");
		pS.setQueryTimeout(30);
		pS.setString(1,username);
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
        if (rs!=null) try {rs.close();} catch (SQLException ignore) {}
	}
	
}

/**
 * Used to get the favorite recipes of the loaded user
 * @param user The user id of the loaded user
 * @return A CachedRowSet containing the favorites recipes of a certain user
 * @throws SQLException
 */
public static CachedRowSet getFavouriteRecipe(int user) throws SQLException {
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
		PreparedStatement pS = connection.prepareStatement("select * from favouriteRecipes where user = ?");	
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







