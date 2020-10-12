import java.util.Scanner;

public class PrimeNumber {

	public static void main(String[] args) {
		  int num ,count;
		  Scanner sc = new Scanner(System.in);
		  System.out.println("Enter No Of Inputs Reqd");
		  num = Integer.valueOf(sc.nextLine());
		  
		  for(int i=1;i<=num;i++) {
			  count = 0;
			  for(int j=2;j<i;j++) {
				  if(i % j == 0) {
					  count ++;
					  break;
				  }
			  }
			  
			  if(count == 0) {
				  System.out.println("Prime Nr : "+i);
			  }
		  }
		  sc.close();
	}

}
