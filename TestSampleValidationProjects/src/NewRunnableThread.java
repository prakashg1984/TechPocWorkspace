
public class NewRunnableThread implements Runnable{

	private int counter = 0;

	@Override
	public void run() {
		counter++;
		System.out.println("Inside Runnable "+counter);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
