package com.qa.rest.tests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetCallBDD {
	
	@Test
	public void test_noOfCircuitsFor2017_season() {
		given().
		when().
			get("http://ergast.com/api/f1/drivers.json").
		then().
			assertThat().
			statusCode(200).
			and().
			body("MRData.DriverTable.Drivers.driverId", hasSize(30)).
			and().
			header("content-Type", equalTo("application/json; charset=utf-8"));
	}

}
