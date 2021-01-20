/**
 * @author Thomas
 *
 */
package tse.crewmatse.frigomanager.api;



import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import javafx.scene.image.Image;
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
		String url="https://api.spoonacular.com/recipes/findByIngredients?apiKey=7bf95295ec3e40eaad42f4c49b80c864&ingredients=";
		for (int i =0;i<ingredients.size();i++) {
			if (i!=0) {
				url+=",+";
			}
			url+=ingredientToUrl(ingredients.get(i));
		}
		url+="&ranking=1";
		return url;
	}
	
	public static String urlSteps(int apiId) {
		String url ="https://api.spoonacular.com/recipes/"+apiId+"/analyzedInstructions?apiKey=7bf95295ec3e40eaad42f4c49b80c864";
		return url;
	};
	
	public static String urlCal(int apiId) {
		String url ="https://api.spoonacular.com/recipes/"+apiId+"/nutritionWidget.json?apiKey=7bf95295ec3e40eaad42f4c49b80c864";
		return url;
	};
	
	public static String urlInf(int apiId) {
		String url ="https://api.spoonacular.com/recipes/"+apiId+"/information?apiKey=7bf95295ec3e40eaad42f4c49b80c864";
		return url;
		// 81864c9e51a048cda9377275626cd6b8
		// 244bcc1fa9f84039951a54e8926203dc
		// 7bf95295ec3e40eaad42f4c49b80c864
		// b08d5d7f88ee4a9f86823069e845b548
	};
	
	private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }


    
    
    public static JSONArray urltoJsonArray(String url) throws IOException, JSONException {
        InputStream myurl = new URL(url).openStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(myurl, Charset.forName("UTF-8")));
        String jsonText = readAll(rd);
        JSONArray json = new JSONArray(jsonText);
        return json;       
    }
    
    public static JSONObject urltoJsonObject(String url) throws IOException, JSONException {
        InputStream myurl = new URL(url).openStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(myurl, Charset.forName("UTF-8")));
        String jsonText = readAll(rd);
        JSONObject json = new JSONObject(jsonText);
        return json;       
    }
    
    
    public static ArrayList<Recette> parse(JSONArray recettes) throws JSONException, IOException {
    	ArrayList<Recette> result = new ArrayList<Recette>();
    	for (int i = 0;i<recettes.length();i++) {
    		
    		int id = recettes.getJSONObject(i).getInt("id");
    		String name = recettes.getJSONObject(i).getString("title");
    		
    		JSONArray usedIngredient = recettes.getJSONObject(i).getJSONArray("usedIngredients");
    		JSONArray missedIngredient = recettes.getJSONObject(i).getJSONArray("missedIngredients");
    		JSONArray steps = urltoJsonArray(urlSteps(id));
    		JSONObject inf = urltoJsonObject(urlInf(id));
    		JSONObject cal = urltoJsonObject(urlCal(id));
    		JSONArray ing = inf.getJSONArray("extendedIngredients");
    		ArrayList<String> listIngredient = new ArrayList<>();
    		ArrayList<String> listMissedIngredient = new ArrayList<>();
    		ArrayList<String> listUsedIngredient = new ArrayList<>();
    		ArrayList<String> listSteps = new ArrayList<>();
    		ArrayList<Double> listQuantity = new ArrayList<>();
    		ArrayList<String> listUnits = new ArrayList<>();
    		String imageUrl = inf.getString("image");
    		Image image = new Image(imageUrl);
    		int healthscore = inf.getInt("healthScore");
    		int servings = inf.getInt("servings");
    		String calories = cal.getString("calories");
    		for (int j = 0;j<ing.length();j++) {
    			listIngredient.add(ing.getJSONObject(j).getString("originalName"));
    			listQuantity.add(ing.getJSONObject(j).getJSONObject("measures").getJSONObject("us").getDouble("amount"));
    			listUnits.add(ing.getJSONObject(j).getJSONObject("measures").getJSONObject("us").getString("unitLong"));
    		}
    		for (int j = 0;j<usedIngredient.length();j++) {
    			listUsedIngredient.add(usedIngredient.getJSONObject(j).getString("name"));
    		};
    		for (int j = 0;j<missedIngredient.length();j++) {
    			listMissedIngredient.add(missedIngredient.getJSONObject(j).getString("name"));
    		};
    		for (int j = 0;j<steps.length();j++) {
    			JSONArray stepsInformation = steps.getJSONObject(j).getJSONArray("steps");
    			for (int k = 0 ;k<stepsInformation.length();k++) {
    				listSteps.add(stepsInformation.getJSONObject(k).getString("step"));
    			};
    		};
    		
    		listMissedIngredient = new ArrayList<String>(new HashSet<String>(listMissedIngredient));
    		listUsedIngredient = new ArrayList<String>(new HashSet<String>(listUsedIngredient));
    		listSteps = new ArrayList<String>(new HashSet<String>(listSteps));
    		
    		Recette r = new Recette(name,id,listIngredient,listQuantity,listUnits,listMissedIngredient,listUsedIngredient,listSteps,servings,healthscore,image,calories);
    		result.add(r);
    	};
    	return result;
    };
    


}
