/**
 * This class defines the Ingredients type
 * @author Guillaume Bayon
 *
 */
package tse.crewmatse.frigomanager.util;

public class Ingredients {
	/** 
	 * The constructor of Ingredients
	 * @param idApi A string returned by edamam api
	 * @param nameFood The name of the food, returned by edamam api
	 * @param quantity The quantity, entered by the user
	 * @param date The expiration date of the food, entered by the user
	 */
	public Ingredients(String idApi, String nameFood, String quantity, String date) {
		super();
		this.idApi = idApi;
		this.nameFood = nameFood;
		this.quantity = quantity;
		this.date = date;
	}
	
	public Ingredients() {
		super();
	}

	public String getIdApi() {
		return idApi;
	}
	public void setIdApi(String idApi) {
		this.idApi = idApi;
	}
	public String getNameFood() {
		return nameFood;
	}
	public void setNameFood(String nameFood) {
		this.nameFood = nameFood;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	private String nameFood;
	private String quantity;
	private String date;	
	private String idApi;

}
