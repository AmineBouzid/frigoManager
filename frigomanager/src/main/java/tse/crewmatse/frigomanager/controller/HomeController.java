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
import javafx.scene.control.TextField;
import tse.crewmatse.frigomanager.App;
import tse.crewmatse.frigomanager.userprofile.UserProfile;
import tse.crewmatse.frigomanager.util.DatabaseController;

public class HomeController implements Initializable{
	@FXML private  TextField firstNameTxtField;
	@FXML private  TextField lastNameTxtField;
	@FXML private  Label labelActiveUser;
	
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
	//@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		//getFirstNameTxtField().setText();
		//getLastNameTxtField().setText("Bouzid");
		
		ResultSet rs = DatabaseController.loadUserWithLoadedState();
		try {
			getLabelActiveUser().setText(rs.getString(1));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
    
}