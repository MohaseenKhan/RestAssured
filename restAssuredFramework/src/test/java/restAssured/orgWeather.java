package restAssured;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.util.List;


public class orgWeather {
	
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
	
	String keyWithInvalid = "https://api.openweathermap.org/data/3.0/stations";
	String keyWithValid = "https://api.openweathermap.org/data/3.0/stations?appid=1099ae492f1d93cda0c15af7dbd97055";
	@SuppressWarnings("unchecked")
	@Test
	public void postwithInvalidKey()
	{
		JSONObject json = new JSONObject();
		json.put("external_id", "SF_TEST001");
		json.put("name", "San bangalore Test Station");
		json.put("latitude", 37.76);
		json.put("longitude", -111.76);
		json.put("altitude", 150);
		
		Response res = given().header("Content-Type","application/json").when().
		post(keyWithInvalid).then().assertThat().statusCode(401).extract().response();
		
		System.out.println(res.asString());
		
		String ErrorCode = getNodeValueUsingJpath(res.asString(),"cod");
		String ErrorMessage = getNodeValueUsingJpath(res.asString(),"message");
		
		System.out.println(ErrorCode + " "+ ErrorMessage);
	}
}