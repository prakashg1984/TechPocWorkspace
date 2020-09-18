import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Validate the cell state after N days
 * @author PGPS
 *
 */
public class ValidateCellState {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] currentState = {0,1,1,1,0,0,1,0};
		int days = 2;
		List<Integer> finalList = findCellState(days, currentState);
		
		
	}
	
	private static List<Integer> findCellState(int days,int[] currentState){
		int[] newFinalState = new int[8];
		
		while(days > 0){
			days --;
			System.out.println("currentState "+Arrays.toString(currentState));
			for(int i=0 ; i < currentState.length ; i++  ){
				System.out.println("currentState "+Arrays.toString(currentState));
				int rightCellState = 0;
				int leftCellState = 0;
				
				if(i !=0){
					leftCellState = currentState[i-1];
				}
				
				if(i != currentState.length -1){
					rightCellState = currentState[i+1];
				}
				
				System.out.println("leftCellState : "+leftCellState);
				System.out.println("rightCellState : "+rightCellState);
				if(leftCellState == rightCellState){
					newFinalState[i] = 0;
				}else{
					newFinalState[i] = 1;
				}
				
				System.out.println(i + "=" + newFinalState[i]);
			}
			currentState = Arrays.copyOf(newFinalState, newFinalState.length);
		}
		
		System.out.println("newFinalState "+Arrays.toString(newFinalState));
		
		List<Integer> intList = new ArrayList<Integer>();
		for (int i : newFinalState)
		{
		    intList.add(i);
		}

		
		return intList;
	}

}
