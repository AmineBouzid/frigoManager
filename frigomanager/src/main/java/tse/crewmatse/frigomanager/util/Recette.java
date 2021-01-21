package tse.crewmatse.frigomanager.util;

import javafx.scene.image.Image;
import java.util.ArrayList;

/**
 * @author Thomas
 *
 */
public class Recette implements Comparable<Recette>{
	//Attributes List
	private String name;
	private int idApi;
	private ArrayList<String> listIngredient;
	private ArrayList<Double> listQuantity;
	private ArrayList<String> listUnits;
	private ArrayList<String> listMissedIngredient;
	private ArrayList<String> listUsedIngredient;
	private ArrayList<String> listSteps;
	private int servings;
	private int healthscore;
	private Image image;
	private String cal;
	private String summary;
	
	//Getters & Setters List
	/**
	 * @return : String 'Value of calories of the Recipe'
	 */
	public String getCal() {
		return cal;
	}
	/**
	 * @param cal : String 'Value of calories of the Recipe'
	 */
	public void setCal(String cal) {
		this.cal = cal;
	}
	/**
	 * @return String 'Name of the Recipe'
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name : String 'Name of the Recipe'
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return integer 'Id of the Recipe in the API'
	 */
	public int getIdApi() {
		return idApi;
	}
	/**
	 * @param idApi : integer 'Id of the Recipe in the API'
	 */
	public void setIdApi(int idApi) {
		this.idApi = idApi;
	}
	/**
	 * @return List of ingredients of the recipe
	 */
	public ArrayList<String> getListIngredient() {
		return listIngredient;
	}
	/**
	 * @param listIngredient : List of ingredients of the recipe
	 */
	public void setListIngredient(ArrayList<String> listIngredient) {
		this.listIngredient = listIngredient;
	}
	/**
	 * @return Quantity list of each ingredient of the recipe
	 */
	public ArrayList<Double> getListQuantity() {
		return listQuantity;
	}
	/**
	 * @param listQuantity : Quantity list of each ingredient of the recipe
	 */
	public void setListQuantity(ArrayList<Double> listQuantity) {
		this.listQuantity = listQuantity;
	}
	/**
	 * @return Unit list of each ingredient of the recipe
	 */
	public ArrayList<String> getListUnits() {
		return listUnits;
	}
	/**
	 * @param listUnits : Unit list of each ingredient of the recipe
	 */
	public void setListUnits(ArrayList<String> listUnits) {
		this.listUnits = listUnits;
	}
	/**
	 * @return list of ingredients missing in our pantry for the recipe
	 */
	public ArrayList<String> getListMissedIngredient() {
		return listMissedIngredient;
	}
	/**
	 * @param listMissedIngredient : list of ingredients missing in our pantry for the recipe
	 */
	public void setListMissedIngredient(ArrayList<String> listMissedIngredient) {
		this.listMissedIngredient = listMissedIngredient;
	}
	/**
	 * @return list of ingredients owned in our pantry for the recipe
	 */
	public ArrayList<String> getListUsedIngredient() {
		return listUsedIngredient;
	}
	/**
	 * @param listUsedIngredient : list of ingredients owned in our pantry for the recipe
	 */
	public void setListUsedIngredient(ArrayList<String> listUsedIngredient) {
		this.listUsedIngredient = listUsedIngredient;
	}
	/**
	 * @return list of each step of the recipe
	 */
	public ArrayList<String> getListSteps() {
		return listSteps;
	}
	/**
	 * @param listSteps : list of each step of the recipe
	 */
	public void setListSteps(ArrayList<String> listSteps) {
		this.listSteps = listSteps;
	}
	/**
	 * @return integer 'number of servings for the recipe'
	 */
	public int getServings() {
		return servings;
	}
	/**
	 * @param servings : integer 'number of servings for the recipe'
	 */
	public void setServings(int servings) {
		this.servings = servings;
	}
	/**
	 * @return integer 'Health Score of the recipe'
	 */
	public int getHealthscore() {
		return healthscore;
	}
	/**
	 * @param healthscore : integer 'Health Score of the recipe'
	 */
	public void setHealthscore(int healthscore) {
		this.healthscore = healthscore;
	}
	/**
	 * @return the image of the recipe
	 */
	@SuppressWarnings("exports")
	public Image getImage() {
		return image;
	}
	/**
	 * @param image : the image of the recipe
	 */
	public void setImage(@SuppressWarnings("exports") Image image) {
		this.image = image;
	}
	/**
	 * @return the summary of the recipe
	 */
	public String getSummary() {
		return summary;
	}
	/**
	 * @param summary : the summary of the recipe
	 */

	public void setSummary(String summary) {
		this.summary = summary;
	}

	

	/**
	 * Constructor of the class with all attributes
	 * @param name : String 'Name of the recipe'
	 * @param idApi : integer 'Id of the Recipe in the API'
	 * @param listIngredient : List of ingredients of the recipe
	 * @param listQuantity : Quantity list of each ingredient of the recipe
	 * @param listUnits : Unit list of each ingredient of the recipe
	 * @param listMissedIngredient : list of ingredients missing in our pantry for the recipe
	 * @param listUsedIngredient : list of ingredients missing in our pantry for the recipe
	 * @param listSteps : list of each step of the recipe
	 * @param servings : integer 'number of servings for the recipe'
	 * @param healthscore : integer 'Health Score of the recipe'
	 * @param image : the image of the recipe
	 * @param cal : String 'Value of calories of the Recipe'
	 */
	public Recette(String name, int idApi, ArrayList<String> listIngredient, ArrayList<Double> listQuantity,
			ArrayList<String> listUnits, ArrayList<String> listMissedIngredient,
			ArrayList<String> listUsedIngredient, ArrayList<String> listSteps, int servings, int healthscore,
			@SuppressWarnings("exports") Image image, String cal) {
		super();
		this.name = name;
		this.idApi = idApi;
		this.listIngredient = listIngredient;
		this.listQuantity = listQuantity;
		this.listUnits = listUnits;
		this.listMissedIngredient = listMissedIngredient;
		this.listUsedIngredient = listUsedIngredient;
		this.listSteps = listSteps;
		this.servings = servings;
		this.healthscore = healthscore;
		this.image = image;
		this.cal = cal;
	}	
	/**
	 * Displays in console the main variables of the recipe
	 */
	public void display() {
		System.out.println("Id : "+this.idApi+" | Name : "+this.name+ " | Servings : "+this.servings+" | Healthscore : "+this.healthscore+" | Ingredients :");
		for (int i = 0;i<this.listIngredient.size();i++) {
			System.out.println(this.listIngredient.get(i));
		};
		for (int i = 0;i<this.listSteps.size();i++) {
			System.out.println(this.listSteps.get(i));
		};
	}
	/** Overload Constructor of the class with fewer parameters
	 * @param name : String 'Name of the recipe'
	 * @param idApi : integer 'Id of the Recipe in the API'
	 */
	public Recette(String name, int idApi) {
		super();
		this.name = name;
		this.idApi = idApi;
	}
	/**
	 * @param r : Recipe 
 	 * @return integer 'Health Score of the recipe'
	 */
	public int compareTo(Recette r) {
		return (this.getHealthscore()-r.getHealthscore());
	}
	public Recette(String name2, int id, ArrayList<String> listIngredient2,@SuppressWarnings("exports") Image image,String summary) {
			super();
			this.name = name2;
			this.idApi = id;
			this.listIngredient = listIngredient2;
			this.image = image;
			this.summary = summary;
		}

			
}
