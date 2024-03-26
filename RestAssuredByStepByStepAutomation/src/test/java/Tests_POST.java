import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Tests_POST {
	
	Response response;
	
	@AfterMethod
	void test01() {
		response = RestAssured.get("https://reqres.in/api/users?page=2");
		
		System.out.println(response.asPrettyString());
		//System.out.println(response.getBody().asString());
		//System.out.println(response.getStatusCode());
		System.out.println(response.getStatusLine());
		System.out.println(response.getHeader("content-type"));
		System.out.println(response.getTime());
		System.out.println(response.getContentType());
	}
	
	@Test
	void test01_post() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "Jalandhar");
		map.put("job", "QA Lead");
		
		System.out.println(map);
		
		JSONObject requestBody = new JSONObject(map);
		
		System.out.println(requestBody);
		
		RestAssured.given().header("Content-Type","application/json").
		contentType(ContentType.JSON).
		accept(ContentType.JSON).
		body(requestBody.toJSONString()).
		when().
		post("https://reqres.in/api/users").
		then().
		statusCode(201);
	}
	
	@Test
	void test01_put() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "Jalandhar");
		map.put("job", "QA Lead - Put");
		
		System.out.println(map);
		
		JSONObject requestBody = new JSONObject(map);
		
		System.out.println(requestBody);
		
		RestAssured.given().header("Content-Type","application/json").
		contentType(ContentType.JSON).
		accept(ContentType.JSON).
		body(requestBody.toJSONString()).
		when().
		put("https://reqres.in/api/users/2").
		then().
		statusCode(200).log().all();
	}
	
	@Test
	void test01_patch() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "Jalandhar");
		map.put("job", "QA Lead - Patch");
		
		System.out.println(map);
		
		JSONObject requestBody = new JSONObject(map);
		
		System.out.println(requestBody);
		
		RestAssured.given().header("Content-Type","application/json").
		contentType(ContentType.JSON).
		accept(ContentType.JSON).
		body(requestBody.toJSONString()).
		when().
		patch("https://reqres.in/api/users/2").
		then().
		statusCode(200).log().all();
	}
	
	@Test
	void test01_delete() {
		
		RestAssured.given().
		delete("https://reqres.in/api/users/2").
		then().
		statusCode(204);
	}

}