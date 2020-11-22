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
import org.json.JSONArray;
import org.json.JSONException;



public class ApiRecette {
	public static String ingredientToUrl(String ingredient) {
		String ingredientUrl = "";
		for (int i = 0;i<ingredient.length();i++) {
			if (ingredient.charAt(i) != ' ') {
				ingredientUrl+=ingredient.charAt(i);
			}
			else {
				ingredientUrl+="%20";
			}
		}
		return ingredientUrl;
	}
	
	public static String urlIngredient(String ingredientUrl) {
		String url = "https://api.edamam.com/api/food-database/v2/parser?ingr="+ingredientUrl+"&app_id=3b2759b3&app_key=eaec543203e28db84d3dbc227ce467c2";
		return url;
	}
	
	public static String urlRecette(ArrayList<String> ingredients) {
		String url="https://api.spoonacular.com/recipes/findByIngredients?apiKey=81864c9e51a048cda9377275626cd6b8&ingredients=";
		for (int i =0;i<ingredients.size();i++) {
			if (i!=0) {
				url+=",+";
			}
			url+=ingredientToUrl(ingredients.get(i));
		}
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


    public static JSONObject UrltoJsonIngredient(String url) throws IOException, JSONException {
        try (InputStream myurl = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(myurl, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        }
        
    }
    
    public static JSONArray UrltoJsonRecette(String url) throws IOException, JSONException {
        try (InputStream myurl = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(myurl, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONArray json = new JSONArray(jsonText);
            return json;
        }
    }
    
    public static void displayRecettes(JSONArray recettes) throws JSONException {
    	ArrayList<String> recettesString = new ArrayList<String>();
    	for (int i=0;i<recettes.length();i++) {
    		recettesString.add(recettes.getJSONObject(i).getString("title"));
    	}
    	System.out.println(recettes.length()+" recettes ont été trouvées :");
    	for (int i=0;i<recettes.length();i++) {
    		System.out.println(recettesString.get(i));
    	}
    }
    
    /*public static void main(String[] args) throws IOException, JSONException {
        // launch();
        String ingredient = "apples";
        JSONObject json = UrltoJsonIngredient(urlIngredient(ingredientToUrl(ingredient)));
        System.out.println(json.getString("text"));
        ArrayList<String> ingredients = new ArrayList<String>();
        ingredients.add(ingredient);
        ingredients.add("flour");
        ingredients.add("sugar");
        JSONArray json2 = UrltoJsonRecette(urlRecette(ingredients));
        displayRecettes(json2);
    }*/

}
