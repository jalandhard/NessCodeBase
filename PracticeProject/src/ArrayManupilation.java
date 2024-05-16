
public class ArrayManupilation {

	public static void main(String[] args) {
		int[] arr = { 3, 4, 5, 6, 7, 1, 2 };
		int d = 2, tmp;

		for (int i = 0; i < d; i++) {
			tmp = arr[0];
			for (int j = 0; j < arr.length - 1; j++) {
				arr[j] = arr[j + 1];
			}
			arr[arr.length - 1] = tmp;
		}

		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + ",");

	}

}
