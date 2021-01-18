package tse.crewmatse.frigomanager.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.sql.rowset.CachedRowSet;

import org.json.JSONException;



import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import tse.crewmatse.frigomanager.App;
import tse.crewmatse.frigomanager.util.DatabaseController;
import tse.crewmatse.frigomanager.util.Ingredients;
import tse.crewmatse.frigomanager.util.Recette;
import tse.crewmatse.frigomanager.api.ApiRecette;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import tse.crewmatse.api.ApiIngredients;

public class RecipeController implements Initializable{
	@FXML private SplitPane recipeSplitPane;
	@FXML private TableView<Recette> recipeTableView;
	@FXML private TableColumn<Recette, String> colRecipe;
	@FXML private TableColumn<Recette,TableView<Recette>> colIngredients;
	@FXML private TableColumn<Recette, ArrayList<String>> colUsed;
	@FXML private TableColumn<Recette, ArrayList<String>> colMissed;
	@FXML private TableView<Ingredients> pantryTableView;
	@FXML private TableColumn<Ingredients, String> colFood;
	@FXML private TableColumn<Ingredients, String> colExpiration;
	@FXML private TableColumn<Ingredients, String> colQuantity;
	@FXML private TableView<Ingredients> selectedTableView;
	@FXML private TableColumn<Ingredients, String> colIngredient;
	@FXML private Button addButton;
	@FXML private Button deleteButton;
	@FXML private Button searchButton;
	
	public static Recette selectedRecipe;
	
	public static Recette getSelectedRecipe() {
		return selectedRecipe;
	}

	

	public SplitPane getRecipeSplitPane() {
		return recipeSplitPane;
	}

	public void setRecipeSplitPane(SplitPane recipeSplitPane) {
		this.recipeSplitPane = recipeSplitPane;
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

	public TableColumn<Recette, TableView<Recette>> getColIngredients() {
		return colIngredients;
	}

	public void setColIngredients(TableColumn<Recette, TableView<Recette>> colIngredients) {
		this.colIngredients = colIngredients;
	}

	public TableColumn<Recette, ArrayList<String>> getColUsed() {
		return colUsed;
	}

	public void setColUsed(TableColumn<Recette, ArrayList<String>> colUsed) {
		this.colUsed = colUsed;
	}

	public TableColumn<Recette, ArrayList<String>> getColMissed() {
		return colMissed;
	}

	public void setColMissed(TableColumn<Recette, ArrayList<String>> colMissed) {
		this.colMissed = colMissed;
	}

	public TableView<Ingredients> getPantryTableView() {
		return pantryTableView;
	}

	public void setPantryTableView(TableView<Ingredients> pantryTableView) {
		this.pantryTableView = pantryTableView;
	}

	public TableColumn<Ingredients, String> getColFood() {
		return colFood;
	}

	public void setColFood(TableColumn<Ingredients, String> colFood) {
		this.colFood = colFood;
	}

	public TableColumn<Ingredients, String> getColExpiration() {
		return colExpiration;
	}

	public void setColExpiration(TableColumn<Ingredients, String> colExpiration) {
		this.colExpiration = colExpiration;
	}

	public TableColumn<Ingredients, String> getColQuantity() {
		return colQuantity;
	}

	public void setColQuantity(TableColumn<Ingredients, String> colQuantity) {
		this.colQuantity = colQuantity;
	}

	public TableView<Ingredients> getSelectedTableView() {
		return selectedTableView;
	}

	public void setSelectedTableView(TableView<Ingredients> selectedTableView) {
		this.selectedTableView = selectedTableView;
	}

	public TableColumn<Ingredients, String> getColIngredient() {
		return colIngredient;
	}

	public void setColIngredient(TableColumn<Ingredients, String> colIngredient) {
		this.colIngredient = colIngredient;
	}

	public Button getAddButton() {
		return addButton;
	}

	public void setAddButton(Button addButton) {
		this.addButton = addButton;
	}

	public Button getDeleteButton() {
		return deleteButton;
	}

	public void setDeleteButton(Button deleteButton) {
		this.deleteButton = deleteButton;
	}

	public Button getSearchButton() {
		return searchButton;
	}

	public void setSearchButton(Button searchButton) {
		this.searchButton = searchButton;
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		colFood.setCellValueFactory( new PropertyValueFactory<Ingredients,String>("nameFood"));
		colExpiration.setCellValueFactory( new PropertyValueFactory<Ingredients,String>("date"));
		colQuantity.setCellValueFactory( new PropertyValueFactory<Ingredients,String>("quantity"));
		colIngredient.setCellValueFactory( new PropertyValueFactory<Ingredients,String>("nameFood"));
		colRecipe.setCellValueFactory( new PropertyValueFactory<Recette,String>("name"));
		colUsed.setCellValueFactory( new PropertyValueFactory<Recette,ArrayList<String>>("listUsedIngredient"));
		colMissed.setCellValueFactory( new PropertyValueFactory<Recette,ArrayList<String>>("listMissedIngredient"));
		
		recipeTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
	    	public void handle(MouseEvent event) {
	    		if (event.getClickCount() == 2) {
	    			System.out.println(getRecipeTableView().getSelectionModel().getSelectedItem().getName());
	    			selectedRecipe = getRecipeTableView().getSelectionModel().getSelectedItem();
	    			try {
						App.setRoot("recipeView");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		}
	    	}
		});
		
		try {
			
			ResultSet rs = DatabaseController.selectAllRows();
			while (rs.next()) { // charge la database dans la table view au lancement de la fenetre Pantry						
				Ingredients toAdd = new Ingredients(rs.getString("apiID"), rs.getString("foodName"),  rs.getString("quantity"),rs.getString("expirationDate"));
				getPantryTableView().getItems().add(toAdd);
			}
			rs.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	private void addButtonAction() {
		Ingredients toAdd = getPantryTableView().getSelectionModel().getSelectedItem();
		getSelectedTableView().getItems().add(toAdd);
	}
	
	@FXML
	private void searchButtonAction() throws JSONException, IOException {
		getRecipeTableView().getItems().clear();
		ObservableList<Ingredients> ing = getSelectedTableView().getItems();
		ArrayList<Ingredients> listIngredients = new ArrayList<Ingredients>();
		for (int i = 0;i<ing.size();i++) {
			listIngredients.add(ing.get(i));
		};
		ArrayList<Recette> listRecettes = ApiRecette.parse(ApiRecette.urltoJsonArray(ApiRecette.urlRecette(listIngredients)));
		for (int i = 0;i<listRecettes.size();i++) {
			getRecipeTableView().getItems().add(listRecettes.get(i));
		};
		getSelectedTableView().getItems().clear();
	}
	
	

	@FXML
	private void deleteButtonAction() {
		int i = getSelectedTableView().getSelectionModel().getSelectedIndex();
		getSelectedTableView().getItems().remove(i);
	}
	
	@FXML
	private void clearButtonAction() {
		getRecipeTableView().getItems().clear();
	}
    
}