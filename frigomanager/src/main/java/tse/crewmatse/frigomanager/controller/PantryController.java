/**
 * This class is a controller for the page Pantry
 * @author Guillaume Bayon, Amine Bouzid
 *
 */

package tse.crewmatse.frigomanager.controller;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tse.crewmatse.frigomanager.App;
import tse.crewmatse.frigomanager.util.Ingredients;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import tse.crewmatse.api.ApiIngredients;

import tse.crewmatse.frigomanager.util.DatabaseController;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;


public class PantryController implements Initializable {
	@FXML
	private SplitPane pantrySplitPane;
	@FXML
	private TableView<Ingredients> pantryTableView;
	@FXML
	private TableView<Ingredients> apiTableView;
	@FXML
	private TableColumn<Ingredients, String> colApiResult;
	@FXML
	private TableColumn<Ingredients, String> colFood;
	@FXML
	private TableColumn<Ingredients, String> colExpiration;
	@FXML
	private TableColumn<Ingredients, String> colQuantity;
	@FXML
	private DatePicker datePicker;
	@FXML
	private TextField quantityField;
	@FXML
	private ChoiceBox unitChoiceBox;
	@FXML
	private TextField foodField;
	@FXML
	private Button addButton;
	@FXML
	private Button searchButton;
	@FXML
	private Button delButton;

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

	public Button getAddButton() {
		return this.addButton;
	}

	public Button getSearchButton() {
		return this.searchButton;
	}

	public ChoiceBox getUnit() {
		return this.unitChoiceBox;
	}

	public TextField getQuantityField() {
		return this.quantityField;
	}

	public TableView getPantryView() {
		return this.pantryTableView;
	}

	public TableView<Ingredients> getApiTableView() {
		return apiTableView;
	}

	public void setApiTableView(TableView<Ingredients> apiTableView) {
		this.apiTableView = apiTableView;
	}

	public TableColumn<Ingredients, String> getColApiResult() {
		return colApiResult;
	}

	public void setColApiResult(TableColumn<Ingredients, String> colApiResult) {
		this.colApiResult = colApiResult;
	}

	public Button getDelButton() {
		return this.delButton;
	}


	/**
	 * Here we initialize the Pantry page, loading the pantry database in the tableview
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		pantrySplitPane.setDividerPositions(0.8f);
		String[] unitsList = { "item", "g", "L", "unit(s)" };
		unitChoiceBox.getItems().addAll(unitsList);
		unitChoiceBox.getSelectionModel().selectFirst();
		colFood.setCellValueFactory(new PropertyValueFactory<Ingredients, String>("nameFood"));
		colExpiration.setCellValueFactory(new PropertyValueFactory<Ingredients, String>("date"));
		colQuantity.setCellValueFactory(new PropertyValueFactory<Ingredients, String>("quantity"));
		colApiResult.setCellValueFactory(new PropertyValueFactory<Ingredients, String>("nameFood"));

		try {

			CachedRowSet rs = DatabaseController.selectAllRows();
			while (rs.next()) { 
				Ingredients toAdd = new Ingredients(rs.getString("apiID"), rs.getString("foodName"),
						rs.getString("quantity"), rs.getString("expirationDate"));
				getPantryView().getItems().add(toAdd);
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//	private void addButtonAction() {
//
//		Ingredients selectedApiResult=apiTableView.getSelectionModel().getSelectedItem();
//		String quantity = quantityField.getText().concat(" " + unitChoiceBox.getSelectionModel().getSelectedItem().toString());
//		DatabaseController.addItemInTable(selectedApiResult.getIdApi(),selectedApiResult.getNameFood(), datePicker.getValue().toString(), quantity );			
//		Ingredients toAdd = new Ingredients(selectedApiResult.getIdApi(), selectedApiResult.getNameFood(),quantity,datePicker.getValue().toString());
//		getPantryView().getItems().add(toAdd);
//		apiTableView.getItems().clear();	
//		foodField.clear();
//		quantityField.clear();
//		datePicker.getEditor().clear();
//		
//	}
	/**
	 * This method handles the addButton, by adding the selected api ingredient in the database and tableview
	 */
	@FXML
	private void addButtonAction() {

		Ingredients selectedApiResult = apiTableView.getSelectionModel().getSelectedItem();
		String quantity = quantityField.getText()
				.concat(" " + unitChoiceBox.getSelectionModel().getSelectedItem().toString());

		try {
			CachedRowSet crs = DatabaseController.checkIfItemExists(selectedApiResult.getIdApi());
			if (crs.next()) {
				String quantity_fromDB = crs.getString("quantity");
				String[] parts = quantity_fromDB.split("\\s+");
				String quantity_temp = String.valueOf(Double.valueOf(parts[0]) + Double.valueOf(quantityField.getText()));
				quantity = quantity_temp.concat(" " + unitChoiceBox.getSelectionModel().getSelectedItem().toString());
				DatabaseController.updateItemInTable(selectedApiResult.getIdApi(), quantity, datePicker.getValue().toString());
				
			} else {
				DatabaseController.addItemInTable(selectedApiResult.getIdApi(), selectedApiResult.getNameFood(),
						datePicker.getValue().toString(), quantity);
			}
			crs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		getPantryView().getItems().clear();
		try {

			CachedRowSet rs1 = DatabaseController.selectAllRows();
			while (rs1.next()) { 
				Ingredients toAdd = new Ingredients(rs1.getString("apiID"), rs1.getString("foodName"),
						rs1.getString("quantity"), rs1.getString("expirationDate"));
				getPantryView().getItems().add(toAdd);
			}
			rs1.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		apiTableView.getItems().clear();
		foodField.clear();
		quantityField.clear();
		datePicker.getEditor().clear();

	}

	/**
	 * This method handles the searchButton, by using edamam api to give 10 results according to the food the user typed
	 */
	@FXML
	private void searchButtonAction() {
		apiTableView.getItems().clear();
		HashMap<String, String> result;

		result = ApiIngredients.apiConnectionAndTest(foodField.getText());

		for (Map.Entry<String, String> entry : result.entrySet()) {
			String quantity = quantityField.getText()
					.concat(" " + unitChoiceBox.getSelectionModel().getSelectedItem().toString());
			Ingredients addListView = new Ingredients(entry.getKey(), entry.getValue(), quantity,
					datePicker.getValue().toString());
			getApiTableView().getItems().add(addListView);
			System.out.println(entry.getKey() + "    " + entry.getValue());
		}


	}

	/**
	 * This method handles the delButton, wich deletes the selected ingredient from the database and tableview
	 */
	@FXML
	private void delButtonAction() {
		Ingredients selectedIngredient = pantryTableView.getSelectionModel().getSelectedItem();

		DatabaseController.deleteSelectedRow(selectedIngredient.getIdApi());

		pantryTableView.getItems().clear();
		try {

			CachedRowSet rs = DatabaseController.selectAllRows();
			while (rs.next()) {
				Ingredients toAdd = new Ingredients(rs.getString("apiID"), rs.getString("foodName"),
						rs.getString("quantity"), rs.getString("expirationDate"));
				getPantryView().getItems().add(toAdd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}