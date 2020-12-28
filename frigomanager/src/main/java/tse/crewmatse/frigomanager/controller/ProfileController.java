package tse.crewmatse.frigomanager.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import tse.crewmatse.frigomanager.App;
import tse.crewmatse.frigomanager.userprofile.UserProfile;
import tse.crewmatse.frigomanager.util.DatabaseController;
import tse.crewmatse.frigomanager.util.Ingredients;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class ProfileController  implements Initializable{
	
	@FXML private  TextField usernameTxtField;
	@FXML private  TextField firstNameTxtField;
	@FXML private  TextField lastNameTxtField;
	@FXML private  ChoiceBox choiceBoxGender;
	@FXML private  ChoiceBox choiceBoxProfile;
	@FXML private  DatePicker birthDatePicker;	
	@FXML private  TextField heightTxtField;
	@FXML private  TextField weightTxtField;
	@FXML private  TextField weightTxtImc;
	@FXML private  TextField weightTxtMinWeight;
	@FXML private  TextField weightTxtMaxWeight;
	@FXML private  Button saveButtonProfile;
	@FXML private  Button deleteButtonProfile;
	@FXML private  Button loadButtonProfile;
	@FXML private  Button modifButtonProfile;
	@FXML private RadioButton radioButtonHealthy;
	@FXML private  Label labelIMCmean;
	
	public UserProfile user = null;
	
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

    
   
    public RadioButton getradioButtonHealthy() {
		return radioButtonHealthy;
	}

	public void setradioButtonHealthy(RadioButton radioButtonHealthy) {
		this.radioButtonHealthy= radioButtonHealthy;
	}
	
    public Button getmodifButtonProfile() {
		return modifButtonProfile;
	}

	public void setmodifButtonProfile(Button modifButtonProfile) {
		this.modifButtonProfile= modifButtonProfile;
	}
    
    public Button getloadButtonProfile() {
		return loadButtonProfile;
	}

	public void setloadButtonProfile(Button loadButtonProfile) {
		this.loadButtonProfile = loadButtonProfile;
	}

    public Button getdeleteButtonProfile() {
		return deleteButtonProfile;
	}

	public void setdeleteButtonProfile(Button deleteButtonProfile) {
		this.deleteButtonProfile = deleteButtonProfile;
	}
	
    public ChoiceBox getchoiceBoxProfile() {
		return choiceBoxProfile;
	}

	public void setchoiceBoxProfile(ChoiceBox choiceBoxProfile) {
		this.choiceBoxProfile = choiceBoxProfile;
	}

    
    public TextField getusernameTxtField() {
		return usernameTxtField;
	}

	public void setusernameTxtField(TextField usernameTxtField) {
		this.usernameTxtField = usernameTxtField;
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
	
	public void populateUserChoiceBox() {
		getchoiceBoxProfile().getItems().clear();
		ArrayList<String> profileArrayList = new ArrayList<String>();
		try {
			
			CachedRowSet rs = DatabaseController.getUserProfile();
			while (rs.next()) { 					
				profileArrayList.add(rs.getString("Username"));
			}
			rs.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String profileList[] = new String[profileArrayList.size()]; 
		  
        // ArrayList to Array Conversion 
        for (int j = 0; j < profileArrayList.size(); j++) { 
  
            // Assign each value to String array 
            profileList[j] = profileArrayList.get(j); 
        } 
        getchoiceBoxProfile().getItems().addAll(profileList);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		String[] genderList = {"Male","Female"};
		getChoiceBoxGender().getItems().addAll(genderList);
		getWeightTxtImc().setEditable(false);
		getWeightTxtMaxWeight().setEditable(false);
		getWeightTxtMinWeight().setEditable(false);
		
		populateUserChoiceBox();
		
	
		try {
			CachedRowSet rs = DatabaseController.loadUserWithLoadedStateForInitialize();
			 while (rs.next()) {
			 user = new UserProfile(rs.getDouble("Weight"), rs.getDouble("Height"), rs.getString("Username"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("Gender"),rs.getBoolean("HealthyMode"),rs.getString("BirthDate"),rs.getInt("UserID"));
			 }rs.close();
		} 
		catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		getusernameTxtField().setText(user.getusername());
		getFirstNameTxtField().setText(user.getUserFirstName());
		getLastNameTxtField().setText(user.getUserLastName());
		
		if(user.getuserGender().equalsIgnoreCase("Male")) {
			getChoiceBoxGender().getSelectionModel().selectFirst();
		} else if(user.getuserGender().equalsIgnoreCase("Female")) {
			 
			getChoiceBoxGender().getSelectionModel().selectLast();
		}
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate birthdate =LocalDate.parse(user.getbirthDate(), formatter); 
		getBirthDatePicker().setValue(birthdate);			
		getHeightTxtField().setText(String.valueOf(user.getUserHeight()));	
		getWeightTxtField().setText(String.valueOf(user.getUserWeight()));					 
		getWeightTxtImc().setText(String.format("%.2f",user.getUserBMI()));			
		getWeightTxtMaxWeight().setText(String.format("%.2f", user.getIdealWeightMax()));
		getWeightTxtMinWeight().setText(String.format("%.2f", user.getIdealWeightMin()));
		
		if (user.getHealthyMode() == true) {
			getradioButtonHealthy().setSelected(true);
		}else {
			getradioButtonHealthy().setSelected(false);
		}
		
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
	
	@FXML
	private void saveButtonAction() {
		String username = getusernameTxtField().getText();
		String FirstName = getFirstNameTxtField().getText();
		String LastName = getLastNameTxtField().getText();
		String userGender = getChoiceBoxGender().getSelectionModel().getSelectedItem().toString();
		Double Height = Double.valueOf(getHeightTxtField().getText());
		Double Weight = Double.valueOf(getWeightTxtField().getText());
		Boolean HealthyMode = getradioButtonHealthy().isSelected();
		String birthDate = String.valueOf(getBirthDatePicker().getValue());
		if (username != null && !username.trim().isEmpty() 
				&& FirstName != null && !LastName.trim().isEmpty()
				&& LastName != null && !LastName.trim().isEmpty()
				&& userGender != null && !userGender.trim().isEmpty()
				&& Weight > 0.1
	        	&& Height > 10.0
	        	&& birthDate != null
				) {
		DatabaseController.saveUserInfo(username, FirstName, LastName, userGender, Height, Weight, HealthyMode, birthDate);
		}
		populateUserChoiceBox();
	}
	
	@FXML
	private void modifButtonAction() {
		String username = getusernameTxtField().getText();
		String FirstName = getFirstNameTxtField().getText();
		String LastName = getLastNameTxtField().getText();
		String userGender = getChoiceBoxGender().getSelectionModel().getSelectedItem().toString();
		Double Height = Double.valueOf(getHeightTxtField().getText());
		Double Weight = Double.valueOf(getWeightTxtField().getText());
		Boolean HealthyMode = getradioButtonHealthy().isSelected();
		String birthDate = String.valueOf(getBirthDatePicker().getValue());
		Integer userId = Integer.valueOf(user.getuserId()); 
		if (username != null && !username.trim().isEmpty() 
			&& FirstName != null && !LastName.trim().isEmpty()
			&& LastName != null && !LastName.trim().isEmpty()
			&& userGender != null && !userGender.trim().isEmpty()
			&& Weight > 0.1
        	&& Height > 10.0
        	&& birthDate != null
        	&& userId != 0
			) {
			DatabaseController.modifUserInfo(username, FirstName, LastName, userGender, Height, Weight, HealthyMode, birthDate, userId);
		}
		populateUserChoiceBox();
		user.setusername(username);
		user.setUserFirstName(FirstName);
		user.setUserLastName(LastName);
		user.setuserGender(userGender);
		user.setUserHeight(Height);
		user.setUserWeight(Weight);
		user.setHealthyMode(HealthyMode);
		user.setbirthDate(birthDate);
		user.calculateBMI();
		user.calculateIdealWeight();
		getWeightTxtImc().setText(String.format("%.2f",user.getUserBMI()));			
		getWeightTxtMaxWeight().setText(String.format("%.2f", user.getIdealWeightMax()));
		getWeightTxtMinWeight().setText(String.format("%.2f", user.getIdealWeightMin()));
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
	
	@FXML
	private void loadButtonAction() throws SQLException {
			String username = getchoiceBoxProfile().getSelectionModel().getSelectedItem().toString();
			
			try {
				CachedRowSet rs = DatabaseController.getUserInfo(username);
				//double userWeight, double userHeight,String username, String userFirstName, String userLastName, String userGender,boolean healthyMode, Date birthDate, int userId
				while (rs.next()) {
				user = new UserProfile(rs.getDouble("Weight"), rs.getDouble("Height"), rs.getString("Username"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("Gender"),rs.getBoolean("HealthyMode"),rs.getString("BirthDate"),rs.getInt("UserID"));
				}
				rs.close();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			getusernameTxtField().setText(user.getusername());
			getFirstNameTxtField().setText(user.getUserFirstName());
			getLastNameTxtField().setText(user.getUserLastName());
			
			if(user.getuserGender().equalsIgnoreCase("Male")) {
				getChoiceBoxGender().getSelectionModel().selectFirst();
			} else if(user.getuserGender().equalsIgnoreCase("Female")) {
				 
				getChoiceBoxGender().getSelectionModel().selectLast();
			}
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate birthdate = LocalDate.parse(user.getbirthDate(), formatter);
			getBirthDatePicker().setValue(birthdate);			
			getHeightTxtField().setText(String.valueOf(user.getUserHeight()));	
			getWeightTxtField().setText(String.valueOf(user.getUserWeight()));					 
			getWeightTxtImc().setText(String.format("%.2f",user.getUserBMI()));			
			getWeightTxtMaxWeight().setText(String.format("%.2f", user.getIdealWeightMax()));
			getWeightTxtMinWeight().setText(String.format("%.2f", user.getIdealWeightMin()));
			
			if (user.getHealthyMode() == true) {
				getradioButtonHealthy().setSelected(true);
			}else {
				getradioButtonHealthy().setSelected(false);
			}
			
			DatabaseController.updateLoadedState(user.getuserId());
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