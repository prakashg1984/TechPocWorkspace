import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Merge2Arrays {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer array1[]= {3, 15, 12, 4};
		Integer array2[]= {2, 4, 3, 9, 7};

		List<Integer> mergedList = new ArrayList<Integer>(Arrays.asList(array1));
		mergedList.addAll(new ArrayList<Integer>(Arrays.asList(array2)));
		
		Collections.sort(mergedList);
		System.out.println(mergedList);

	}

}
