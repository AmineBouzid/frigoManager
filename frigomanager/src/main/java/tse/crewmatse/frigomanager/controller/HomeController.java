package tse.crewmatse.frigomanager.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
	@FXML private  TextField firstNameTxtField;
	@FXML private  TextField lastNameTxtField;
	@FXML private  Label labelActiveUser;
	@FXML private  TableView<Recette> lastViewedRecipestableView;	
	@FXML private  TableColumn<Recette,String> lastViewedRecipesColumn;
	
	
	public TableView<Recette> getLastViewedRecipestableView() {
		return lastViewedRecipestableView;
	}

	public void setLastViewedRecipestableView(TableView<Recette> lastViewedRecipestableView) {
		this.lastViewedRecipestableView = lastViewedRecipestableView;
	}	
	@FXML
	private TableColumn<Ingredients, String> colFood;
	@FXML
	private TableColumn<Ingredients, String> colExpiration;
	@FXML
	private TableView<Ingredients> ExpirationTableView;
	
    public TableColumn<Recette, String> getLastViewedRecipesColumn() {
		return lastViewedRecipesColumn;
	}

	public void setLastViewedRecipesColumn(TableColumn<Recette, String> lastViewedRecipesColumn) {
		this.lastViewedRecipesColumn = lastViewedRecipesColumn;
	}

	public Label getLabelActiveUser() {
		return labelActiveUser;
	}

	public void setLabelActiveUser(Label labelActiveUser) {
		this.labelActiveUser = labelActiveUser;
	}

	@FXML
    private void switchToProfile() throws IOException {
        App.setRoot("profile");
    }
    
    @FXML
    private void switchToHome() throws IOException {
        App.setRoot("home");
    }
    
    @FXML
    private void switchToPantry() throws IOException {
        App.setRoot("pantry");
    }
    
    @FXML
    private void switchToRecipe() throws IOException {
        App.setRoot("recipe");
    }
    
    @FXML
    private void switchToAlerts() throws IOException {
        App.setRoot("alerts");
    }
    
    
    public TextField getFirstNameTxtField() {
		return firstNameTxtField;
	}

	public void setFirstNameTxtField(TextField firstNameTxtField) {
		this.firstNameTxtField = firstNameTxtField;
	}

	public TextField getLastNameTxtField() {
		return lastNameTxtField;
	}

	public void setLastNameTxtField(TextField lastNameTxtField) {
		this.lastNameTxtField = lastNameTxtField;
	}
	public TableView getExpirationView() {
		return this.ExpirationTableView;
	}
	//@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		//getFirstNameTxtField().setText();
		//getLastNameTxtField().setText("Bouzid");
		int loadedUser = 0;
		
		
		lastViewedRecipesColumn.setCellValueFactory(new PropertyValueFactory<Recette,String>("name"));
		
		try {
			CachedRowSet rs = DatabaseController.loadUserWithLoadedState();
			 while (rs.next()) {
				 getLabelActiveUser().setText(rs.getString("Username"));
				 loadedUser = rs.getInt("UserID");
			    }
			 
			 CachedRowSet lastViewedRecipes = DatabaseController.getLastViewedRecipes(loadedUser);
			 while ( lastViewedRecipes.next()) {
				 Recette toAdd = new Recette ( lastViewedRecipes.getString("recipeName"), lastViewedRecipes.getInt("recipeId"));
				 getLastViewedRecipestableView().getItems().add(toAdd);
			 }
			 
			 rs.close();
			 lastViewedRecipes.close();
			
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		colFood.setCellValueFactory(new PropertyValueFactory<Ingredients, String>("nameFood"));
		colExpiration.setCellValueFactory(new PropertyValueFactory<Ingredients, String>("date"));

		try {

			ResultSet rs = DatabaseController.getIngredientsClostoBeExpired(false);
			while (rs.next()) {
				Ingredients toAdd = new Ingredients(rs.getString("apiID"), rs.getString("foodName"),
						rs.getString("quantity"), rs.getString("expirationDate"));

				getExpirationView().getItems().add(toAdd);

			}
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
    
}