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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import tse.crewmatse.frigomanager.App;
import tse.crewmatse.frigomanager.userprofile.UserProfile;
import tse.crewmatse.frigomanager.util.DatabaseController;
import tse.crewmatse.frigomanager.util.Ingredients;
import tse.crewmatse.frigomanager.util.Recette;

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
	@FXML private TableView<Recette> favoriteRecipesList;
    @FXML private TableColumn<Recette, String> colFavName;
    @FXML private  Button favoriteRecipeDelete;
    
    
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

	public TableView<Recette> getFavoriteRecipesList() {
		return favoriteRecipesList;
	}

	public void setFavoriteRecipesList(TableView<Recette> favoriteRecipesList) {
		this.favoriteRecipesList = favoriteRecipesList;
	}

	public TableColumn<Recette, String> getColFavName() {
		return colFavName;
	}

	public void setColFavName(TableColumn<Recette, String> colFavName) {
		this.colFavName = colFavName;
	}

	public Button getFavoriteRecipeDelete() {
		return favoriteRecipeDelete;
	}

	public void setFavoriteRecipeDelete(Button favoriteRecipeDelete) {
		this.favoriteRecipeDelete = favoriteRecipeDelete;
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
	
	/**
	 * This method calls the DatabaseController method getUserProfile that returns the list of usernames in the database.
	 * It then populate the user checkbox with the returned list
	 */
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
	
	/**
	 * This method calls the DatabaseController method loadUserWithLoadedState that returns the loaded user.
	 * It then loads the favourite recipes of that user from the favouriteRecipe table using the DatabaseController method getFavouriteRecipe
	 * It adds each loaded recipe to the tableview of the page. 
	 */ 
	public void populateFavouriteTable() {
		int loadedUser = 0;
		try {
			CachedRowSet rs = DatabaseController.loadUserWithLoadedState();
			 while (rs.next()) {
				 loadedUser = rs.getInt("UserID");
			    }
			 rs.close();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		colFavName.setCellValueFactory(new PropertyValueFactory<Recette,String>("name"));
		
		try {
			CachedRowSet rs1 = DatabaseController.getFavouriteRecipe(loadedUser);
			 while (rs1.next()) {
					 Recette toAdd = new Recette ( rs1.getString("recipeName"), rs1.getInt("recipeId"));
					 getFavoriteRecipesList().getItems().add(toAdd);
				 }
				 rs1.close();
			 } 
		catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Here we initialize the Porfile page, loading the loaded user with loadedstate = 1 info from the database.
	 * It calls the populateUserChoiceBox and populateFavouriteTable
	 * It  updates the user's class attributes to the loaded user info.
	 * It then populates the user information fields with the loaded data then calculates the userBMI and weight methods and displays them
	 * 
	 * 
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		String[] genderList = {"Male","Female"};
		getChoiceBoxGender().getItems().addAll(genderList);
		getWeightTxtImc().setEditable(false);
		getWeightTxtMaxWeight().setEditable(false);
		getWeightTxtMinWeight().setEditable(false);
		
		populateUserChoiceBox();
		
		populateFavouriteTable();
		
		
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
	
	
	/**
	 * This method is called when the delete favourite recipe button is clicked. It checks which user profile is loaded. 
	 * Then calls the database method deleteFavouriteRecipe with the loaded user's username and selected recipe's Id as parameters
	 * Then deletes the recipe from the tableView.
	 */
	@FXML
	private void deleteFavouriteButtonAction() {
		int loadedUser = 0;
		try {
			CachedRowSet rs = DatabaseController.loadUserWithLoadedState();
			 while (rs.next()) {
				 loadedUser = rs.getInt("UserID");
			    }
			 rs.close();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			int id = getFavoriteRecipesList().getSelectionModel().getSelectedItem().getIdApi();
			int id_table = getFavoriteRecipesList().getSelectionModel().getSelectedIndex();
			DatabaseController.deleteFavouriteRecipe(id,loadedUser);
			getFavoriteRecipesList().getItems().remove(id_table);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * This method extracts all the information from the page's textfields and datepicker. Then calls the DatabaseCOntroller method saveUserInfo with the extracted data as parameters
	 * It then updates the user's class attributes to the saved user info.
	 */
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
	
	/**
	 * This method updates an existing user's profile with the date that the user just modified in the UI
	 */
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
	
	/**
	 * This method loads the user's informations depending on which one is selcted in the getchoiceBoxProfile
	 * @throws SQLException
	 */
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
			getFavoriteRecipesList().getItems().clear();
			populateFavouriteTable();
	}
	
	/**
	 * @throws SQLException
	 * This method will delete the user's profile depending on which user is selected in the getchoiceBoxProfile
	 */
	@FXML
	private void deleteButtonAction() throws SQLException {
			String username = getchoiceBoxProfile().getSelectionModel().getSelectedItem().toString();
			
			try {
				DatabaseController.deleteUser(username);
				//double userWeight, double userHeight,String username, String userFirstName, String userLastName, String userGender,boolean healthyMode, Date birthDate, int userId
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			populateUserChoiceBox();
	}
	@FXML
    private void isHealthy() {

        System.out.println("u are healthy");
    }
	
	
	
    
    
    
}