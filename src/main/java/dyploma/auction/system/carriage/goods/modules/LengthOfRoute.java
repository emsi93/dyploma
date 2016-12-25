package dyploma.auction.system.carriage.goods.modules;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class LengthOfRoute {

	public static void main(String[] args) throws IOException{
		System.out.println(LengthOfRoute.getLength("£ódŸ,%20Ofiarna", "Tomaszów%20Mazowiecki"));
	}
	
	public static Double getLength(String A, String B) throws IOException{
		
		String url = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=%C5%81%C3%B3d%C5%BA,%20Ofiarna,DC&destinations=Tomasz%C3%B3w%20Mazowiecki&key=AIzaSyBQAR809YkzO5lbIQ_dht4OlSFEaznt2T4";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		return Double.parseDouble(parse(response))/1000;
	}
	
	private static String parse(StringBuffer response)
	{
		String string = response.toString();
		JSONObject json = new JSONObject(string);
		JSONArray rows = json.getJSONArray("rows");
		String s = rows.toString().substring(1,rows.toString().length()-1);
		JSONObject json2 = new JSONObject(s);
		JSONArray elements = json2.getJSONArray("elements");
		String s2 = elements.toString().substring(1,elements.toString().length()-1);
		JSONObject json3 = new JSONObject(s2);
		JSONObject distance = new JSONObject(json3.get("distance").toString());
		return distance.get("value").toString();
	}
	
}
