package restAssured;

public class WeatherPostResponse {
	private String ID;
	 private String updated_at;
	 private String created_at;
	 private String user_id;
	 private String external_id;
	 private String name;
	 private double latitude;
	 private double longitude;
	 private float altitude;
	 private float rank;
	 private float source_type;


	 // Getter Methods 

	 public String getID() {
	  return ID;
	 }

	 public String getUpdated_at() {
	  return updated_at;
	 }

	 public String getCreated_at() {
	  return created_at;
	 }

	 public String getUser_id() {
	  return user_id;
	 }

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

	 public float getRank() {
	  return rank;
	 }

	 public float getSource_type() {
	  return source_type;
	 }

	 // Setter Methods 

	 public void setID(String ID) {
	  this.ID = ID;
	 }

	 public void setUpdated_at(String updated_at) {
	  this.updated_at = updated_at;
	 }

	 public void setCreated_at(String created_at) {
	  this.created_at = created_at;
	 }

	 public void setUser_id(String user_id) {
	  this.user_id = user_id;
	 }

	 public void setExternal_id(String external_id) {
	  this.external_id = external_id;
	 }

	 public void setName(String name) {
	  this.name = name;
	 }

	 public void setLatitude(double latitude) {
	  this.latitude = latitude;
	 }

	 public void setLongitude(double longitude) {
	  this.longitude = longitude;
	 }

	 public void setAltitude(float altitude) {
	  this.altitude = altitude;
	 }

	 public void setRank(float rank) {
	  this.rank = rank;
	 }

	 public void setSource_type(float source_type) {
	  this.source_type = source_type;
	 }
}
