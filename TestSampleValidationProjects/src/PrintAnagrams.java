import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class PrintAnagrams {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		sc.close();
		List<String> inputList = Arrays.asList(input.split(" "));

		HashMap<String, List<String>> anagramMap = new HashMap<String, List<String>>();
		
		inputList.forEach(k -> {
			String sortedKey = sortString(k);
			if(anagramMap.containsKey(sortedKey)){
				List<String> values = (List<String>)anagramMap.get(sortedKey);
				values.add(k);
			}else{
				List<String> values = new ArrayList<String>();
				values.add(k);
				anagramMap.put(sortedKey, values);
			}
		});
		
		anagramMap.forEach((k,v) -> {
			if(v.size() > 1) {
        		System.out.println("Anagrams : "+v);
			}else {
        		System.out.println("Not Anagrams : "+v);
			}
		});
		
	}
	
	private static String sortString(String data){
        char tempArray[] = data.toCharArray(); 
        Arrays.sort(tempArray); 
        return new String(tempArray); 
	}

}
