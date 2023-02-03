
public class LinkedListMain {

	public static void main(String[] args) {
		MyLinkedList myLinkedList = new MyLinkedList(); 
		/*Node start = new Node(1);
		start.next = new Node(2);
		start.next.next = new Node(3);
		start.next.next.next = new Node(4);
		start.next.next.next.next = new Node(5);
		start.next.next.next.next.next = new Node(6);
		myLinkedList.head = start;*/
		int[] data = {1,2,3,4,5,6};
		myLinkedList.head = createLinkedList(data);
		
		
		printLinkedList(myLinkedList);
		System.out.println("--");
		reverseLinkedList(myLinkedList);
		printLinkedList(myLinkedList);
		
		reverseSecHalfLinkedList(myLinkedList.head);
		System.out.println("--");
		printLinkedList(myLinkedList);
	}
	
	private static Node createLinkedList(int[] data) {
		Node head = new Node(data[0]);
		Node temp = head;
		for(int i=1;i<data.length;i++) {
			Node nextNode = new Node(data[i]);
			head.next = nextNode;
			head = head.next;
		}
		return temp;
	}
	
	
	private static void printLinkedList(MyLinkedList myLinkedList){
		Node temp = myLinkedList.head;
		while(null != myLinkedList.head){
			System.out.println(myLinkedList.head.getData());
			myLinkedList.head = myLinkedList.head.next;
		}
		myLinkedList.head = temp;
	}
	
	private static void printLinkedList(Node head){
		Node temp = head;
		while(null != head){
			System.out.println(head.getData());
			head = head.next;
		}
		head = temp;
	}
	
	public static void reverseLinkedList(MyLinkedList myLinkedList){
		Node previousNode = null;
		Node currentNode = myLinkedList.head;
		Node nextNode;
		
		while(null != currentNode){
			nextNode = currentNode.next;
			currentNode.next = previousNode;
			previousNode = currentNode;
			currentNode = nextNode;
		}
		myLinkedList.head = previousNode;
	}
	
	public static Node reverseSecHalfLinkedList(Node start){
		Node previousNode = null;
		Node temp = start;
		
		Node nextNode;
		Node preNode = start;
		
		int size = 0;
		
		while(null != temp){
			temp = temp.next;
			size ++;
		}
		for(int i=0; i< size/2 ; i++){
			preNode = start;
			start = start.next;
		}

		while(null != start){
			nextNode = start.next;
			start.next = previousNode;
			previousNode = start;
			start = nextNode;
		}
		preNode.next = previousNode;

		return preNode;
	}
	
}
