import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KTHLargestNum {

	public static void main(String[] args) {
		int[] nums = {3,2,3,1,2,4,5,5,6};

		System.out.println(findKthLargest(nums,4));
	}

	private static int findKthLargest(int[] nums, int k) {
		
		List<Integer> list = new ArrayList<Integer>();
		for(int i=0;i<nums.length;i++) {
			if(!list.contains(nums[i])) {
				list.add(nums[i]);
			}
		}
		Collections.sort(list);
		Collections.reverse(list);
		return list.get(k-1);
    }
}
