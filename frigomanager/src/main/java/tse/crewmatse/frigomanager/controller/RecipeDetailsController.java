package tse.crewmatse.frigomanager.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import org.jsoup.Jsoup;



import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import tse.crewmatse.frigomanager.App;
import tse.crewmatse.frigomanager.util.Ingredients;
import tse.crewmatse.frigomanager.util.Recette;

public class RecipeDetailsController  implements Initializable{
	
	@FXML private Label recipeTitle;
	@FXML private ImageView recipeImage;
	@FXML private Button quitButton;
	@FXML private TableView<Ingredients> ingredientsTable;
	@FXML private TableColumn<Ingredients, String> colIngredients;
	@FXML private Label summary;
	
	@FXML
    private void quit() throws IOException {
        App.setRoot("alerts");
    }
	
	public TableView<Ingredients> getIngredientsTable() {
		return ingredientsTable;
	}

	public void setIngredientsTable(TableView<Ingredients> ingredientsTable) {
		this.ingredientsTable = ingredientsTable;
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		colIngredients.setCellValueFactory( new PropertyValueFactory<Ingredients,String>("nameFood"));
		
		
		Recette recipe = AlertController.getSelectedRecipe();
		ArrayList<String> ingredients = recipe.getListIngredient();
		recipeTitle.setText(recipe.getName());
		recipeImage.setImage(recipe.getImage());
		
		summary.setText(Jsoup.parse(recipe.getSummary()).wholeText());
		for (int i = 0;i<ingredients.size();i++) {
			Ingredients ingredient = new Ingredients(ingredients.get(i));
			ingredientsTable.getItems().add(ingredient);
		};
		
	}

}
