package inheritance;

public class Child extends Parent{

	private int privateInt = 3;
	
	@Override
	public int addIntValues(int a, int b) {
		return (int)(Math.pow((double)a, 2.0)) + b;
	}
	
	private void testingMethod() {
		// TODO Auto-generated method stub
		
		int sum = addIntValues(2, 5);

	}
	
	public static void main(String[] args) {
		Parent child = new Child();
		System.out.println(child.addIntValues(((Child)child).privateInt, 0));
	}
	
}
