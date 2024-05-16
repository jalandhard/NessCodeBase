import java.util.ArrayList;
import java.util.List;

public class Sample {

	public static void main(String[] args) {
//		List<Integer> listOfIntergers = new ArrayList<>();
//		
//		int num = 0;
//		
//		listOfIntergers.add(2);
//		listOfIntergers.add(5);
//		listOfIntergers.add(4);
//		listOfIntergers.add(6);
//		
//		List<Integer> numbersUsed = new ArrayList<Integer>();
//		
//		for(int i = 0; i < listOfIntergers.size() * 3; i++) {
//			for(int j = 0; j < listOfIntergers.size() ; j++) {
//				if(num > 100)
//					break;
//				if(numbersUsed.contains(listOfIntergers.get(j)))
//					continue;
//				num = num * 10 + listOfIntergers.get(j);
//				numbersUsed.add(listOfIntergers.get(j));
//			}
//			System.out.println(num);
//			num = 0;
//			numbersUsed.clear();
//		}

//		StringSample();

		StringSampleModified();

	}

	public static void StringSample() {
		List<String> listOfChars = new ArrayList<String>();

		listOfChars.add("2");
		listOfChars.add("5");
		listOfChars.add("4");
		listOfChars.add("6");

		String str = "";

		System.out.println("Inside String Sample");

		for (int i = 0; i < listOfChars.size(); i++) {
			if (listOfChars.get(i).equals("0"))
				continue;
			str = str + listOfChars.get(i);
			for (int k = 0; k < factorial(listOfChars.size() - 1); k++) {
				for (int j = 0; j < 4; j++) {
					if (j != i) {
						str = str + listOfChars.get(j);
						for (int l = 0; l < factorial(listOfChars.size() - 2); l++) {
							for (int m = 0; m < listOfChars.size(); m++) {
								if (m != i && m != j) {
									str = str + listOfChars.get(m);
									System.out.println(str);
									break;
								}
								str = str.substring(0, 2);
							}
						}
					}
				}
				str = listOfChars.get(i);
			}
			str = "";
		}

	}

	public static int factorial(int n) {
		int tmp = n;
		int fact = 1;
		while (tmp > 1) {
			fact = fact * tmp;
			tmp--;
		}
		return fact;
	}

	public static void StringSampleModified() {
		List<String> listOfChars = new ArrayList<String>();
		List<Integer> indexsMapping = new ArrayList<Integer>();
		List<String> indexSequencing = new ArrayList<String>();

		listOfChars.add("2");
		listOfChars.add("5");
		listOfChars.add("4");
		listOfChars.add("6");
		listOfChars.add("0");
		listOfChars.add("2");

		String str = "";

		System.out.println("Inside String Sample");

		for (int i = 0; i < listOfChars.size(); i++) {
			if (listOfChars.get(i).equals("0"))
				continue;
			indexsMapping.add(i);
			for (int j = 0; j < listOfChars.size(); j++) {
				if (indexsMapping.contains(j))
					continue;
				indexsMapping.add(j);
				for (int k = 0; k < listOfChars.size(); k++) {
					if (indexsMapping.contains(k))
						continue;
					indexsMapping.add(k);
					str = indexsMapping.get(0) + "" + indexsMapping.get(1) + "" + indexsMapping.get(2);
					if (indexSequencing.contains(str)) {
						indexsMapping.remove(2);
						continue;
					}
					indexSequencing.add(str);
					indexsMapping.remove(2);
				}
				indexsMapping.clear();
				indexsMapping.add(i);
			}
			indexsMapping.clear();
		}
		
		System.out.println("Size of index Sequencing : " + indexSequencing.size());

		for (String string : indexSequencing) {
			System.out.println(listOfChars.get(Integer.parseInt(string.substring(0, 1)))
					+ listOfChars.get(Integer.parseInt(string.substring(1, 2)))
					+ listOfChars.get(Integer.parseInt(string.substring(2))));
		}

	}

}
