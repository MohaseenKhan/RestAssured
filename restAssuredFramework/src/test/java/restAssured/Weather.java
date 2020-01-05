package restAssured;

public class Weather {
	private String external_id;
	 private String name;
	 private double latitude;
	 private double longitude;
	 private float altitude;


	 // Getter Methods 

	 public String getExternal_id() {
	  return external_id;
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

	 public float getAltitude() {
	  return altitude;
	 }

	 // Setter Methods 

	 public void setExternal_id(String external_id) {
	  this.external_id = external_id;
	 }

	 public void setName(String name) {
	  this.name = name;
	 }

	 public void setLatitude(double d) {
	  this.latitude = d;
	 }

	 public void setLongitude(double d) {
	  this.longitude = d;
	 }

	 public void setAltitude(float altitude) {
	  this.altitude = altitude;
	 }
}
