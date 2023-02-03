import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

		//System.out.println("Fibonacci Series: ");
		
		for(int i=0;i<num.length;i++) {
			//System.out.println(num[i]);
		}
		
		Stream.iterate(new int[]{0, 1}, s -> new int[]{s[1], s[0] + s[1]})
        .limit(fibLength)
		.map(n -> n[0])
		.forEach(System.out::println);
		//.collect(Collectors.toList());
	}

}
