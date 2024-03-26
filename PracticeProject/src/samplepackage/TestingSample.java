package samplepackage;

public class TestingSample {
	
	public static int getRandomNumberBetweenRange(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}
	
	public static void main(String[] args) {
		System.out.println(getRandomNumberBetweenRange(2047, 2100));
		System.out.println(getRandomNumberBetweenRange(1, 12));
		System.out.println(getRandomNumberBetweenRange(1, 7));
	}

}
