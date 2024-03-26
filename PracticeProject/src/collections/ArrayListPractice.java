package collections;

import java.util.ArrayDeque;
//import java.util.ArrayList;

public class ArrayListPractice {
	
	public static void main(String[] args) {
		ArrayDeque<String> arrayList = new ArrayDeque<String>();
		arrayList.add("string1");
		arrayList.add("String3");
		System.out.println(arrayList);
		arrayList.removeLast();
		arrayList.addLast("String2");
		arrayList.push("String0");
		System.out.println(arrayList);
		arrayList.pop();
		//arrayList.add(1, "String2");
		
		System.out.println("Traversing through Array List!!!!!!!!");
		
		for(String str:arrayList) {
			System.out.println(str);
		}
	}

}
