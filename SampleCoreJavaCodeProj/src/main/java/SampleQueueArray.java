import java.util.Scanner;

public class SampleQueueArray {

	int front,rear;
	int maxsize = 10;
	int[] arr = new int[maxsize];

	boolean isEmpty() {
		return (rear < 0);
	}

	SampleQueueArray() {
		rear = -1;
		front = -1;
	}

	boolean insert(Scanner sc) {
		if (rear == maxsize - 1) {
			System.out.println("Overflow !!");
			return false;
		}else if(front == -1 && rear == -1) {
			front = 0;  
	        rear = 0;
		}
		else {
			rear = rear +1;
		}
		System.out.println("Enter value to insert..");
		int val = sc.nextInt();
		arr[rear] = val;
		return true;
	}

	boolean delete() {
		int item;
		if (front == -1 || front > rear) {
			System.out.println("Underflow !!");
			return false;
		} else {
			item = arr[front];
			if (front == rear) {
				front = -1;
				rear = -1;
			} else {
				front = front + 1;
			}
			System.out.println("Item deleted "+item);
			return true;
		}
	}

	void display() {
		System.out.println("Printing queue elements .....");
		for (int i = front; i <= rear; i++) {
			System.out.println(arr[i]);
		}
	}
}
