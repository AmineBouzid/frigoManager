package tse.crewmatse.frigomanager.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import tse.crewmatse.frigomanager.App;

public class MenuControllers {

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
    
}