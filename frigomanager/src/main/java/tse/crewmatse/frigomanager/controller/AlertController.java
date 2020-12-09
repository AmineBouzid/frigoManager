package tse.crewmatse.frigomanager.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tse.crewmatse.frigomanager.App;
import tse.crewmatse.frigomanager.util.DatabaseController;
import tse.crewmatse.frigomanager.util.Ingredients;

/**
 * 
 * @author LEKMAD Mohamed
 * 
 * This class manage notifications of our application.
 * There are three types of notifications:
 * 1.Close expiration dates: where we notify the user if an item is three days away from being expired.
 * //TODO Add the other notifications
 * 
 *
 */

public class AlertController implements Initializable {

	@FXML
	private TableView<Ingredients> ExpirationTableView;
	@FXML
	private TableView<Ingredients> IngredientsTableView;
	@FXML
	private TableView<Ingredients> RecipesTableView;
	@FXML
	private TableColumn<Ingredients, String> colFood;
	@FXML
	private TableColumn<Ingredients, String> colExpiration;
	@FXML
	private TextField foodField;
	@FXML
	private DatePicker datePicker;

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

	public TextField getFoodField() {
		return this.foodField;
	}

	public DatePicker getExpirationDate() {
		return this.datePicker;
	}

	public TableView getExpirationView() {
		return this.ExpirationTableView;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		colFood.setCellValueFactory(new PropertyValueFactory<Ingredients, String>("nameFood"));
		colExpiration.setCellValueFactory(new PropertyValueFactory<Ingredients, String>("date"));

		

		try {

			ResultSet rs = DatabaseController.selectAllRows();
			while (rs.next()) { 
				Ingredients toAdd = new Ingredients(rs.getString("apiID"), rs.getString("foodName"),
						rs.getString("quantity"), rs.getString("expirationDate"));

				

				if (isCloseToBeExpired(toAdd.getDate())) {
					getExpirationView().getItems().add(toAdd);
				}

			}
			rs.close();

		} catch (SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public  Boolean isCloseToBeExpired(String date) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date currentDate = new Date();
		Calendar c = Calendar.getInstance();
		
		Date IngredientExpirationDate = dateFormat.parse(date);
		c.setTime(IngredientExpirationDate);
		c.add(Calendar.DAY_OF_MONTH, -3);
		Date riskDate = c.getTime();

		if (currentDate.compareTo(riskDate) > 0) {
			return true;
		}
		
		return false;
	}
	
	

}