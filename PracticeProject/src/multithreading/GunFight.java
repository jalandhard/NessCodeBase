package multithreading;

public class GunFight {
	
	private int bullets = 40;
	private int bulletsToBeFired = 40;
	private int bulletsFired = 0;
	public static Thread fireThread;
	public static Thread reloadThread;
	  
    // This method fires the number of bullets that are
    // passed it. When the bullet in magazine becomes zero,
    // it calls the wait() method and releases the lock.
    synchronized public void fire(int bulletsToBeFired)
    {
    	fireThread = Thread.currentThread();
    	if(bulletsToBeFired >= 40) {
    		this.bulletsToBeFired = bulletsToBeFired;
    	}
    	else
    		this.bulletsToBeFired = 0;
    	int i = 0;
        do {
            if(bullets >= 1 && bulletsToBeFired >= 1) {
            	bulletsFired++;
            	i++;
            	System.out.println("Firing " + i + "th Bullet By Thread : " + Thread.currentThread().getName());
            }
        	if (bullets == 0) {
                System.out.println(i
                                   + " bullets fired and "
                                   + bullets + " remains");
                //System.out.println("Invoking the wait() method");
                try {
                	//System.out.println(Thread.currentThread().getName() + " Invoking notify() method");
                	notify();
                	//System.out.println(Thread.currentThread().getName() + " getting into wait state");
                	
                    wait();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Fire Thread status : " + GunFight.fireThread.getState());
                System.out.println(
                    "Continuing the fire after reloading");
            }
  
            bullets--;
        }
        while(i < bulletsToBeFired);
        notify();
        if(bulletsToBeFired >= 1)
        	System.out.println("Total Bullets to be fired : " + bulletsFired);
        else
        	System.out.println("Total Bullets to be fired is less than or equal to zero!!!!!");
        System.out.println(
            "The firing process is complete");
    }
  
    // reload() increases the bullets by 40 everytime it is
    // invoked and calls the notify() method which wakes up
    // the thread that was sent to sleep using wait() inside
    // of fire() method
    synchronized public void reload()
    {
    	reloadThread = Thread.currentThread();
        while(bulletsToBeFired >= 40) {
        	System.out.println("Reloading the magazine and resuming " + "the gun fight thread using notify() by Reloading Thread : " + Thread.currentThread().getName());
        	bullets += 41;
        	System.out.println("Bullets reloaded and Bullets left : " + (bullets - 1));
        	bulletsToBeFired = bulletsToBeFired - 40;
        	System.out.println("Left Bullets to be fired : " + bulletsToBeFired);
        	//System.out.println(Thread.currentThread().getName() + " Invoking notify() method");
        	notify();
        	try {
        		//System.out.println(Thread.currentThread().getName() + " getting into wait state");
        		wait();
        		//Thread.currentThread().join();
        	} catch (InterruptedException e) {
        		// TODO Auto-generated catch block
        		e.printStackTrace();
        	}
        }
        System.out.println("Reload Thread status : " + GunFight.reloadThread.getState());
        
    }

}
