/**
 * @author Guillaume
 *
 */
package tse.crewmatse.frigomanager.util;

public class Ingredients {
	public Ingredients(String idApi, String nameFood, String quantity, String date) {
		super();
		this.idApi = idApi;
		this.nameFood = nameFood;
		this.quantity = quantity;
		this.date = date;
	}
	public Ingredients(String nameFood) {
		super();
		this.nameFood = nameFood;
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
