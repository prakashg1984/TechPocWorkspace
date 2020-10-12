import java.util.Scanner;

public class VowelsInString {

	public static void main(String[] args) {
		System.out.println("Enter String..");
		String input;
		char[] ch;
		int count=0;
		Scanner sc = new Scanner(System.in);
		
		input = sc.nextLine().toUpperCase();
		ch = input.toCharArray();
		
		for(int i=0;i<ch.length;i++) {
			switch(ch[i]) {
				case 'A' :
				case 'E' :
				case 'I' :
				case 'O' :
				case 'U' :
					count++;
					System.out.println(ch[i]);
			}
		}
		if(count==0) {
			System.out.println("There are no vowels in a string"); 
		
		}
		
		sc.close();
	}

}
