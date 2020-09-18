import java.util.ArrayList;
import java.util.List;

public class TestLeetCodeMay2 {

	public static void main(String[] args) {
		TestLeetCodeMay2 testLeetCodeMay2 = new TestLeetCodeMay2();

	    List<List<String>> cityList = new ArrayList<List<String>>();
	    List<String> city1 = new ArrayList<String>();
	    List<String> city2 = new ArrayList<String>();
	    List<String> city3 = new ArrayList<String>();
	    List<String> city4 = new ArrayList<String>();
	    List<String> city5 = new ArrayList<String>();
	    List<String> city6 = new ArrayList<String>();
	    
	    city1.add("jMgaf WaWA");
	    city1.add("iinynVdmBz");

	    city3.add("QCrEFBcAw");
	    city3.add("wRPRHznLWS");

	    city2.add("iinynVdmBz");
	    city2.add("OoLjlLFzjz");

	    city4.add("OoLjlLFzjz");
	    city4.add("QCrEFBcAw");

	    city5.add("IhxjNbDeXk");
	    city5.add("jMgaf WaWA");

	    city6.add("jmuAYy vgz");
	    city6.add("IhxjNbDeXk");
	    
	    cityList.add(city1);
	    cityList.add(city3);
	    cityList.add(city2);
	    cityList.add(city4);
	    cityList.add(city5);
	    cityList.add(city6);
	    
		//testLeetCodeMay2.destCity(cityList);
		
		/*int[] nums = {1,0,0,0,1,0,0,1};
		testLeetCodeMay2.lengthKPlace(nums,2);*/
		
		
		/*int[] nums2 = {8,2,4,7};
		testLeetCodeMay2.contSubArray(nums2,2);*/
		//System.out.println(testLeetCodeMay2.validPerfSq(8));
		
		//System.out.println(testLeetCodeMay2.canConstruct("aa","aab"));

		System.out.println(testLeetCodeMay2.findComplement(5));

	}
	
	
	public int firstUniqChar(String s) {
		for(char c : s.toCharArray()) {	
			if(s.indexOf(c) == s.lastIndexOf(c)) {
        		return s.indexOf(c);
			}
		}
		return -1;
    }

	public int findComplement(int num) {
		String bits = Integer.toString(num,2);

		bits = bits.replace('0', '2');
		bits = bits.replace('1', '0');
		bits = bits.replace('2', '1');
		
		System.out.println("complementBits "+bits);
		
		return Integer.parseInt(bits,2);
    }

	public boolean canConstruct(String ransomNote, String magazine) {
		for(char c : ransomNote.toCharArray()) {	
			System.out.println("magazine "+magazine);
        	int index = magazine.indexOf(c);
			System.out.println("index "+index);

        	if(index >= 0) {
        		magazine = magazine.replaceFirst(String.valueOf(c), "");
        	}else {
        		return false;
        	}
        }
		
		return true;
    }
	
	public int numJewelsInStones(String J, String S) {
        int total = 0;		
        for(char c : J.toCharArray()) {
        	int count = Math.toIntExact(S.chars().filter(ch -> ch == c).count());
        	total = total+count;
        }
        return total;
    }

	public boolean validPerfSq(int n) {
		int sum =0;
		for(int i=1; i< n ; i=i+2) {
			sum = sum+i;
			if(sum == n) {
				return true;
			}
			if(sum > n) {
				return false;
			}
		}
		return false;
	}
	
	public String destCity(List<List<String>> paths) {
		String finalCity = "";
		
		for(int i=0;i<paths.size();i++) {
			String startCity = paths.get(i).get(0);
			String endCity = paths.get(i).get(1);
			boolean found = false;
			for(int j=0;j<paths.size();j++) {
				if(i != j) {
					String nextStartCity = paths.get(j).get(0);
					String nextendCity = paths.get(j).get(1);
					
					if(endCity.equalsIgnoreCase(nextStartCity)){
						found = true;
					}

				}
			}
			if(!found) {
				finalCity = endCity;
			}
			System.out.println("finalCity "+finalCity);
		}
		
		System.out.println("finalCity "+finalCity);
		return finalCity;
	}
	
	
	public boolean lengthKPlace(int[] nums, int k) {
		boolean result = true;
		
		for(int i=0;i<nums.length;i++) {
			int val = nums[i];
			if(val == 1) {
				for(int j=i+1;j<nums.length;j++) {
					int secVal = nums[j];
					if(secVal == 1) {
						if(Math.abs((i-j)) <k+1) {
							result = false;
							break;
						}
					}
				}
			}
			if(!result) {
				break;
			}
		}
		
		System.out.println("result "+result);
		return result;
	}
	
	
	public int contSubArray(int[] nums, int limit) {
		int result = 0;
		List<Integer> input = new ArrayList<>();
		for(int num : nums) {
			input.add(num);
		}
		
		for(int i=0;i<=input.size();i++) {
			for(int toIndex = i+1;toIndex<=input.size();toIndex++) {
				List<Integer> subList = input.subList(i, toIndex);
				if(subList.size() == 1) {
					if(subList.get(0) - subList.get(0) <=limit) {
						result = (result > subList.size() ? result : subList.size());
					}
				}else {
					int min=0;
					int max=0;
					for(int j=0;j<subList.size();j++) {
						if(j ==0) {
							min = subList.get(j);
						}
						if(subList.get(j) > max) {
							max = subList.get(j);
						}
						if(subList.get(j) < min) {
							min = subList.get(j);
						}
					}
					if(max-min <= limit) {

						result = (result > subList.size() ? result : subList.size());

					}
				}
			}			
		}
		
		System.out.println("result "+result);
		return result;
	}
}
