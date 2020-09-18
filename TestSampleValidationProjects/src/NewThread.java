
public class NewThread extends Thread {

	private int counter = 0;

	public NewThread() {
        super("MyThread");
    }
    public void run() {
    	counter ++;
        System.out.println("Inside NewThread "+this.getName() + " "+counter);
    }
}
