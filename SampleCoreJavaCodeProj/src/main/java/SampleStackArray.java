import java.util.Scanner;

public class SampleStackArray {

	int top;
	int maxsize = 10;
	int[] arr = new int[maxsize];

	boolean isEmpty() {
		return (top < 0);
	}

	SampleStackArray() {
		top = -1;
	}

	boolean push(Scanner sc) {
		if (top == maxsize - 1) {
			System.out.println("Overflow !!");
			return false;
		} else {
			System.out.println("Enter Value");
			int val = sc.nextInt();
			top++;
			arr[top] = val;
			System.out.println("Item pushed "+top);
			return true;
		}
	}

	boolean pop() {
		if (top == -1) {
			System.out.println("Underflow !!");
			return false;
		} else {
			System.out.println("Item popped "+arr[top]);
			top--;
			return true;
		}
	}

	void display() {
		System.out.println("Printing stack elements .....");
		for (int i = top; i >= 0; i--) {
			System.out.println(arr[i]);
		}
	}
}
