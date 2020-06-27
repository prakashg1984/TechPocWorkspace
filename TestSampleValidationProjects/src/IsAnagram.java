import java.util.Scanner;


public class IsAnagram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Enter Input");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		
		String reverseString="";
		System.out.println(input);

		for(int i=input.length()-1;i>=0;i--){
			reverseString += input.charAt(i);
		}
		System.out.println(reverseString);
		
		if(input.equalsIgnoreCase(reverseString)){
			System.out.println("Is Anagram");
		}else{
			System.out.println("Not Anagram");
		}
	}

}
