
public class Pattern {
	
	public static void main(String[] args) {
		
		// This pattern set works only for odd numbers, doesn't work for even numbers
		char[][] charArray = new char[13][13];
		
		for(int i = 0; i < charArray.length; i++)
			for(int j = 0; j < charArray.length; j++)
				charArray[i][j] = ' ';
		
		int middle = charArray.length / 2;
		int i = 0;
		
		for(; i <= charArray.length/2; i++) {
			charArray[i][middle] = '*';
			for(int j = (middle - 1); j >= (middle - i); j--)
				charArray[i][j] = '*';
			for(int j = (middle + 1); j <= (middle + i); j++)
				charArray[i][j] = '*';
		}
		
		for(; i < charArray.length; i++) {
			charArray[i][middle] = '*';
			for(int j = (middle - 1); j >= (i - middle); j--)
				charArray[i][j] = '*';
			for(int j = (middle + 1); j < (charArray.length - (i - middle)); j++)
				charArray[i][j] = '*';
		}
		
		for(i = 0; i < charArray.length; i++) {
			for(int j = 0; j < charArray.length; j++) {
				System.out.print(charArray[i][j]);
			}
			System.out.println();
		}
		
	}

}
