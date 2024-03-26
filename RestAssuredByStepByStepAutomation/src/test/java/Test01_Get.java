import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;

public class Test01_Get {
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
	
	//@Test
	void test02() {
		response.then().body("data.id[0]", Matchers.equalTo(7))
		.and().body("data.first_name", Matchers.hasItems("Tobias","Michael","Ferguson"));
	}

}
