package tse.crewmatse.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;



public class ApiIngredients {

	
	private static HttpURLConnection connection;
	private static HashMap<String, String> result;
	
	public static HashMap<String, String> apiConnectionAndTest(String keyword) {
	
		BufferedReader reader;
		String line;
		StringBuffer responseContent = new StringBuffer();
		 
		try {
			// the API url 
			URL url = new URL("https://api.edamam.com/api/food-database/v2/parser?nutrition-type=logging&ingr="+keyword+"&app_id=6b68bbde&app_key=5fe7b8c81ef2bbb009195b055c52b753");
			connection = (HttpURLConnection) url.openConnection();
			
			// set request
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			
			
			int status = connection.getResponseCode();
			
			if(status > 299) {
				reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				while((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
				reader.close();
			}else {
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				while((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
				reader.close();
			}
			
			result = parse(responseContent.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			connection.disconnect();
		}
		return result;
		
	}
	
	public static HashMap<String, String>  parse(String responseBody) {
		HashMap<String, String> apiResponses = new HashMap<String, String>();
		JSONObject  data = new JSONObject(responseBody);
		JSONArray  ingredients = data.getJSONArray("hints");
		for (int i = 0; i < 10; i++) {
			JSONObject ingredient =  ingredients.getJSONObject(i);
			JSONObject food = ingredient.getJSONObject("food");
			String foodId = food.getString("foodId");
			String foodName = food.getString("label");
			apiResponses.put(foodId, foodName);
		}

		return apiResponses;
	}
	
	
	

}
