import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class LRUCacheUsingSet {

	Set<Integer> cache; 
    int capacity; 
  
    public LRUCacheUsingSet(int capacity) 
    { 
        this.cache = new LinkedHashSet<Integer>(capacity); 
        this.capacity = capacity; 
    } 
    
    //If already present - then add to top
    public boolean get(int key) 
    { 
        if (!cache.contains(key)) {
        	return false; 
        }
        //Remove and add it to top as recently used
        cache.remove(key); 
        cache.add(key); 
        return true; 
    } 
    
    public void put(int key) 
    { 
    	if (cache.contains(key))  {
    		//IF already present, remove it to add it later at top
    		cache.remove(key); 
    	}else if (cache.size() == capacity) { 
    		//IF cache size is full - remove the least used one
            int firstKey = cache.iterator().next(); 
            cache.remove(firstKey); 
        } 
        
    	cache.add(key);
    }
    
    public void display() 
    { 
        Iterator<Integer> itr = cache.iterator(); 
        while (itr.hasNext()) { 
            System.out.print(itr.next() + " "); 
        } 
    } 
    
    public void refer(int key) {         
    	put(key);  
    } 
    
    public static void main(String[] args) 
    { 
    	LRUCacheUsingSet ca = new LRUCacheUsingSet(4); 
        ca.refer(1); 
        ca.refer(2); 
        ca.refer(3); 
        ca.get(1); 
        ca.refer(4); 
        ca.refer(5); 
        ca.get(1); 
        ca.display(); 
    } 
    
}
