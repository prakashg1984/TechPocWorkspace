import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TestLeetCode3 {

	public static void main(String[] args) {
		
		//https://leetcode.com/contest/weekly-contest-185/problems/minimum-number-of-frogs-croaking/
		String input = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean flag = true;
		try {
			while (flag) {
				System.out.println("Enter Input ");
				input = br.readLine();
				if(input.equalsIgnoreCase("bye")) {
					flag = false;
				}
				int count = findOutPut(input);
				System.out.println(count);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private static int findOutPut(String input) {
		char[] alphabets = {'c','r','o','a','k'};
		int c=0 ; int r=0; int o=0; int a=0; int k=0; int count=0; int use=0;
		ArrayList<Character> alphabetsList = new ArrayList<Character>();
		for (char ch : alphabets) {
	    	alphabetsList.add(ch);
	    }
		
		for(char chn : input.toCharArray()) {
			if(!alphabetsList.contains(chn)) {
				return -1;
			}
			
			switch(chn) {
				case 'c' :
					c++;
					use++;
					break;
				case 'r' :
					r++;
					break;
				case 'o' :
					o++;
					break;
				case 'a' :
					a++;
					break;
				case 'k' :
					k++;
					use--;
					break;
			}
			
			count = Math.max(count, use);
			if ((c < r) || (r < o) || (o < a) || (a < k)) 
                return -1;
		}
		
		if (use == 0 && c == r && c == o && c == a && c == k)
            return count;
		
		return -1;
	}

}
