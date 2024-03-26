package samplepackage;

import java.util.HashMap;
import java.util.Map;

public class Sample1 {
	
	public String str = "My name is Jalandhar";
	
	private void reversed() {
		// TODO Auto-generated method stub
		String[] strArray = str.split(" ");
		
		for(String str: strArray) {
			for(int i = str.length() - 1 ; i >= 0 ; i--) {
				System.out.print(str.charAt(i));
			}
			System.out.print(" ");
		}
	}
	
	private void noOfOccuranes() {
		HashMap<Character, Integer> hashmap = new HashMap<>();
		for(int i = 0; i < str.length(); i++) {
			if(hashmap.containsKey(str.charAt(i)))
				hashmap.put(str.charAt(i), hashmap.get(str.charAt(i)) + 1);
			else
				hashmap.put(str.charAt(i), 1);
		}
		
		for(Map.Entry<Character, Integer> entry : hashmap.entrySet())
			System.out.println("CHaracter - " + entry.getKey() + " Occured - " + entry.getValue());

	}
	
	public static void main(String[] args) {
		Sample1 sample = new Sample1();
		sample.reversed();
		sample.noOfOccuranes();
	}

}
