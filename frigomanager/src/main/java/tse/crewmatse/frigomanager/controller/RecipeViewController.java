package tse.crewmatse.frigomanager.controller;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;


import org.json.JSONException;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

public class RecipeViewController implements Initializable{
	@FXML private Label recipeTitle;
	@FXML private ImageView recipeImage;
	@FXML private Button quitButton;
	@FXML private ListView<String> stepsListView;
	@FXML private ListView<String> ingredientsListView;
	
	
	
	
	
	

	@FXML
    private void quit() throws IOException {
        App.setRoot("recipe");
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		
		
		Recette r = RecipeController.getSelectedRecipe();
		recipeImage.setImage(r.getImage());
		recipeTitle.setText(r.getName());
		
		int nIngredients = r.getListIngredientQuantity().size();
		int nSteps = r.getListSteps().size();
		
		for (int i = 0;i<nSteps;i++) {
			stepsListView.getItems().add(r.getListSteps().get(nSteps-1-i));
		};
		
		for (int i = 0;i<nIngredients;i++) {
			ingredientsListView.getItems().add(r.getListIngredientQuantity().get(i));
		};
		
	}
}
