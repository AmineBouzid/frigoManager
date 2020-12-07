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

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Calendar c = Calendar.getInstance();

		try {

			ResultSet rs = DatabaseController.selectAllRows();
			while (rs.next()) { // charge la database dans la table view au lancement de la fenetre Pantry
				Ingredients toAdd = new Ingredients(rs.getString("apiID"), rs.getString("foodName"),
						rs.getString("quantity"), rs.getString("expirationDate"));

				Date IngredientExpirationDate = dateFormat.parse(toAdd.getDate());
				c.setTime(IngredientExpirationDate);
				c.add(Calendar.DAY_OF_MONTH, 3);
				Date riskDate = c.getTime();

				if (riskDate.compareTo(date) < 0) {
					getExpirationView().getItems().add(toAdd);
				}

			}
			rs.close();

		} catch (SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}