/**
 * @author Thomas
 *
 */
package tse.crewmatse.frigomanager.util;

import java.util.ArrayList;

public class Recette {
		private String name;
		private int idApi;
		private ArrayList<String> listIngredient;
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public int getIdApi() {
			return idApi;
		}
		
		public void setIdApi(int idApi) {
			this.idApi = idApi;
		}
		
		public ArrayList<String> getListIngredient() {
			return listIngredient;
		}
		
		public void setListIngredient(ArrayList<String> listIngredient) {
			this.listIngredient = listIngredient;
		}
		
		public Recette(String name, int idApi, ArrayList<String> listIngredient) {
			super();
			this.name = name;
			this.idApi = idApi;
			this.listIngredient = listIngredient;
		}
		
		public void display() {
			System.out.println("Id : "+this.idApi+" | Name : "+this.name+" | Ingredients :");
			for (int i = 0;i<this.listIngredient.size();i++) {
				System.out.println(this.listIngredient.get(i));
			};
		}
		
		
		
}
