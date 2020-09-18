
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestLeetCode {

	public static void main (String[] args) {
		TestLeetCode testLeetCode = new TestLeetCode();
		String input ="";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			System.out.println("Enter Input ");
			input = br.readLine();
			
			//testLeetCode.leetCode185_1(input);
			
			/*String[] inputArray = {"leetcoder","leetcode","od","hamlet","am"};
			testLeetCode.leetCode184_1(inputArray);*/
			
			/*int[] inputArray = {3,1,2,1};
			testLeetCode.leetCode184_1(inputArray,5);*/

			/*int[] inputArray = {6};
			testLeetCode.leetCode183_1(inputArray);*/
			
			/*input = "1101";
			testLeetCode.leetCode183_2(input);*/
			
			//testLeetCode.leetCode183_3(3,1,1);
			
			//testLeetCode.leetCode183_3(3,1,1);
			
			System.out.println(testLeetCode.hasAlternatingBits(11));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean hasAlternatingBits(int n) {
		String bits = Integer.toString(n,2);
		char prevVal = 'a';
		for(char c : bits.toCharArray()) {
			if(prevVal == c) {
				return false;
			}
			prevVal = c;
		}
		
		return true;
	}
	public String leetCode183_3(int a,int b,int c) {
		//https://leetcode.com/contest/weekly-contest-183/problems/longest-happy-string/
		int size = a+b+c;
		StringBuilder sb = new StringBuilder();
		
		boolean lastA = false;
		boolean lastB = false;
		boolean lastC = false;
		
		while(size > 0) {
			size--;
			
			if(a < b || a< c) {
				lastA = true;
			}
			if(b < a || b< c) {
				lastB = true;
			}
			if(c < a || c< b) {
				lastC = true;
			}
			
			System.out.println(sb.toString());
			System.out.println("a "+a);
			System.out.println("b "+b);
			System.out.println("c "+c);
			System.out.println("lastA "+lastA);
			System.out.println("lastB "+lastB);
			System.out.println("lastC "+lastC);

			if(a >=2 && !lastA) {
				lastA = true;
				lastB = false;
				lastC = false;
				sb.append("aa");
				a=a-2;
			}else if(b >=2  && !lastB) {
				lastB = true;
				lastA = false;
				lastC = false;
				sb.append("bb");
				b=b-2;
			}else if(c >=2 && !lastC) {
				lastC = true;
				lastB = false;
				lastA = false;
				sb.append("cc");
				c=c-2;
			}else if(a == 1) {
				lastC = false;
				lastB = false;
				lastA = false;
				sb.append("a");
				a=a-1;
			}else if(b == 1) {
				lastC = false;
				lastB = false;
				lastA = false;
				sb.append("b");
				b=b-1;
			}else if(c == 1) {
				lastC = false;
				lastB = false;
				lastA = false;
				sb.append("c");
				c=c-1;
			}
		}
		
		System.out.println("output "+sb.toString().replace("aaa", "aa").replace("bbb", "bb").replace("ccc", "cc"));
		return sb.toString().replace("aaa", "aa").replace("bbb", "bb").replace("ccc", "cc");
		
	}
	
	public int leetCode183_2(String binary) {
		//https://leetcode.com/contest/weekly-contest-183/problems/number-of-steps-to-reduce-a-number-in-binary-representation-to-one/
		
		int output = 0;
		BigInteger decimal;
		decimal = new BigInteger(binary,2);
		
		while(decimal.intValue() != 1) {
			if(decimal.mod(new BigInteger("2")).equals(new BigInteger("0"))) {
				output = output+1;
				decimal = decimal.divide(new BigInteger("2"));
			}else {
				output = output+2;
				decimal =  decimal.add(new BigInteger("1")).divide(new BigInteger("2"));
			}
		}
		
		return output;
	}
	public List<Integer> leetCode183_1(int[] nums) {
		//https://leetcode.com/contest/weekly-contest-183/problems/minimum-subsequence-in-non-increasing-order/
		
		List<Integer> output = new ArrayList<>();
		
		List<Integer> input = new ArrayList<>();
		for(int num : nums) {
			input.add(num);
		}
		Collections.sort(input);
		Collections.reverse(input);
		System.out.println("input "+input);
		int sumIncluded = 0;
		int sumExcluded = 0;
		int total=0;
		
		while(sumExcluded >= sumIncluded && total<input.size()) {
			output = new ArrayList<>();
			sumIncluded = 0;
			sumExcluded = 0;
			total++;
			for(int i=0; i<total;i++) {
				sumIncluded = sumIncluded+input.get(i);
			}
			
			for(int j=total; j<input.size();j++) {
				sumExcluded = sumExcluded+input.get(j);
			}
			
			if(sumIncluded > sumExcluded) {
				for(int i=0; i<total;i++) {
					output.add(input.get(i));
				}
			}
			System.out.println("sumIncluded "+sumIncluded);
			System.out.println("sumExcluded "+sumExcluded);
			
		}
		
		System.out.println(output);

		return output;
		
	}
	
	public int[] leetCode184_1(int[] queries, int m) {
		//https://leetcode.com/contest/weekly-contest-184/problems/queries-on-a-permutation-with-key/
		
		List<Integer> output = new ArrayList<>();
		List<Integer> input  = new ArrayList<>();
		
		for(int i=1;i<=m;i++) {
			input.add(i);
		}
		
		System.out.println("input "+input);
		
		for(int j=0;j<queries.length ; j++) {
			int val = queries[j];
			output.add(input.indexOf(val));
			System.out.println(val);
			input.remove(input.indexOf(val));
			input.add(0, val);
		}
		
		System.out.println("output "+output);
		return output.stream()
				.mapToInt(Integer::intValue)
				.toArray();
		
	}
	
	public List<String> leetCode184_1(String[] words) {
		//https://leetcode.com/contest/weekly-contest-184/problems/string-matching-in-an-array/
		
		List<String> output = new ArrayList<>();
		
		List<String> wordsList =  Arrays.asList(words);
		
		wordsList.forEach(word ->{
			wordsList.forEach(item ->{
				if(!item.equalsIgnoreCase(word) && item.contains(word)) {
					if(!output.contains(word)) {
						output.add(word);
					}
				}
			});
		});
		System.out.println(output);
		return output;
		
	}
	public void leetCode186_1(String number) {
		
		//https://leetcode.com/contest/weekly-contest-186/problems/maximum-score-after-splitting-a-string/
		
		if(number.length() >= 2 && number.length() <= 500) {
			int maxSum =0;
			for(int i=1;i<number.length();i++) {
				String left = number.substring(0, i);
				String right = number.substring(i, number.length());

				System.out.println("left "+left);
				System.out.println("right "+right);
				int sumLeft =0;
				int sumRight =0;
				int sum =0;
				
				for(int j=0;j<left.length();j++) {
					int val = Integer.valueOf(left.substring(j, j+1));
					if(val == 0) {
						sumLeft = ++sumLeft;
					}
				}

				for(int j=0;j<right.length();j++) {
					int val = Integer.valueOf(right.substring(j, j+1));
					if(val == 1) {
						sumRight = ++sumRight;
					}
				}
				
				System.out.println("sumLeft "+sumLeft);
				System.out.println("sumRight "+sumRight);
				sum = sumRight + sumLeft;
				if(maxSum <sum) {
					maxSum = sum;
				}

			}
			System.out.println("Max Sum : "+maxSum);
		}
	
	}
	
	public void leetCode185_1(String input) {
		
		//https://leetcode.com/contest/weekly-contest-185/problems/display-table-of-food-orders-in-a-restaurant/
		
		char[] alphabets = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		char[] numbers = {'0','1','2','3','4','5','6','7','8','9'};
		ArrayList<Character> alphabetsList = new ArrayList<Character>();
		ArrayList<Character> numbersList = new ArrayList<Character>();

		
		ArrayList<Character> newAlphabet = new ArrayList<Character>();
		ArrayList<Character> newNumber = new ArrayList<Character>();
		String finalOutput = "";
		
	    for (char ch : alphabets) {
	    	alphabetsList.add(ch);
	    }
	    for (char ch : numbers) {
	    	numbersList.add(ch);
	    }

		for(int i=0;i<input.length();i++) {
			char val = input.charAt(i);
			System.out.println("val "+val);
			
			if(alphabetsList.contains(val)) {
				newAlphabet.add(val);
			}
			if(numbersList.contains(val)) {
				newNumber.add(val);
			}
		}
		
		System.out.println("newAlphabet.size() "+newAlphabet.size());
		System.out.println("newNumber.size() "+newNumber.size());
		
		if(Math.abs (newAlphabet.size() - newNumber.size()) <= 1) {
			
			if(newNumber.size() > newAlphabet.size()) {
				for(int j=0;j<newAlphabet.size();j++) {
					finalOutput = finalOutput + "" + newNumber.get(j) + "" + newAlphabet.get(j);
				}
				finalOutput = finalOutput + "" + newNumber.get(newNumber.size()-1);
			}else if(newNumber.size() < newAlphabet.size()) {
				for(int j=0;j<newNumber.size();j++) {
					finalOutput = finalOutput + "" + newAlphabet.get(j) + "" + newNumber.get(j);
				}
				finalOutput = finalOutput + "" + newAlphabet.get(newAlphabet.size()-1);
			}else {
				for(int j=0;j<newNumber.size();j++) {
					finalOutput = finalOutput + "" + newAlphabet.get(j) + "" + newNumber.get(j);
				}
			}
			
		}else {
			System.out.println("Not possible to optimize");
		}
		
		System.out.println("finalOutput "+finalOutput);
		
	}

		
}
