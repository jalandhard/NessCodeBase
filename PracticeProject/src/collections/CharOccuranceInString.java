package collections;

import java.util.HashMap;
import java.util.Map;

public class CharOccuranceInString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String name = "Jalandhar";
		char[] charArray = name.toCharArray();
		Map<Character, Integer> hashMap = new HashMap<Character,Integer>();
		
		for(int i = 0; i < charArray.length; i++) {
			if(hashMap.containsKey(charArray[i]))
				hashMap.put(charArray[i], hashMap.get(charArray[i]) + 1);
			else
				hashMap.put(charArray[i], 1);
		}
		
		StringBuffer namebuilder = new StringBuffer(name);
		String nameRev = namebuilder.reverse().toString();
		System.out.println("Reversed String : " + nameRev);
		System.out.println("Printing all characters with their respective occurances : ");
		
		//Map.Entry<Character, Integer> entrySet = hashMap.entrySet();
		for(Map.Entry<Character, Integer> entry : hashMap.entrySet()) {
			if(entry.getValue() > 1) 
				System.out.println("Character : " + entry.getKey() + " Occured : " + entry.getValue() + " Times");
			else
				System.out.println("Character : " + entry.getKey() + " Occured : " + entry.getValue() + " Time");
		}
	}
}
