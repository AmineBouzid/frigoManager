/**
 * @author Thomas
 *
 */
package tse.crewmatse.frigomanager.util;

import javafx.scene.image.Image;
import java.util.ArrayList;

public class Recette {
		private String name;
		private int idApi;
		private ArrayList<String> listIngredient;
		private ArrayList<String> listIngredientQuantity;
		private ArrayList<String> listMissedIngredient;
		private ArrayList<String> listUsedIngredient;
		private ArrayList<String> listSteps;
		private int servings;
		private int healthscore;
		private Image image;
		
		
		
		
		public ArrayList<String> getListIngredientQuantity() {
			return listIngredientQuantity;
		}


		public void setListIngredientQuantity(ArrayList<String> listQuantity) {
			this.listIngredientQuantity = listQuantity;
		}




		public void display() {
			System.out.println("Id : "+this.idApi+" | Name : "+this.name+ " | Servings : "+this.servings+" | Healthscore : "+this.healthscore+" | Ingredients :");
			for (int i = 0;i<this.listIngredient.size();i++) {
				System.out.println(this.listIngredient.get(i));
			};
			for (int i = 0;i<this.listSteps.size();i++) {
				System.out.println(this.listSteps.get(i));
			};
		}




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




		public ArrayList<String> getListMissedIngredient() {
			return listMissedIngredient;
		}




		public void setListMissedIngredient(ArrayList<String> listMissedIngredient) {
			this.listMissedIngredient = listMissedIngredient;
		}




		public ArrayList<String> getListUsedIngredient() {
			return listUsedIngredient;
		}




		public void setListUsedIngredient(ArrayList<String> listUsedIngredient) {
			this.listUsedIngredient = listUsedIngredient;
		}




		public ArrayList<String> getListSteps() {
			return listSteps;
		}




		public void setListSteps(ArrayList<String> listSteps) {
			this.listSteps = listSteps;
		}




		public int getServings() {
			return servings;
		}




		public void setServings(int servings) {
			this.servings = servings;
		}




		public int getHealthscore() {
			return healthscore;
		}




		public void setHealthscore(int helthscore) {
			this.healthscore = helthscore;
		}




		public Image getImage() {
			return image;
		}




		public void setImage(Image image) {
			this.image = image;
		}




		


		public Recette(String name, int idApi, ArrayList<String> listIngredient, ArrayList<String> listQuantity,
				ArrayList<String> listMissedIngredient, ArrayList<String> listUsedIngredient,
				ArrayList<String> listSteps, int servings, int healthscore, Image image) {
			super();
			this.name = name;
			this.idApi = idApi;
			this.listIngredient = listIngredient;
			this.listIngredientQuantity = listQuantity;
			this.listMissedIngredient = listMissedIngredient;
			this.listUsedIngredient = listUsedIngredient;
			this.listSteps = listSteps;
			this.servings = servings;
			this.healthscore = healthscore;
			this.image = image;
		}
		public Recette(String name, int idApi) {
			super();
			this.name = name;
			this.idApi = idApi;

		}




		
		
		
		
}
