import java.util.Arrays;
import java.util.Scanner;

public class FibanoociSeries {

	public static void main(String[] args) {
		int fibLength;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Please enter length: ");
		fibLength = sc.nextInt();

		int[] num = new int[fibLength];
		
		num[0] =0;
		num[1] =1;
		num[2] =2;

		for(int i=3;i<fibLength;i++) {
			num[i] = num[i - 1] + num[i - 2];
		}

		System.out.println("Fibonacci Series: ");
		
		for(int i=0;i<num.length;i++) {
			System.out.println(num[i]);
		}
		
	}

}
