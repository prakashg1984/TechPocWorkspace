import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class TestPG {

	public static void main(String[] args) {
		TestPG testPG = new TestPG();
		testPG.writeFile();
		
	}

	private void ComparableTest(){
		Player p1 = new Player("Prakash",34);
		Player p2 = new Player("Priya",30);
		
		List<Player> playerList = new ArrayList<Player>();
		playerList.add(p1);
		playerList.add(p2);
		Collections.sort(playerList);
		System.out.println(playerList.toString());
		
		Collections.sort(playerList, new NameComparator());
		
		System.out.println(playerList.toString() );
	}
	
	private void HashMapTest(){
		HashMap testMap = new HashMap();
		testMap.put("Key1", "Value1");
		testMap.put("Key2", "Value2");
		testMap.put("Key3", "Value3");
		
		Iterator<Map.Entry<String, String>> it = testMap.entrySet().iterator() ;
		while(it.hasNext()){
			Map.Entry<String, String> entry = it.next();
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}
	}
	
	private void runThread(){
		Thread t = new SampleThreadClass();
		//t.start();
		System.out.println("Invoked thread");
		
		ExecutorService executor = Executors.newFixedThreadPool(10);
		executor.execute(t);
	}
	
	private void runRunnable(){
		Runnable runnable = new SampleRunnableClass();

		Thread thread = new Thread(runnable);
		thread.start();
		System.out.println("Invoked runnable");
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Invoked runnable again");
		ExecutorService executor = Executors.newFixedThreadPool(10);
		executor.execute(runnable);
	}
	
	private void readFile() {
		File file = new File("C:\\WorkSpace\\TestProject\\text.txt");

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			String st;
			while ((st = br.readLine()) != null)
				System.out.println(st);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	} 
	
	private void writeFile(){
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter("C:\\WorkSpace\\TestProject\\text2.txt"));
			writer.append("To: Some data \n");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
