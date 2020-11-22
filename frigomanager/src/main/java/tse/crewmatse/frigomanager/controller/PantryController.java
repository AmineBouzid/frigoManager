/**
 * @author Guillaume
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
import tse.crewmatse.api.API;

import tse.crewmatse.frigomanager.util.DatabaseController;



public class PantryController implements Initializable {
	@FXML private SplitPane pantrySplitPane;
	@FXML private TableView<Ingredients> pantryTableView;
	@FXML private TableView<Ingredients> apiTableView;
	@FXML private TableColumn<Ingredients, String> colApiResult;
	@FXML private TableColumn<Ingredients, String> colFood;
	@FXML private TableColumn<Ingredients, String> colExpiration;
	@FXML private TableColumn<Ingredients, String> colQuantity;
	@FXML private DatePicker datePicker;
	@FXML private TextField quantityField;
	@FXML private ChoiceBox unitChoiceBox;
	@FXML private TextField foodField;
	@FXML private Button addButton;
	@FXML private Button searchButton;
	//@FXML private ListView apiListView;
	@FXML private Button delButton;

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
    
    public DatePicker getExpirationDate () {
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
    

    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		pantrySplitPane.setDividerPositions(0.8f);
		String[] unitsList = {"item","g", "L"};
		unitChoiceBox.getItems().addAll(unitsList);
		unitChoiceBox.getSelectionModel().selectFirst();
		colFood.setCellValueFactory( new PropertyValueFactory<Ingredients,String>("nameFood"));
		colExpiration.setCellValueFactory( new PropertyValueFactory<Ingredients,String>("date"));
		colQuantity.setCellValueFactory( new PropertyValueFactory<Ingredients,String>("quantity"));
		colApiResult.setCellValueFactory( new PropertyValueFactory<Ingredients,String>("nameFood"));
		
		try {
			
			ResultSet rs = DatabaseController.selectAllRows();
			while (rs.next()) { // charge la database dans la table view au lancement de la fenetre Pantry						
				Ingredients toAdd = new Ingredients(rs.getString("apiID"), rs.getString("foodName"), rs.getString("expirationDate"), rs.getString("quantity"));
				getPantryView().getItems().add(toAdd);
			}
			rs.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	@FXML
	private void addButtonAction() {

		Ingredients selectedApiResult=apiTableView.getSelectionModel().getSelectedItem();
		DatabaseController.addItemInTable(selectedApiResult.getIdApi(),selectedApiResult.getNameFood(), datePicker.getValue().toString(), quantityField.getText());			
		Ingredients toAdd = new Ingredients(selectedApiResult.getIdApi(), selectedApiResult.getNameFood(),datePicker.getValue().toString(), quantityField.getText());
		getPantryView().getItems().add(toAdd);
		apiTableView.getItems().clear();	
		foodField.clear();
		quantityField.clear();
		datePicker.getEditor().clear();
		
	}
	
	/**
	 * 
	 */
	@FXML
	private void searchButtonAction() {
		 HashMap<String, String> result;
		 
		 result = API.apiConnectionAndTest(foodField.getText());
		 
		 for(Map.Entry<String, String> entry : result.entrySet()) {
			 //int id = Integer.parseInt(entry.getKey());
			 Ingredients addListView = new Ingredients(entry.getKey(),entry.getValue(),datePicker.getValue().toString(),quantityField.getText());
			 getApiTableView().getItems().add(addListView);
			 System.out.println(entry.getKey()+ "    " + entry.getValue());
		 }
		
		 //result.forEach(foodId,foodName)-> ;
		 
	}
	
	
	
	@FXML
	private void delButtonAction() {
		Ingredients selectedIngredient=pantryTableView.getSelectionModel().getSelectedItem();
		
			
		DatabaseController.deleteSelectedRow(selectedIngredient.getIdApi());
			
	
		
		pantryTableView.getItems().clear();
		try {
			
			ResultSet rs = DatabaseController.selectAllRows();
			while (rs.next()) {
				Ingredients toAdd = new Ingredients(rs.getString("apiID"),rs.getString("foodName"),rs.getString("expirationDate"),rs.getString("quantity"));
				getPantryView().getItems().add(toAdd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//pantryTableView.getSelectionModel().clearSelection(); does not work but why??????? 
	}
	
	
    
   
    
    
    
}