package multithreading;

public class waitDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GunFight gf = new GunFight();
		  
        // Creating a new thread and invoking
        // our fire() method on it
        new Thread() {
            @Override public void run() { gf.fire(82); }
        }.start();
  
        // Creating a new thread and invoking
        // our reload method on it
        new Thread() {
            @Override public void run() { gf.reload(); }
        }.start();
        
	}

}
