import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class PalindromString {

	public static void main(String[] args) {

	      String str, rev = "";
	      Scanner sc = new Scanner(System.in);
	      
	      System.out.println("Enter String");
	      
	      str = sc.nextLine();
	      
	     /* int length = str.length();
	      
	      for(int i = length-1 ; i >= 0 ; i--) {
	    	  rev = rev + str.charAt(i);
	      }
	      
	      System.out.println("rev "+rev);
	      
	      if(str.equalsIgnoreCase(rev)) {
	    	  System.out.println(str + " is a palindrome!");
	      }else {
	    	  System.out.println(str + " is not a palindrome!");
	      }*/
	      
	      String tempString = str.replaceAll("\\s+", "").toLowerCase();

	      //Palindrome using Java8
	      boolean isPal = IntStream.range(0, tempString.length() / 2)
	            .noneMatch(i -> tempString.charAt(i) != tempString.charAt(tempString.length() - i - 1));
	        
	      boolean isPal2 = IntStream.range(0, tempString.length() / 2)
		            .allMatch(i -> tempString.charAt(i) == tempString.charAt(tempString.length() - i - 1));
	        
	        System.out.println(isPal);
	        System.out.println(isPal2);
	        
	        String s1= "sachin";
	        String s2= "sachin";
	        String s3= new String ("sachin");

	        System.out.println(s1.hashCode());
	        System.out.println(s2.hashCode());
	        System.out.println(s3.hashCode());
	        
	        //Factorial
	        long number = 5;
	        
	        //int fact = IntStream.rangeClosed(2, number).reduce(1, (x, y) -> x * y);
	        
	        long fact = LongStream.rangeClosed(2, number).reduce(1, (x, y) -> x * y);
	        
	        System.out.println(fact);

	}

}
