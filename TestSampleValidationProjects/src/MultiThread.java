import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;


public class MultiThread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	      final List<Thread> threads = new ArrayList<>(100);

		for(int i=0 ; i< 5;i++){
			
			final Thread thread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					System.out.println("Running thread : ");
					
				}
			});
			threads.add(thread);
			thread.start();
		}
		for(Thread thread : threads){
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("Execution completed : ");
		
		for(int i=0 ; i< 5;i++){
			Thread t = new NewThread();
			t.start();
		}
		
		ExecutorService executor = Executors.newFixedThreadPool(10);
		NewRunnableThread rc = new NewRunnableThread();

		for(int i=0 ; i< 25;i++){
			Thread t = new Thread(rc);
			t.start();
			//executor.submit(t);
		}

		//executor.shutdown();
		
	    FutureTask[] randomNumberTasks = new FutureTask[25]; 
	    Callable callThread = new CallableThread();
		for(int i=0 ; i< 25;i++){
			
			randomNumberTasks[i] = new FutureTask(callThread);
			
			Thread t = new Thread(randomNumberTasks[i]);
			//t.start();
			executor.submit(t);
		}
		
		for(int i=0 ; i< 25;i++){
			try {
				System.out.println(randomNumberTasks[i].get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		executor.shutdown();
	}

}
