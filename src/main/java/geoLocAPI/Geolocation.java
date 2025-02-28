package geoLocAPI;

public class Geolocation {
	private final String name;
	private final double latitude;
	private final double longitude;
	private final String state;
	
	public Geolocation(String name, double latitude, double longitude, String state) {
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.state = state;
	}
	
	public String toString() {
		return String.format("Location: %s, %s (Lat: %.4f, Lon: %.4f)", name, state, latitude, longitude);
	}

	public String getName() {
		return name;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public String getState() {
		return state;
	}
}
