import java.util.Scanner;

public class DuplicateCharacters {

	public static void main(String[] args) {
		String input = "";
		Scanner sc = new Scanner(System.in);
		
		input = sc.nextLine();
		
		char[] inputArray = input.toCharArray();
		
		for(int i=0; i<inputArray.length; i++) {
			for(int j=i+1; j<inputArray.length; j++) {
				if(inputArray[i] == inputArray[j]) {
					System.out.println(inputArray[i]);
					break;
				}
			}
		}
		
		sc.close();

	}

}
