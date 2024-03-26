package samplepackage;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

public class JSONObjects {
	
	public static void main(String[] args) {
		Map<String,Object> cars = new HashMap<String, Object>();
		//First Car Object
		Map<String,Object> carsMap1 = new HashMap<String,Object>();
		carsMap1.put("Car Name", "i20");
		carsMap1.put("Car Varient", "Magnum");
		carsMap1.put("Reg Number", "KA01MU9661");
		carsMap1.put("Color", "Blue Grayish");
		Map<String,String> parts1 = new HashMap<String,String>();
		parts1.put("engine", "IBM Brand");
		parts1.put("tyres", "MRF");
		parts1.put("Seats", "Living Home");
		carsMap1.put("Parts", parts1);
		//Second Car Object
		Map<String,Object> carsMap2 = new HashMap<String,Object>();
		carsMap2.put("Car Name", "i20");
		carsMap2.put("Car Varient", "Sportz");
		carsMap2.put("Reg Number", "KA05MU9661");
		carsMap2.put("Color", "White Blackish");
		Map<String,String> parts2 = new HashMap<String,String>();
		parts2.put("engine", "IBM Brand");
		parts2.put("tyres", "CEAT");
		parts2.put("Seats", "Living Home");
		carsMap2.put("Parts", parts2);
		
		//Adding both cars into cars object
		cars.put("Car 1", carsMap1);
		cars.put("Car 2", carsMap2);
		
		String jsonText = JSONObject.toJSONString(cars);
		System.out.println(jsonText);
	}

}
