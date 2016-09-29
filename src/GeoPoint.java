
public class GeoPoint {
	double latitude;
	double longitude;
	double altitude;
	
	public GeoPoint(double latitude, double longitude, double altitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
	}
	public GeoPoint(double latitude, double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude=0;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getAltitude() {
		return altitude;
	}
	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}
}
