/**
 * @author Thomas
 *
 */
package tse.crewmatse.frigomanager.api;



import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import org.json.JSONObject;

import tse.crewmatse.frigomanager.util.Ingredients;
import tse.crewmatse.frigomanager.util.Recette;

import org.json.JSONArray;
import org.json.JSONException;



public class ApiRecette {
	public static String ingredientToUrl(Ingredients ingredient) {
		String ingredientUrl = "";
		for (int i = 0;i<ingredient.getNameFood().length();i++) {
			if (ingredient.getNameFood().charAt(i) != ' ') {
				ingredientUrl+=ingredient.getNameFood().charAt(i);
			}
			else {
				ingredientUrl+="%20";
			}
		}
		return ingredientUrl;
	}
	
	public static String urlRecette(ArrayList<Ingredients> ingredients) {
		String url="https://api.spoonacular.com/recipes/findByIngredients?apiKey=81864c9e51a048cda9377275626cd6b8&ingredients=";
		for (int i =0;i<ingredients.size();i++) {
			if (i!=0) {
				url+=",+";
			}
			url+=ingredientToUrl(ingredients.get(i));
		}
		url+="&ranking=1";
		return url;
	}
	private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }


    
    
    public static JSONArray urltoJsonRecette(String url) throws IOException, JSONException {
        InputStream myurl = new URL(url).openStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(myurl, Charset.forName("UTF-8")));
        String jsonText = readAll(rd);
        JSONArray json = new JSONArray(jsonText);
        return json;
        
        
    }
    
    
    public static ArrayList<Recette> parse(JSONArray recettes) {
    	ArrayList<Recette> result = new ArrayList<Recette>();
    	for (int i = 0;i<recettes.length();i++) {
    		
    		int id = recettes.getJSONObject(i).getInt("id");
    		String name = recettes.getJSONObject(i).getString("title");
    		
    		JSONArray usedIngredient = recettes.getJSONObject(i).getJSONArray("usedIngredients");
    		JSONArray missedIngredient = recettes.getJSONObject(i).getJSONArray("missedIngredients");
    		
    		ArrayList<String> listIngredient = new ArrayList<>();
    		ArrayList<String> listMissedIngredient = new ArrayList<>();
    		ArrayList<String> listUsedIngredient = new ArrayList<>();
    		
    		for (int j = 0;j<usedIngredient.length();j++) {
    			listIngredient.add(usedIngredient.getJSONObject(j).getString("name"));
    			listUsedIngredient.add(usedIngredient.getJSONObject(j).getString("name"));
    		};
    		for (int j = 0;j<missedIngredient.length();j++) {
    			listIngredient.add(missedIngredient.getJSONObject(j).getString("name"));
    			listMissedIngredient.add(missedIngredient.getJSONObject(j).getString("name"));
    		};
    		
    		Recette r = new Recette(name,id,listIngredient,listMissedIngredient,listUsedIngredient);
    		result.add(r);
    	};
    	return result;
    };
    
    /*public static void main(String[] args) throws JSONException, IOException {
		Ingredients ing1 = new Ingredients();
		ing1.setNameFood("apples");
		Ingredients ing3 = new Ingredients();
		ing3.setNameFood("butter");
		Ingredients ing2 = new Ingredients();
		ing2.setNameFood("flour");
		ArrayList<Ingredients> list = new ArrayList<Ingredients>();
		list.add(ing1);
		list.add(ing3);
		list.add(ing2);
		
		JSONArray r = urltoJsonRecette(urlRecette(list));
		ArrayList<Recette> listR = parse(r);
		for(int i = 0;i<listR.size();i++) {
			listR.get(i).display();
		};
	}*/

}
