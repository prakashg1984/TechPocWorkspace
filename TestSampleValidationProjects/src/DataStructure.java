import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Stream;


public class DataStructure {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		mapStructure();
	}

	private static void mapStructure(){
	      // HashMap  
		HashMap hm = new HashMap();
	      hm.put("Zara", new Double(3434.34));
	      hm.put("Mahnaz", new Double(123.22));
	      hm.put("Ayan", new Double(1378.00));
	      hm.put("Daisy", new Double(99.22));
	      hm.put("Qadir", new Double(-19.08));

	      Set entrySet = hm.entrySet();
	      Iterator mapIterator = entrySet.iterator();
	      
	      System.out.println("Printing HashMap using EntrySet and Iterator");
	      while(mapIterator.hasNext()){
	    	  Map.Entry<String, Double> me = (Map.Entry)mapIterator.next();
	    	  System.out.println(me.getKey());
	    	  System.out.println(me.getValue());
	      }
	      
	      System.out.println("-----------------------");

	      //Sorted Map
	      SortedMap sm = new TreeMap();

	      // Put elements to the map
	      sm.put("Zara", new Double(3434.34));
	      sm.put("Mahnaz", new Double(123.22));
	      sm.put("Ayan", new Double(1378.00));
	      sm.put("Daisy", new Double(99.22));
	      sm.put("Qadir", new Double(-19.08));

	      Set entrySet2 = sm.entrySet();
	      Iterator mapIterator2 = entrySet2.iterator();
	      
	      System.out.println("Printing SortedMap using EntrySet and Iterator");

	      while(mapIterator2.hasNext()){
	    	  Map.Entry<String, Double> me = (Map.Entry)mapIterator2.next();
	    	  System.out.println(me.getKey());
	    	  System.out.println(me.getValue());
	      }
	      
	      System.out.println("-----------------------");

	      System.out.println("Printing SortedMap using KeySet");
	      Set<String> keySet = sm.keySet();
	      for(String key : keySet){
	    	  System.out.println(key);
	    	  System.out.println(sm.get(key));
	      }
	      
/*	      sm.keySet().stream().forEach(key -> System.out.println(key : + " " + sm.get(key)));
	      
	      Stream.of(sm.keySet().toArray())
			.forEach(s -> System.out.println(key + "=" + sm.get(s)));*/
	}
}
