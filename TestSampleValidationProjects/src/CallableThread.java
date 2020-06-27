import java.util.concurrent.Callable;


public class CallableThread implements Callable {

	int counter = 0;
	@Override
	public Object call() throws Exception {
		// TODO Auto-generated method stub4
		System.out.println("Inside callable");
		Thread.sleep(2000);
		return counter++;
	}

}
