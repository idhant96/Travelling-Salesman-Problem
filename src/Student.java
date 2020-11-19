
public class Student implements Runnable{
	    private boolean running = false;

	    public synchronized void stopCompute() {
	        this.running = false;
	    }
	    public synchronized void startCompute() {
	        this.running = true;
	    }
	    

	    private synchronized boolean continueCompute() {
	        return this.running == true;
	    }
	@Override
	public void run() {
		while (continueCompute()) {
		System.out.println(Thread.currentThread().getName() 
                + ", executing Student run() method!"); 
		}
	}

}
