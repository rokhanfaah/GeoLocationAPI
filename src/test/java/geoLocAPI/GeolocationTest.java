package geoLocAPI;


import static org.junit.jupiter.api.Assertions.*;

import org.testng.annotations.Test;

public class GeolocationTest {
	
	@Test
	void testValidCityState() {
		OpenWeatherClient client = new OpenWeatherClient();
		Geolocation geo = client.getGeolocation("Madison,WI");
		System.out.println(" fhhefhehfeffef : "+geo);
		assertNotNull(geo);
		assertEquals("Madison",geo.getName());
	}
	
	@Test
	void testValidZipCode() {
		OpenWeatherClient client = new OpenWeatherClient();
		Geolocation geo = client.getGeolocation("E14");
		assertNotNull(geo);
		assertEquals("New York",geo.getName());
	}
	
	@Test
	void testInvalidLocation() {
		OpenWeatherClient client = new OpenWeatherClient();
		Geolocation geo = client.getGeolocation("Invalid");
		assertNull(geo);
	}

}
