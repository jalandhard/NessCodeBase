package samplepackage;

import java.util.LinkedHashMap;
import java.util.Map;

public class Sample {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int number = 3999;
		System.out.println(number >= 10?calculate(number - 10):number - 1);
	}
	
	public static int calculate(int number) {
		Map<Integer, Integer> valueMap = new LinkedHashMap<Integer,Integer>();
		int biggestNumber = 0;
		int highestTotal = 0;
		int actualNumber = number + 10;
		int len = String.valueOf(actualNumber).length();
		StringBuilder num = new StringBuilder();
		//int num = 0;
		int sum = 0;
		for(int i = 0; i < len - 1; i++) {
			sum = sum + 9;
			//num = 9 + i * 90;
			/*
			 * if(i == 0) { num = 9; } else num = (9 * 10^i) + (9 * 10^(i-1)) + 9;
			 */
			num.append(9);
		}
		valueMap.put(Integer.parseInt(num.toString()), sum);
		//valueMap.put(num, sum);
		while (number < actualNumber) {
			sum = 0;
			String numStr = String.valueOf(number);
			for (int i = 0; i < numStr.length(); i++) {
				sum = sum + Integer.parseInt(String.valueOf(numStr.charAt(i)));
			}
			valueMap.put(Integer.parseInt(numStr), sum);
			number++;
		}
		for(Map.Entry<Integer, Integer> entry : valueMap.entrySet()) {
			if(highestTotal <= entry.getValue()){
				biggestNumber = entry.getKey();
				highestTotal = entry.getValue();
			}
		}
		return biggestNumber;
	}
}
