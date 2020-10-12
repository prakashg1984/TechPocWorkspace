import java.util.Scanner;

public class PalindromString {

	public static void main(String[] args) {

	      String str, rev = "";
	      Scanner sc = new Scanner(System.in);
	      
	      System.out.println("Enter String");
	      
	      str = sc.nextLine();
	      
	      int length = str.length();
	      
	      for(int i = length-1 ; i >= 0 ; i--) {
	    	  rev = rev + str.charAt(i);
	      }
	      
	      System.out.println("rev "+rev);
	      
	      if(str.equalsIgnoreCase(rev)) {
	    	  System.out.println(str + " is a palindrome!");
	      }else {
	    	  System.out.println(str + " is not a palindrome!");
	      }
	}

}
