import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.pojo.Label;
import com.java.pojo.LabelList;
import com.java.pojo.LabelsList;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Testing {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		deserializeSingleJsonObject();
		deserializeJsonArrayObject();
		deserializeJsonComplexArrayObject();
	}

	public static void deserializeSingleJsonObject() throws Exception {
		ObjectMapper mapper = new ObjectMapper();

		Label label = new Label();

		Response response = RestAssured.given().baseUri("http://localhost:8080").basePath("/examples/Stub.jsp").when()
				.get().then().extract().response();
		System.out.println("API Response : " + response.getBody().asString());

		label = mapper.readValue(response.getBody().asString(), Label.class);

		System.out.println(label);
	}
	
	public static void deserializeJsonArrayObject() throws Exception {
		ObjectMapper mapper = new ObjectMapper();

		Response response = RestAssured.given().baseUri("http://localhost:8080").basePath("/examples/StubArrays.jsp").when()
				.get().then().extract().response();
		System.out.println("API Response : " + response.getBody().asString());
		
		LabelList labelList = mapper.readValue(response.getBody().asString(), LabelList.class);

		System.out.println(labelList);
	}
	
	public static void deserializeJsonComplexArrayObject() throws Exception {
		ObjectMapper mapper = new ObjectMapper();

		Response response = RestAssured.given().baseUri("http://localhost:8080").basePath("/examples/StubComplexArrays.jsp").when()
				.get().then().extract().response();
		System.out.println("API Response : " + response.getBody().asString());
		
		LabelsList labelsList = mapper.readValue(response.getBody().asString(), LabelsList.class);

		System.out.println(labelsList);
	}

}
