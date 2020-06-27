import java.util.Scanner;


public class ReverseString {

	public static void main(String[] args) {

		System.out.println("Enter Input");
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
