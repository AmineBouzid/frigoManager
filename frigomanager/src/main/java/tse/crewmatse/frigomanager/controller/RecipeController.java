package tse.crewmatse.frigomanager.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import javax.sql.rowset.CachedRowSet;

import org.json.JSONException;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import tse.crewmatse.frigomanager.App;
import tse.crewmatse.frigomanager.api.ApiRecette;
import tse.crewmatse.frigomanager.util.DatabaseController;
import tse.crewmatse.frigomanager.util.Ingredients;
import tse.crewmatse.frigomanager.util.Recette;


/**
 * This class is the controller for the recipe page
 * @author Thomas Blomme, Amine Bouzid
 *
 */
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
	@FXML private ProgressBar loadingRecipes;
	@FXML private Label labelFeedBack;
		
	

	public static Recette selectedRecipe;
	public static ArrayList<Ingredients> selectedIngredients = new ArrayList<Ingredients>();
	public static ArrayList<Recette> searchRecipes = new ArrayList<Recette>();
	
	public static Recette getSelectedRecipe() {
		return selectedRecipe;
	}

	

	/**
	 * @return recipeSplitPane
	 */
	@SuppressWarnings("exports")
	public SplitPane getRecipeSplitPane() {
		return recipeSplitPane;
	}

	/**
	 * @param recipeSplitPane
	 */
	public void setRecipeSplitPane(@SuppressWarnings("exports") SplitPane recipeSplitPane) {
		this.recipeSplitPane = recipeSplitPane;
	}

	/**
	 * @return recipeTableView
	 */
	public TableView<Recette> getRecipeTableView() {
		return recipeTableView;
	}

	/**
	 * @param recipeTableView
	 */
	public void setRecipeTableView(TableView<Recette> recipeTableView) {
		this.recipeTableView = recipeTableView;
	}

	/**
	 * @return colRecipe
	 */
	public TableColumn<Recette, String> getColRecipe() {
		return colRecipe;
	}

	/**
	 * @param colRecipe
	 */
	public void setColRecipe(TableColumn<Recette, String> colRecipe) {
		this.colRecipe = colRecipe;
	}

	/**
	 * @return colIngredient
	 */
	public TableColumn<Recette, TableView<Recette>> getColIngredients() {
		return colIngredients;
	}

	/**
	 * @param colIngredients
	 */
	public void setColIngredients(TableColumn<Recette, TableView<Recette>> colIngredients) {
		this.colIngredients = colIngredients;
	}

	/**
	 * @return colUsed
	 */
	public TableColumn<Recette, ArrayList<String>> getColUsed() {
		return colUsed;
	}

	/**
	 * @param colUsed
	 */
	public void setColUsed(TableColumn<Recette, ArrayList<String>> colUsed) {
		this.colUsed = colUsed;
	}

	/**
	 * @return colMissed
	 */
	public TableColumn<Recette, ArrayList<String>> getColMissed() {
		return colMissed;
	}

	/**
	 * @param colMissed
	 */
	public void setColMissed(TableColumn<Recette, ArrayList<String>> colMissed) {
		this.colMissed = colMissed;
	}

	/**
	 * @return pantryTableView
	 */
	public TableView<Ingredients> getPantryTableView() {
		return pantryTableView;
	}

	/**
	 * @param pantryTableView
	 */
	public void setPantryTableView(TableView<Ingredients> pantryTableView) {
		this.pantryTableView = pantryTableView;
	}

	/**
	 * @return colFood
	 */
	public TableColumn<Ingredients, String> getColFood() {
		return colFood;
	}

	/**
	 * @param colFood
	 */
	public void setColFood(TableColumn<Ingredients, String> colFood) {
		this.colFood = colFood;
	}

	/**
	 * @return colExpiration
	 */
	public TableColumn<Ingredients, String> getColExpiration() {
		return colExpiration;
	}

	/**
	 * @param colExpiration
	 */
	public void setColExpiration(TableColumn<Ingredients, String> colExpiration) {
		this.colExpiration = colExpiration;
	}

	/**
	 * @return colQuantity
	 */
	public TableColumn<Ingredients, String> getColQuantity() {
		return colQuantity;
	}

	/**
	 * @param colQuantity
	 */
	public void setColQuantity(TableColumn<Ingredients, String> colQuantity) {
		this.colQuantity = colQuantity;
	}

	/**
	 * @return selectedTableView
	 */
	public TableView<Ingredients> getSelectedTableView() {
		return selectedTableView;
	}

	/**
	 * @param selectedTableView
	 */
	public void setSelectedTableView(TableView<Ingredients> selectedTableView) {
		this.selectedTableView = selectedTableView;
	}

	/**
	 * @return colIngredient
	 */
	public TableColumn<Ingredients, String> getColIngredient() {
		return colIngredient;
	}

	/**
	 * @param colIngredient
	 */
	public void setColIngredient(TableColumn<Ingredients, String> colIngredient) {
		this.colIngredient = colIngredient;
	}

	/**
	 * @return addButton
	 */
	@SuppressWarnings("exports")
	public Button getAddButton() {
		return addButton;
	}

	/**
	 * @param addButton
	 */
	public void setAddButton(@SuppressWarnings("exports") Button addButton) {
		this.addButton = addButton;
	}

	/**
	 * @return deleteButton
	 */
	@SuppressWarnings("exports")
	public Button getDeleteButton() {
		return deleteButton;
	}

	/**
	 * @param deleteButton
	 */
	public void setDeleteButton(@SuppressWarnings("exports") Button deleteButton) {
		this.deleteButton = deleteButton;
	}

	/**
	 * @return searchButton
	 */
	@SuppressWarnings("exports")
	public Button getSearchButton() {
		return searchButton;
	}
	
	/**
	 * @return loadingRecipes
	 */
	public ProgressBar getLoadingRecipes() {
		return loadingRecipes;
	}

	/**
	 * @param loadingRecipes
	 */
	public void setLoadingRecipes(ProgressBar loadingRecipes) {
		this.loadingRecipes = loadingRecipes;
	}



	/**
	 * @param searchButton
	 */
	public void setSearchButton(@SuppressWarnings("exports") Button searchButton) {
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

	/**
	 * This method initializes the Recipe page with the pantry ingredients and the search bar
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadingRecipes.setProgress(ProgressBar.INDETERMINATE_PROGRESS);
		labelFeedBack.setVisible(false);
		colFood.setCellValueFactory( new PropertyValueFactory<Ingredients,String>("nameFood"));
		colExpiration.setCellValueFactory( new PropertyValueFactory<Ingredients,String>("date"));
		colQuantity.setCellValueFactory( new PropertyValueFactory<Ingredients,String>("quantity"));
		colIngredient.setCellValueFactory( new PropertyValueFactory<Ingredients,String>("nameFood"));
		colRecipe.setCellValueFactory( new PropertyValueFactory<Recette,String>("name"));
		colUsed.setCellValueFactory( new PropertyValueFactory<Recette,ArrayList<String>>("listUsedIngredient"));
		colMissed.setCellValueFactory( new PropertyValueFactory<Recette,ArrayList<String>>("listMissedIngredient"));
		if (selectedIngredients!=null) {
			for (int i=0;i<selectedIngredients.size();i++) {
				getSelectedTableView().getItems().add(selectedIngredients.get(i));
			}
		}
		
		if (getSelectedTableView().getItems().size()!=0) {
			for (int i=0;i<searchRecipes.size();i++) {
				getRecipeTableView().getItems().add(searchRecipes.get(i));
			}
		}
		recipeTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
	    	public void handle(MouseEvent event) {
	    		if (event.getClickCount() == 2) {
	    			System.out.println(getRecipeTableView().getSelectionModel().getSelectedItem().getName());
	    			System.out.println(""+getRecipeTableView().getSelectionModel().getSelectedItem().getIdApi());
	    			selectedRecipe = getRecipeTableView().getSelectionModel().getSelectedItem();
	    			try {
						App.setRoot("recipeView");
					} catch (IOException e) {
						e.printStackTrace();
					}
	    			
	    			int loadedUserId = 0 ;
                	try {
            			CachedRowSet rs = DatabaseController.loadUserWithLoadedState();
            			 while (rs.next()) {
            				 loadedUserId =rs.getInt("UserID");
            			    }
            			
            			
            		} catch (NumberFormatException e) {
            			// TODO Auto-generated catch block
            			e.printStackTrace();
            		} catch (SQLException e) {
            			// TODO Auto-generated catch block
            			e.printStackTrace();
            		}
                	DatabaseController.storeLastViewedRecipe(getRecipeTableView().getSelectionModel().getSelectedItem().getIdApi()
                			, loadedUserId, getRecipeTableView().getSelectionModel().getSelectedItem().getName());


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
	
	/**
	 * Handles the addButton, to add ingredients in the search zone and update the progress bar
	 */
	@FXML
	private void addButtonAction() {
		labelFeedBack.setVisible(false);
		loadingRecipes.setProgress(ProgressBar.INDETERMINATE_PROGRESS);
		Ingredients toAdd = getPantryTableView().getSelectionModel().getSelectedItem();
		if (!getSelectedTableView().getItems().contains(toAdd)) {
			getSelectedTableView().getItems().add(toAdd);
			selectedIngredients.add(toAdd);
		}
		
	}
	
	/**
	 * Handles the searchButton, launch a call to the spoonacular api and updates the progress bar
	 * After an api response, displays the recipes
	 * @throws JSONException
	 * @throws IOException
	 */
	@FXML
	private void searchButtonAction() throws JSONException, IOException {		
		getRecipeTableView().getItems().clear();
		ObservableList<Ingredients> ing = getSelectedTableView().getItems();
		ArrayList<Ingredients> listIngredients = new ArrayList<Ingredients>();
		for (int i = 0;i<ing.size();i++) {
			listIngredients.add(ing.get(i));
		};
		try { //if no error 402:

			ArrayList<Recette> listRecettes = ApiRecette.parse(ApiRecette.urltoJsonArray(ApiRecette.urlRecette(listIngredients)));
			labelFeedBack.setVisible(true);
			labelFeedBack.setText("Recipes Found !");
			try {
				CachedRowSet rs = DatabaseController.loadUserWithLoadedState();
				while (rs.next()) {
					
					if (rs.getBoolean("HealthyMode")) {
						Collections.sort(listRecettes);
					}
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			for (int i = 0;i<listRecettes.size();i++) {
				getRecipeTableView().getItems().add(listRecettes.get(i));
			};
			searchRecipes=listRecettes;
			loadingRecipes.setProgress(100);
			
		} catch (IOException e) { //catch error 402 API request
			labelFeedBack.setVisible(true);
			labelFeedBack.setText("Error 402 : No More tokens");
			loadingRecipes.setProgress(0);
		}
	}
	
	

	/**
	 * Handles the deleteButton to delete ingredients from the search zone
	 */
	@FXML
	private void deleteButtonAction() {
		labelFeedBack.setVisible(false);
		loadingRecipes.setProgress(ProgressBar.INDETERMINATE_PROGRESS);
		int i = getSelectedTableView().getSelectionModel().getSelectedIndex();
		getSelectedTableView().getItems().remove(i);
		selectedIngredients.remove(i);
	}
	
	/**
	 * Handles the clearButton, which clears the search zone
	 */
	@FXML
	private void clearButtonAction() {
		labelFeedBack.setVisible(false);
		loadingRecipes.setProgress(ProgressBar.INDETERMINATE_PROGRESS);
		getRecipeTableView().getItems().clear();
	}
    
}