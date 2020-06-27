
public class SampleRunnableClass implements Runnable {

	public void run() {
		System.out.println("Running Runnable");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("After Running Runnable");
		
	}

}
