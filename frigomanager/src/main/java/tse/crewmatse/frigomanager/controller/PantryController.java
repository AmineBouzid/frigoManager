package tse.crewmatse.frigomanager.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import tse.crewmatse.frigomanager.App;

public class PantryController implements Initializable {
	@FXML private SplitPane pantrySplitPane;

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
		pantrySplitPane.setDividerPositions(0.8f);
		//pantrySplitPane.setDisable(true);
		
	}
    
   
    
    
    
}