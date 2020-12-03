package tse.crewmatse.frigomanager.util;

import java.util.ArrayList;

public class Recette {
		public Recette(String name, int idApi, ArrayList<Ingredients> listIngredient) {
			this.name = name;
			this.idApi = idApi;
			this.listIngredient = listIngredient;
		}

		private String name;
		private int idApi;
		private ArrayList<Ingredients> listIngredient;
		
		public int getIdApi() {
			return idApi;
		}
		
		public ArrayList<Ingredients> getListIngredient() {
			return listIngredient;
		}
		
		public String getName() {
			return name;
		}
		
		public void setIdApi(int idApi) {
			this.idApi = idApi;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public void setListIngredient(ArrayList<Ingredients> listIngredient) {
			this.listIngredient = listIngredient;
		}
		
		
}
