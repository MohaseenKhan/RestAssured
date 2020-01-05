package restAssured;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import io.restassured.response.Response;


public class orgWeather {
	JSONObject json = new JSONObject();
	JSONObject json2 = new JSONObject();
	String keyWithInvalid = "https://api.openweathermap.org/data/3.0/stations";
	String keyWithValid = "https://api.openweathermap.org/data/3.0/stations?appid=1099ae492f1d93cda0c15af7dbd97055";
	String deleteURL = "http://api.openweathermap.org/data/3.0/stations/";
	String getURL = "https://api.openweathermap.org/data/3.0/stations?appid=1099ae492f1d93cda0c15af7dbd97055";
	static String id = "";
	public String getNodeValueUsingJpath(String responseString, String jpath)
	{
		List<?> list = null;
		Object val = null;
		DocumentContext jsonDocContext;
		String jsonPath = "$.."+jpath;
		String val2 = null;
		jsonDocContext = JsonPath.parse(responseString);
		list = jsonDocContext.read(JsonPath.compile(jsonPath));
		if(list.size()>0)
		{
			val = list.get(0);
			if(val==null)
			{
				val2 = "";
			}
			else
				val2 = val.toString();
			return val2;
		}
		else
			return "";
	}
	
	public ArrayList<String> getMulNodeValueUsingJpath(String responseString, String jpath)
	{
		ArrayList<String> aList = new ArrayList<String>();
		DocumentContext jsonDocContext;
		String jsonPath = "$.."+jpath;
		
		jsonDocContext = JsonPath.parse(responseString);
		aList = jsonDocContext.read(JsonPath.compile(jsonPath));
		return aList;
	}
	
	public int gen() {
	    Random r = new Random( System.currentTimeMillis() );
	    return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
	}
	
	
	@SuppressWarnings("unchecked")
	@Test(priority=0,enabled=false)
	public void postwithInvalidKey()
	{
		String ran = String.valueOf(gen());
		JSONObject json = new JSONObject();
		json.put("external_id", "SF_TEST001");
		json.put("name", "San bangalore Test Station "+ran);
		json.put("latitude", 37.76);
		json.put("longitude", -111.76);
		json.put("altitude", 150);
		
		Response res = given().header("Content-Type","application/json").body(json).when().
		post(keyWithInvalid).then().assertThat().statusCode(401).extract().response();
		
		System.out.println(res.asString());
		
		String ErrorCode = getNodeValueUsingJpath(res.asString(),"cod");
		String ErrorMessage = getNodeValueUsingJpath(res.asString(),"message");
		
		System.out.println(ErrorCode + " "+ ErrorMessage);
		Assert.assertEquals("401", ErrorCode);
		Assert.assertEquals("Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.", ErrorMessage);
	}
	
	@SuppressWarnings("unchecked")
	@Test(priority=1,enabled=false)
	public void postwithValidKey()
	{
		String ran = String.valueOf(gen());
		
		json.put("external_id", "DEMO_TEST1");
		json.put("name", "San bangalore Test Station"+ran);
		json.put("latitude", 37.76);
		json.put("longitude", -111.43);
		json.put("altitude", 150);
		
		System.out.println(json);
		
		
		/*Response res = given().header("Content-Type","application/json").when().
		post(keyWithValid).then().assertThat().statusCode(201).extract().response();*/
		
		Response res = given().header("Content-Type","application/json").body(json).when()
				.post(keyWithValid).then().assertThat().statusCode(201).extract().response();
		
		System.out.println(res.asString());
		
		
		json2.put("external_id", "DEMO_TEST2");
		json2.put("name", "San bangalore Test Station"+ran);
		json2.put("latitude", 37.76);
		json2.put("longitude", -111.43);
		json2.put("altitude", 150);
		
		Response res2 = given().header("Content-Type","application/json").body(json2).when()
				.post(keyWithValid).then().assertThat().statusCode(201).extract().response();
		
		System.out.println(res2.asString());
		
		Response getRes = given().header("Content-Type","application/json").when().
				get(getURL).then().extract().response();
		
		System.out.println(getRes.asString());
		
		ArrayList<String> strName = getMulNodeValueUsingJpath(getRes.asString(),"name");
	}
	
	@Test(priority=2,enabled=false)
	public void deleteFromGet()
	{
		Response res = given().header("Content-Type","application/json").when().
				get(getURL).then().extract().response();
		
		System.out.println(res.asString());
		
		ArrayList<String> str = getMulNodeValueUsingJpath(res.asString(),"id");
		for(int i=0;i<str.size();i++)
		{
			given().param("appid", "1099ae492f1d93cda0c15af7dbd97055").when().delete(deleteURL+str.get(i))
			.then().assertThat().statusCode(204);
			
		}
		
		//Post delete
		for(int i=0;i<str.size();i++)
		{
			given().param("appid", "1099ae492f1d93cda0c15af7dbd97055").when().delete(deleteURL+str.get(i))
			.then().assertThat().statusCode(404);
			
		}
		
	}
	
	@Test(priority=3)
	public void postUsingPojoClass() throws JsonProcessingException
	{
String ran = String.valueOf(gen());
		
		/*json.put("external_id", "DEMO_TEST1");
		json.put("name", "San bangalore Test Station"+ran);
		json.put("latitude", 37.76);
		json.put("longitude", -111.43);
		json.put("altitude", 150);*/
		
		Weather weather = new Weather();
		weather.setExternal_id("DEMO_TEST2");
		weather.setName("San bangalore Test Station"+ran);
		weather.setLatitude(10.20);
		weather.setLongitude(50.60);
		weather.setAltitude(200);
		Gson g = new Gson();
				
		String json3 = g.toJson(weather);
		/*ObjectMapper jsonObj = new ObjectMapper();
		String json3 = jsonObj.writerWithDefaultPrettyPrinter().writeValueAsString(weather);*/
		System.out.println(json3);

		/*Response res = given().header("Content-Type","application/json").when().
		post(keyWithValid).then().assertThat().statusCode(201).extract().response();*/
		
		/*Response res = given().header("Content-Type","application/json").body(json3).when()
				.post(keyWithValid).then().assertThat().statusCode(201).extract().response();*/
		
		Response res = given().header("Content-Type","application/json").body(json3).when().post(keyWithValid);
		
		
		
		WeatherPostResponse response = g.fromJson(res.asString(),WeatherPostResponse.class);
		//response = new ObjectMapper().readValue(res.asString(), WeatherPostResponse.class);
		System.out.println(response.getName());
		System.out.println(weather.getLatitude() +" "+ response.getLatitude());
		Assert.assertEquals(weather.getName(), response.getName());
		Assert.assertEquals(weather.getExternal_id(), response.getExternal_id());
		Assert.assertEquals(weather.getLatitude(), response.getLatitude());
		Assert.assertEquals(weather.getLongitude(), response.getLongitude());
		Assert.assertEquals(weather.getAltitude(), response.getAltitude());
	}
}