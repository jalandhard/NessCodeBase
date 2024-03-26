package samplepackage;

import java.util.ArrayList;
import java.util.List;

public class NumberSequencing {
	
	static int solve(List<Integer> ar) {
		int num = 0;
		for(int i = 1; i < ar.size() - 1; i++) {
			num = (ar.get(i - 1) != ar.get(i) && ar.get(i) != ar.get(i + 1))?i:0;
			if(num > 0) {
				break;
			}
		}
		return num;
	}
	
	public static void main(String[] args) {
		List<Integer> arr1List = new ArrayList<Integer>();
		arr1List.add(1);
		arr1List.add(0);
		arr1List.add(1);
		arr1List.add(0);
		System.out.println(solve(arr1List));
	}

}
