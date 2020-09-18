import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class to find the distance of nearest restaurant
 * @author PGPS
 *
 */
public class FindNearRestaurantDistance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int totalRestaurants =4;
        	List<List<Integer>> allLocations = new ArrayList<>(); 
        	List<Integer> location = new ArrayList<>();
        	location.add(1);
        	location.add(2);
        	allLocations.add(location);
        	
        	location = new ArrayList<>();
        	location.add(3);
        	location.add(4);
        	allLocations.add(location);

        	
        	location = new ArrayList<>();
        	location.add(1);
        	location.add(-1);
        	allLocations.add(location);
        	
        	location = new ArrayList<>();
        	location.add(1);
        	location.add(0);
        	allLocations.add(location);

        
        	int numRestaurants = 2;
        	nearestVegetarianRestaurant(totalRestaurants, allLocations, numRestaurants);
	}
	
	
	private static List<List<Integer>> nearestVegetarianRestaurant(int totalRestaurants, 
            List<List<Integer>> allLocations, 
			int numRestaurants) {

		List<List<Integer>> listOfRest = new ArrayList<List<Integer>>();
		Map<String, Double> finalDistanceMap = new HashMap<String, Double>();

        ValueComparator bvc = new ValueComparator(finalDistanceMap);
        TreeMap<String, Double> sortedFinalDistanceMap = new TreeMap<String, Double>(bvc);

		for (int i=0;i<totalRestaurants;i++) {
			finalDistanceMap.put(""+i, Math.sqrt((Math.pow(
					allLocations.get(i).get(0), 2) + (Math.pow(
							allLocations.get(i).get(1), 2)))));
		}
		
		sortedFinalDistanceMap.putAll(finalDistanceMap);
        
        for( Map.Entry<String, Double> sortedFinalDistance : sortedFinalDistanceMap.entrySet() ){
            if(listOfRest.size() < numRestaurants){
            	listOfRest.add(allLocations.get(Integer.valueOf(sortedFinalDistance.getKey().toString())));
            }

        }
        System.out.println(listOfRest);
		return listOfRest;

	}
	
	
	static class ValueComparator implements Comparator<String> {

	    Map<String, Double> base;

	    public ValueComparator(Map<String, Double> base) {
	        this.base = base;
	    }

	    // Note: this comparator imposes orderings that are inconsistent with
	    // equals.
	    public int compare(String a, String b) {
	        if (base.get(b) >= base.get(a)) {
	            return -1;
	        } else {
	            return 1;
	        } // returning 0 would merge keys
	    }


	}

}
