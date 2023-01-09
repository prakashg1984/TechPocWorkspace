import java.util.Scanner;
import java.util.stream.IntStream;

public class PrimeNumber {

	public static void main(String[] args) {
		  IntStream.iterate(1, i -> 3*i).forEach(System.out::print);
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
		  
		  int number = 21;
		  

		  
		  IntStream.rangeClosed(2, number/2).forEach(System.out::print);
		  
		  boolean isPrime = IntStream.rangeClosed(2, number/2).noneMatch(i -> number%i == 0);
		  
		  boolean isPrime2 = !IntStream.rangeClosed(2, number/2).anyMatch(i -> number%i == 0);

		  
		  System.out.println("isPrime "+isPrime);

	}

}
