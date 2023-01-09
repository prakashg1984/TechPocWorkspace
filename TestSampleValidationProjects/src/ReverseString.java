import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class ReverseString {

	public static void main(String[] args) {
		String stringToReverse = "123456";
		StringBuffer sb = new StringBuffer();
		char[] charArray = stringToReverse.toCharArray();
		
		
		List<Character> out = IntStream.range(0, charArray.length) .mapToObj(i ->
		 charArray[(charArray.length - 1) - i]) .collect(Collectors.toList());

		System.out.println("out "+out);
	    
	    IntStream.range(0, charArray.length)
		        .mapToObj(i -> sb.append((charArray[(charArray.length - 1) - i])))
		        ;
	    
	    String reversed = stringToReverse.chars()
	    	    .mapToObj(c -> (char)c)
	    	    .reduce("", (s,c) -> c+s, (s1,s2) -> s2+s1);
	    
	    System.out.println("reversed "+reversed);
		    
	    System.out.println("sb "+sb.toString());
	    
	    IntStream.range(0, charArray.length) .mapToObj(i ->
		 charArray[(charArray.length - 1) - i]).forEach(a -> {
			 sb.append(a);
		 });
		    
		    System.out.println("sb2 "+sb.toString());
	        //.forEach(System.out::print);
		    
	   // String reversed = Arrays.asList(stringToReverse.split("\\.")).stream().map(m -> new 
	    //StringBuilder(m).reverse().toString()).collect(Collectors.joining("."));
		    
		    System.out.println("\nreversed "+reversed);

		    
		System.out.println("\nEnter Input");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		
		String reverseCompleteSentence = reverseCompleteSentence(input);
		System.out.println("reverseCompleteSentence "+reverseCompleteSentence);
		
		String reverseEachWord = reverseEachWord(input);
		System.out.println("reverseEachWord "+reverseEachWord);

	}
	
	private static String reverseCompleteSentence(String data){
		String reverse = "";
		
		for(int i=data.length()-1 ; i >=0; i--){
			reverse = reverse + data.charAt(i);
		}
		
		System.out.println("new StringBuffer(p).reverse().toString() "+new StringBuffer(data).reverse().toString());
		
		return reverse;

	}
	
	private static String reverseEachWord(String data){
		String[] parts = data.split(" ");
		  StringBuffer sb = new StringBuffer();
		  for (String p : parts) {
			  if(p.contains("\n")){
				  String[] newLines = p.split("\n");
				  int i = 1;
				  for (String newLine : newLines) {
					  String reversed = new StringBuffer(newLine).reverse().toString();
					  sb.append(reversed);
					  sb.append(' ');
					  if(i++ < newLines.length){
						  sb.append("\n");
					  }
				  }
			  }else{
					String reversed = new StringBuffer(p).reverse().toString();
					sb.append(reversed);
				    sb.append(' ');
			  }
				
		   }
		  return sb.toString();
	}

}
