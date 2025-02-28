package geoLocAPI;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OpenWeatherClient {
	private static final String API_KEY = "f897a99d971b5eef57be6fafa0d83239";
	private static final String BASE_URL = "http://api.openweathermap.org/geo/1.0/";
	
	public Geolocation getGeolocation(String location) {
		
		String url = location.matches("^[a-zA-Z0-9]+$") ? BASE_URL + "zip?zip=" + location + ",GB&appid=" + API_KEY : BASE_URL + "direct?q=" + location +",US&limit=1&appid=" + API_KEY;
		System.out.println("url : " +url);
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			return parseResponse(response.body());
		}
		catch(IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private Geolocation parseResponse(String response) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response);
		if(root.isArray() && root.size() > 0) {
			JsonNode firstResult = root.get(0);
			return new Geolocation(
					firstResult.get("name").asText(),
					firstResult.get("lat").asDouble(),
					firstResult.get("lon").asDouble(),
					firstResult.has("state") ? firstResult.get("state").asText() : "N/A"
					);
		}
		return null;
		
	}

}
