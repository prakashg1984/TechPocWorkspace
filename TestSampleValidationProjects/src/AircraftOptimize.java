import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Optimize the aircraft route based on distance
 * @author PGPS
 *
 */
public class AircraftOptimize {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<List<Integer>> forwardRouteList = new ArrayList<>(); 
		List<List<Integer>> returnRouteList = new ArrayList<>(); 

		List<Integer> location = new ArrayList<>();
    	location.add(1);
    	location.add(6000);
    	forwardRouteList.add(location);
    	
    	location = new ArrayList<>();
    	location.add(2);
    	location.add(7000);
    	forwardRouteList.add(location);

    	
    	location = new ArrayList<>();
    	location.add(3);
    	location.add(6000);
    	forwardRouteList.add(location);
    	
    /*	location = new ArrayList<>();
    	location.add(4);
    	location.add(10000);
    	forwardRouteList.add(location);*/
    	
//
    	location = new ArrayList<>();
    	location.add(1);
    	location.add(2000);
    	returnRouteList.add(location);

    	
    	/*location = new ArrayList<>();
    	location.add(2);
    	location.add(3000);
    	returnRouteList.add(location);
    	
    	location = new ArrayList<>();
    	location.add(3);
    	location.add(4000);
    	returnRouteList.add(location);
    	
    	location = new ArrayList<>();
    	location.add(4);
    	location.add(5000);
    	returnRouteList.add(location);*/
    	
    	optimalUtilization(7000, forwardRouteList, returnRouteList);
	}

	
	public static List<List<Integer>> optimalUtilization(int maxTravelDist, 
            List<List<Integer>> forwardRouteList,
			List<List<Integer>> returnRouteList) {

		List<List<Integer>> finalOptimizedList = new ArrayList<List<Integer>>();
		Map<List<Integer>, Integer> finalDistanceMap = new HashMap<List<Integer>, Integer>();
		Map<List<Integer>, Integer> finalNearOptimizedMap = new HashMap<List<Integer>, Integer>();
		int currentOptimalDistance = 0;

		boolean gotOptimizedList = false;
		for (List<Integer> forwardRoute : forwardRouteList) {
			for (List<Integer> returnRoute : returnRouteList) {
				List<Integer> routeId = new ArrayList<>();
				routeId.add(forwardRoute.get(0));
				routeId.add(returnRoute.get(0));
				finalDistanceMap.put(routeId,
						forwardRoute.get(1) + returnRoute.get(1));
			}
		}

		for (Map.Entry<List<Integer>, Integer> finalDistance : finalDistanceMap
				.entrySet()) {
			if (finalDistance.getValue() == maxTravelDist) {
				finalOptimizedList.add(finalDistance.getKey());
				gotOptimizedList = true;
			}
			if (finalNearOptimizedMap.isEmpty()) {
				if(finalDistance.getValue() < maxTravelDist){
					currentOptimalDistance = finalDistance.getValue();
					finalNearOptimizedMap.put(finalDistance.getKey(),
							finalDistance.getValue());
				}
			} else {
				if (currentOptimalDistance < finalDistance.getValue()
						&& finalDistance.getValue() < maxTravelDist) {
					finalNearOptimizedMap.clear();
					finalNearOptimizedMap.put(finalDistance.getKey(),
							finalDistance.getValue());
				}
			}
		}
		System.out.println("finalNearOptimizedMap :" + finalNearOptimizedMap);
		if (!gotOptimizedList) {
			for (Map.Entry<List<Integer>, Integer> finalNearOptimized : finalNearOptimizedMap
					.entrySet()) {
				finalOptimizedList.add(finalNearOptimized.getKey());
			}
		}

		System.out.println("finalOptimizedList :" + finalOptimizedList);
		
		if(finalOptimizedList.isEmpty()){
			System.out.println("No Optimal route which is less than or equal to given distance : "+maxTravelDist);

		}

		return finalOptimizedList;
	}
}
