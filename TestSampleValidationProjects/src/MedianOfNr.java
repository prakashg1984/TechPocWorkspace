import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class MedianOfNr {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Integer input[]= {3, 5, 12,9};
		List<Integer> inputArray = new ArrayList<Integer>(Arrays.asList(input));

		Collections.sort(inputArray);
		int arraySize = inputArray.size();
		float median;
		System.out.println(arraySize % 2);

		if(arraySize % 2 == 0){
			median = (float) (inputArray.get(arraySize / 2) + inputArray.get((arraySize / 2) - 1))/2;
		}else{
			median = inputArray.get((arraySize-1) / 2);
		}
		System.out.println(inputArray);

		System.out.println(median);
	}

}
