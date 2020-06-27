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
		
		for(int i=0;i<inputList.size();i++){
			String sortedKey = sortString(inputList.get(i));
			if(anagramMap.containsKey(sortedKey)){
				List<String> values = (List<String>)anagramMap.get(sortedKey);
				values.add(inputList.get(i));
			}else{
				List<String> values = new ArrayList<String>();
				values.add(inputList.get(i));
				anagramMap.put(sortedKey, values);
			}
		}
		
        for (String key : anagramMap.keySet()) { 
        	List<String> values = (List<String>)anagramMap.get(key);
        	if(values.size() > 1){
        		System.out.println(values);
        	}
        }

	}
	
	private static String sortString(String data){
        char tempArray[] = data.toCharArray(); 
        Arrays.sort(tempArray); 
        return new String(tempArray); 
	}

}
