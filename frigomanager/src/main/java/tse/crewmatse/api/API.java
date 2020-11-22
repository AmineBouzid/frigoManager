package tse.crewmatse.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;



public class API {

	
	private static HttpURLConnection connection;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader reader;
		String line;
		StringBuffer responseContent = new StringBuffer();
		 
		try {
			URL url = new URL("https://api.edamam.com/api/food-database/v2/parser?nutrition-type=logging&ingr=spaghetti&app_id=6b68bbde&app_key=5fe7b8c81ef2bbb009195b055c52b753");
			connection = (HttpURLConnection) url.openConnection();
			
			connection.setRequestMethod("GET");
			//connection.setConnectTimeout(105000);
			//connection.setReadTimeout(105000);
			
			
			int status = connection.getResponseCode();
			
			//System.out.println(status);
			if(status > 299) {
				reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				while((line = reader.readLine()) != null) {
//					System.out.println(line);
					responseContent.append(line);
				}
				reader.close();
			}else {
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				while((line = reader.readLine()) != null) {
					System.out.println(line.toString());
					responseContent.append(line);
				}
				reader.close();
			}
			parse(responseContent.toString());
			//System.out.println(responseContent.toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connection.disconnect();
		}
		
		}
	public static String parse(String responseBody) {
		JSONObject  data = new JSONObject(responseBody);

		JSONArray  ingredients = data.getJSONArray("hints");
		for (int i = 0; i < ingredients.length(); i++) {
			JSONObject ingredient =  ingredients.getJSONObject(i);
			System.out.println(ingredient.toString());
//			int id = ingredient.getInt("id");
//			//we put here data we want to get
//			System.out.println("id"+id);
		}
		return null;
	}

}
