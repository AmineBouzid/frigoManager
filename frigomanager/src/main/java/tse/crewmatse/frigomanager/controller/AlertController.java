package tse.crewmatse.frigomanager.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.ResourceBundle;

import javax.sql.rowset.CachedRowSet;

import org.json.JSONException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import tse.crewmatse.frigomanager.App;
import tse.crewmatse.frigomanager.api.ApiRecette;
import tse.crewmatse.frigomanager.util.DatabaseController;
import tse.crewmatse.frigomanager.util.Ingredients;
import tse.crewmatse.frigomanager.util.Recette;

/**
 * 
 * @author LEKMAD Mohamed
 * 
 *         This class manage notifications of our application. There are three
 *         types of notifications: 1.Close expiration dates: where we notify the
 *         user if an item is three days away from being expired. //TODO Add the
 *         other notifications
 * 
 *
 */

public class AlertController implements Initializable {

	@FXML
	private TableView<Ingredients> ExpirationTableView;
	@FXML
	private TableView<Ingredients> IngredientsTableView;
	
	@FXML
	private TableColumn<Ingredients, String> colFood;
	@FXML
	private TableColumn<Ingredients, String> colExpiration;
	@FXML
	private TextField foodField;
	@FXML
	private DatePicker datePicker;
	
	@FXML private TableView<Recette> recipeTableView;
	@FXML private TableColumn<Recette, String> colRecipe;
	@FXML private TableColumn<Recette, ArrayList<String>> colIngredients;

	public static Recette selectedRecipe;
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

	public TextField getFoodField() {
		return this.foodField;
	}

	public DatePicker getExpirationDate() {
		return this.datePicker;
	}

	public TableView getExpirationView() {
		return this.ExpirationTableView;
	}
	public TableView<Recette> getRecipeTableView() {
		return recipeTableView;
	}

	public void setRecipeTableView(TableView<Recette> recipeTableView) {
		this.recipeTableView = recipeTableView;
	}

	public TableColumn<Recette, String> getColRecipe() {
		return colRecipe;
	}

	public void setColRecipe(TableColumn<Recette, String> colRecipe) {
		this.colRecipe = colRecipe;
	}

	public TableColumn<Recette, ArrayList<String>> getColIngredients() {
		return colIngredients;
	}

	public void setColIngredients(TableColumn<Recette, ArrayList<String>> colIngredients) {
		this.colIngredients = colIngredients;
	}
	public static Recette getSelectedRecipe() {
		return selectedRecipe;
	}

	public static void setSelectedRecipe(Recette selectedRecipe) {
		AlertController.selectedRecipe = selectedRecipe;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		colFood.setCellValueFactory(new PropertyValueFactory<Ingredients, String>("nameFood"));
		colExpiration.setCellValueFactory(new PropertyValueFactory<Ingredients, String>("date"));
		colRecipe.setCellValueFactory( new PropertyValueFactory<Recette,String>("name"));
		colIngredients.setCellValueFactory( new PropertyValueFactory<Recette,ArrayList<String>>("listIngredient"));
	
		try {

			ResultSet rs = DatabaseController.getIngredientsClostoBeExpired();
			while (rs.next()) {
				Ingredients toAdd = new Ingredients(rs.getString("apiID"), rs.getString("foodName"),
						rs.getString("quantity"), rs.getString("expirationDate"));

				getExpirationView().getItems().add(toAdd);

			}
			rs.close();
			fillRecipeTableView();
			showRecipeDetails();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void fillRecipeTableView() {
		try {
			ArrayList<Recette> recipes = ApiRecette.getRandomRecipes();		
			for (int i = 0;i<recipes.size();i++) {
				getRecipeTableView().getItems().add(recipes.get(i));
			};
		} catch (JSONException | IOException e ) {
			e.printStackTrace();
		}
	}
	public void showRecipeDetails() {
		recipeTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
	    	public void handle(MouseEvent event) {
	    		if (event.getClickCount() == 2) {
	    			selectedRecipe = getRecipeTableView().getSelectionModel().getSelectedItem();
	    			try {
						App.setRoot("recipeDetails");
					} catch (IOException e) {
						e.printStackTrace();
					}
	    			
	    		}
	    	}
		});
	}
	

}
