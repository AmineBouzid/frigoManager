package tse.crewmatse.frigomanager.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tse.crewmatse.frigomanager.App;
import tse.crewmatse.frigomanager.userprofile.UserProfile;
import tse.crewmatse.frigomanager.util.DatabaseController;
import tse.crewmatse.frigomanager.util.Recette;
import tse.crewmatse.frigomanager.util.Ingredients;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class HomeController implements Initializable{
	//Attributes List
	@FXML private TextField firstNameTxtField;
	@FXML private TextField lastNameTxtField;
	@FXML private Label labelActiveUser;
	@FXML private Label sysDate;
	@FXML private TableView<Recette> lastViewedRecipestableView;	
	@FXML private TableColumn<Recette,String> lastViewedRecipesColumn;
	@FXML private TableColumn<Ingredients, String> colFood;
	@FXML private TableColumn<Ingredients, String> colExpiration;
	@FXML private TableView<Ingredients> ExpirationTableView;
	
	
	//Getters & Setters List
	/**
	 * @return
	 */
	public TableView<Recette> getLastViewedRecipestableView() {
		return lastViewedRecipestableView;
	}
	/**
	 * @param lastViewedRecipestableView
	 */
	public void setLastViewedRecipestableView(TableView<Recette> lastViewedRecipestableView) {
		this.lastViewedRecipestableView = lastViewedRecipestableView;
	}	
    /**
     * @return
     */
    public TableColumn<Recette, String> getLastViewedRecipesColumn() {
		return lastViewedRecipesColumn;
	}
	/**
	 * @param lastViewedRecipesColumn
	 */
	public void setLastViewedRecipesColumn(TableColumn<Recette, String> lastViewedRecipesColumn) {
		this.lastViewedRecipesColumn = lastViewedRecipesColumn;
	}
	/**
	 * @return
	 */
	public Label getLabelActiveUser() {
		return labelActiveUser;
	}
	/**
	 * @param labelActiveUser
	 */
	public void setLabelActiveUser(Label labelActiveUser) {
		this.labelActiveUser = labelActiveUser;
	}
	/**
	 * @return
	 */
	public Label getSysDate() {
		return sysDate;
	}
	/**
	 * @param sysDate
	 */
	public void setSysDate(Label sysDate) {
		this.sysDate = sysDate;
	}
	/**
	 * @throws IOException
	 */
	@FXML
    private void switchToProfile() throws IOException {
        App.setRoot("profile");
    }
    /**
     * @throws IOException
     */
    @FXML
    private void switchToHome() throws IOException {
        App.setRoot("home");
    }  
    /**
     * @throws IOException
     */
    @FXML
    private void switchToPantry() throws IOException {
        App.setRoot("pantry");
    } 
    /**
     * @throws IOException
     */
    @FXML
    private void switchToRecipe() throws IOException {
        App.setRoot("recipe");
    }  
    /**
     * @throws IOException
     */
    @FXML
    private void switchToAlerts() throws IOException {
        App.setRoot("alerts");
    } 
    /**
     * @return
     */
    public TextField getFirstNameTxtField() {
		return firstNameTxtField;
	}
	/**
	 * @param firstNameTxtField
	 */
	public void setFirstNameTxtField(TextField firstNameTxtField) {
		this.firstNameTxtField = firstNameTxtField;
	}
	/**
	 * @return
	 */
	public TextField getLastNameTxtField() {
		return lastNameTxtField;
	}
	/**
	 * @param lastNameTxtField
	 */
	public void setLastNameTxtField(TextField lastNameTxtField) {
		this.lastNameTxtField = lastNameTxtField;
	}
	/**
	 * @return
	 */
	public TableView getExpirationView() {
		return this.ExpirationTableView;
	}

	
	/**
	 * Initializes the HomePage variables
	 * <br> sysDate : Current System Date
	 * <br> ActiveUser : UserName of the user loaded from the database
	 * <br> LastViewedRecipes : List of names of the 5 last recipes viewed by the user
	 * <br> IngredientsClostoBeExpired : List of the last ingredients input by the user
	 */
	public void initialize(URL location, ResourceBundle resources) {
		LocalDate date = LocalDate.now();	
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		int loadedUser = 0;
		getSysDate().setText(String.valueOf(formatter.format(date)));
		
		lastViewedRecipesColumn.setCellValueFactory(new PropertyValueFactory<Recette,String>("name"));
		
		try {
			CachedRowSet rs = DatabaseController.loadUserWithLoadedState();
			 while (rs.next()) {
				 getLabelActiveUser().setText(rs.getString("Username"));
				 loadedUser = rs.getInt("UserID");
			    }
			 
			 CachedRowSet lastViewedRecipes = DatabaseController.getLastViewedRecipes(loadedUser);
			 if( lastViewedRecipes.next()) {
				 lastViewedRecipes.afterLast();
				 while ( lastViewedRecipes.previous()) {
					 Recette toAdd = new Recette ( lastViewedRecipes.getString("recipeName"), lastViewedRecipes.getInt("recipeId"));
					 getLastViewedRecipestableView().getItems().add(toAdd);
				 } 
				 rs.close();
				 lastViewedRecipes.close();
			 }
			 
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		colFood.setCellValueFactory(new PropertyValueFactory<Ingredients, String>("nameFood"));
		colExpiration.setCellValueFactory(new PropertyValueFactory<Ingredients, String>("date"));

		try {
			ResultSet rs = DatabaseController.getIngredientsClostoBeExpired();
			while (rs.next()) {
				Ingredients toAdd = new Ingredients(rs.getString("apiID"), rs.getString("foodName"),
				rs.getString("quantity"), rs.getString("expirationDate"));
				getExpirationView().getItems().add(toAdd);
			}			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}