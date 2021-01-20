package tse.crewmatse.frigomanager.controller;



import java.io.IOException;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collections;
import java.util.ResourceBundle;

import javax.sql.rowset.CachedRowSet;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import tse.crewmatse.frigomanager.App;
import tse.crewmatse.frigomanager.util.DatabaseController;
import tse.crewmatse.frigomanager.util.Recette;
import java.text.DecimalFormat;


public class RecipeViewController implements Initializable{
	@FXML private SplitPane splitPane;
	@FXML private Label recipeTitle;
	@FXML private Label servings;
	@FXML private ImageView recipeImage;
	@FXML private Button quitButton;
	@FXML private Button favouriteButton;
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
		System.out.println(r.getServings());
		System.out.println(r.getListUnits());
		
		recipeImage.setImage(r.getImage());
		try {
			CachedRowSet rs = DatabaseController.loadUserWithLoadedState();
			while (rs.next()) {
				if (rs.getBoolean("HealthyMode")) {
					recipeTitle.setText(r.getName()+" ("+r.getCal()+" calories)");
				} else {
					recipeTitle.setText(r.getName());
				}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		servings.setText(""+r.getServings());
		int nIngredients = r.getListIngredient().size();
		int nSteps = r.getListSteps().size();
		
		if (r.getListSteps().size()==0) {
			stepsListView.getItems().add("Sorry there are not steps for this recipe!");
		} else {
			for (int i = 0;i<nSteps;i++) {
				stepsListView.getItems().add((i+1)+". "+r.getListSteps().get(nSteps-1-i));
			};
		}
		
		for (int i = 0;i<nIngredients;i++) {
			String s = r.getListIngredient().get(i) + ": " + r.getListQuantity().get(i) + " " + r.getListUnits().get(i);
			ingredientsListView.getItems().add(s);
		};
		
	}
	
	@FXML
    private void addToFavourite() throws IOException {
		Recette r = RecipeController.getSelectedRecipe();
		int loadedUser = 0;
		try {
			CachedRowSet rs = DatabaseController.loadUserWithLoadedState();
			 while (rs.next()) {
				 loadedUser = rs.getInt("UserID");
			    }
			 DatabaseController.addFavouriteRecipe(r.getIdApi(),loadedUser, r.getName());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	@FXML
    private void plus() throws IOException {
		DecimalFormat df = new DecimalFormat("#");
		df.setRoundingMode(RoundingMode.HALF_UP);
		double d = Double.parseDouble(servings.getText());
		d = d+1;
		servings.setText(""+df.format(d));
		ingredientsListView.getItems().clear();
		Recette r = RecipeController.getSelectedRecipe();
		int nIngredients = r.getListIngredient().size();
		for (int i = 0;i<nIngredients;i++) {
			String s = r.getListIngredient().get(i) + ": " + produitEnCroix(d,r.getServings(),r.getListQuantity().get(i)) + " " + r.getListUnits().get(i);
			ingredientsListView.getItems().add(s);
		};
    }
	
	@FXML
    private void minus() throws IOException {
		DecimalFormat df = new DecimalFormat("#.#");
		df.setRoundingMode(RoundingMode.HALF_UP);
		double d = Double.parseDouble(servings.getText());
		d = d-1;
		if (d<1) {
			d=1;
		} 
		servings.setText(""+df.format(d));
		ingredientsListView.getItems().clear();
		Recette r = RecipeController.getSelectedRecipe();
		int nIngredients = r.getListIngredient().size();
		for (int i = 0;i<nIngredients;i++) {
			String s = r.getListIngredient().get(i) + ": " + produitEnCroix(d,r.getServings(),r.getListQuantity().get(i)) + " " + r.getListUnits().get(i);
			ingredientsListView.getItems().add(s);
		};
		
    }
	
	private String produitEnCroix(double d,double s,double q) {
		double r = (d/s)*q;
		DecimalFormat df = new DecimalFormat("#.#");
		df.setRoundingMode(RoundingMode.HALF_UP);
		return df.format(r);
	}
	
}
