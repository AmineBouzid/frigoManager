package tse.crewmatse.frigomanager.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

//import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import tse.crewmatse.frigomanager.App;
import tse.crewmatse.frigomanager.userprofile.UserProfile;

public class ProfileController  implements Initializable{
		
	@FXML private  TextField firstNameTxtField;
	@FXML private  TextField lastNameTxtField;
	@FXML private  ChoiceBox choiceBoxGender;
	@FXML private  DatePicker birthDatePicker;	
	@FXML private  TextField heightTxtField;
	@FXML private  TextField weightTxtField;
	@FXML private  TextField weightTxtImc;
	@FXML private  TextField weightTxtMinWeight;
	@FXML private  TextField weightTxtMaxWeight;
	@FXML private  Button saveButtonProfile;
	@FXML private  Label labelIMCmean;
	

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

	public ChoiceBox getChoiceBoxGender() {
		return choiceBoxGender;
	}

	public void setChoiceBoxGender(ChoiceBox genderChoiceBox) {
		this.choiceBoxGender = genderChoiceBox;
	}

	public DatePicker getBirthDatePicker() {
		return birthDatePicker;
	}

	public void setBirthDatePicker(DatePicker datePicker) {
		this.birthDatePicker = datePicker;
	}

	public TextField getHeightTxtField() {
		return heightTxtField;
	}

	public void setHeightTxtField(TextField heightTxtField) {
		this.heightTxtField = heightTxtField;
	}

	public TextField getWeightTxtField() {
		return weightTxtField;
	}

	public void setWeightTxtField(TextField weightTxtField) {
		this.weightTxtField = weightTxtField;
	}

	public TextField getWeightTxtImc() {
		return weightTxtImc;
	}

	public void setWeightTxtImc(TextField weightTxtImc) {
		this.weightTxtImc = weightTxtImc;
	}

	public TextField getWeightTxtMinWeight() {
		return weightTxtMinWeight;
	}

	public void setWeightTxtMinWeight(TextField weightTxtMinWeight) {
		this.weightTxtMinWeight = weightTxtMinWeight;
	}

	public TextField getWeightTxtMaxWeight() {
		return weightTxtMaxWeight;
	}

	public void setWeightTxtMaxWeight(TextField weightTxtMaxWeight) {
		this.weightTxtMaxWeight = weightTxtMaxWeight;
	}

	public Button getSaveButtonProfile() {
		return saveButtonProfile;
	}

	public void setSaveButtonProfile(Button saveButtonProfile) {
		this.saveButtonProfile = saveButtonProfile;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		String[] genderList = {"male","female"};
		getChoiceBoxGender().getItems().addAll(genderList);
		//Modify these next lines 
		getChoiceBoxGender().getSelectionModel().selectFirst();
		getFirstNameTxtField().setText("Amine");
		getLastNameTxtField().setText("Bouzid");
		getWeightTxtField().setText("65");
		getHeightTxtField().setText("170");
		
		//Date date_naissance = new Date("03-01-2000");
		getBirthDatePicker().setValue(LocalDate.of(2000, 1, 3));
		getWeightTxtImc().setEditable(false);
		getWeightTxtMaxWeight().setEditable(false);
		getWeightTxtMinWeight().setEditable(false);

	}
	
	@FXML
	private void saveButtonAction() {
			//System.out.println(getWeightTxtField().getText());
			//System.out.println(Double.valueOf(getHeightTxtField().getText()));
			
			//System.out.println(firstNameTxtField.getText());
			UserProfile user =  new UserProfile(Double.valueOf(getWeightTxtField().getText()), Double.valueOf(getHeightTxtField().getText()), getFirstNameTxtField().getText(), getLastNameTxtField().getText(),true);
			//System.out.println(user.getIdealWeightMax());
			//System.out.println(String.valueOf(user.getIdealWeightMax()));
			//System.out.println(String.valueOf(user.getIdealWeightMin()));
			getWeightTxtImc().setText(String.valueOf(user.getUserBMI()));
			getWeightTxtMaxWeight().setText(String.valueOf(user.getIdealWeightMax()));
			getWeightTxtMinWeight().setText(String.valueOf(user.getIdealWeightMin()));
			//18.5 to 25
			double a=user.getUserBMI();
			if(a>=30) {
				weightTxtImc.setStyle("-fx-text-inner-color: purple;");
				labelIMCmean.setText("You are obese");
				labelIMCmean.setTextFill(Color.PURPLE);
			} else {
				if(a<18.5 || a>=25) {
				weightTxtImc.setStyle("-fx-text-inner-color: red;");
					if(a<18.5)labelIMCmean.setText("You are underweight.");
					else labelIMCmean.setText("You are overweight.");
				labelIMCmean.setTextFill(Color.RED);
				} else {
				weightTxtImc.setStyle("-fx-text-inner-color: green;");
				labelIMCmean.setText("Your weight is Healthy");
				labelIMCmean.setTextFill(Color.GREEN);
				}
			}
			

	}
	
	
	
    
    
    
}