import java.util.HashMap;
import java.util.Map;

public class RepetativeString {
	
	public static void main(String[] args) {
		String str = "Proficient & meticulous professional having over 12 years of experience years  Testing years Testing";
		
		String[] strArray = str.split(" ");
		
		Map<String, Integer> stringMap = new HashMap<String, Integer>();
		
		for(String string : strArray) {
			if(stringMap.containsKey(string))
				stringMap.put(string, stringMap.get(string) + 1);
			else
				stringMap.put(string, 1);
		}
		
		int large = 0;
		
		for(Map.Entry<String, Integer> entry : stringMap.entrySet()) {
			if(entry.getValue() > large)
				large = entry.getValue();
		}
		
		for(Map.Entry<String, Integer> entry : stringMap.entrySet())
			if(entry.getValue() == large) {
				System.out.println("Largest String is : " + entry.getKey() + " with " + entry.getValue());
				break;
			}
	}

}
