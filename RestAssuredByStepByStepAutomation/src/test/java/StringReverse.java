import java.util.ArrayList;
import java.util.List;

public class StringReverse {
	
	public static void main(String[] args) {
		String str = "I am based out from Mumbai based city";
		
		String[] strArray = str.split(" ");
		
		for(int i = strArray.length - 1; i >= 0; i--) {
			System.out.print(strArray[i]);
			if(i > 0)
				System.out.print(" ");
		}
		System.out.println();
		
		List<String> strList = new ArrayList<String>();
		
		for(String string : strArray)
			strList.add(string);
		
		System.out.println(strList);
		
		System.out.println(strList.contains("based"));
		
		System.out.println("index of based : " + strList.indexOf("based"));
		System.out.println("Last index of based : " + strList.lastIndexOf("based"));
	}

}
