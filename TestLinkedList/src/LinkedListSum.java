
public class LinkedListSum {

	public static void main(String[] args) {

		LinkedListSum linkedListSum = new LinkedListSum();
		
		Node start = new Node(2);
		start.next = new Node(4);
		start.next.next = new Node(3);

		Node start2 = new Node(5);
		start2.next = new Node(6);
		start2.next.next = new Node(4);
		
		Node sumNode = linkedListSum.listNodeSum2(start , start2);

		while(null != sumNode){
			System.out.println(sumNode.getData());
			sumNode = sumNode.next;
		}
	}

	private Node listNodeSum(Node l1, Node l2) {
		Node prev = new Node(0);
		Node head = prev;
		int carryNum = 0;

		while (l1 != null || l2 != null || carryNum != 0) {
			Node curNode = new Node(0);
			int sum = ((l1 != null) ? l1.getData() : 0) + ((l2 != null) ? l2.getData() : 0) + carryNum;
			curNode.setData(sum % 10);
			carryNum = sum / 10;
			Node last = head;
			
			while (last.next != null) {
				last = last.next;
			}
			last.next = curNode;
			l1 = ((l1 == null) ? l1 : l1.next);
			l2 = ((l2 == null) ? l2 : l2.next);
		}
		return head.next;
	}
	
	
	private Node listNodeSum2(Node l1, Node l2) {
		Node prev = new Node(0);
		Node head = prev;
		int carryNum = 0;

		while (l1 != null || l2 != null || carryNum != 0) {
			Node curNode = new Node(0);
			int sum = ((l1 != null) ? l1.getData() : 0) + ((l2 != null) ? l2.getData() : 0) + carryNum;
			curNode.setData(sum % 10);
			carryNum = sum / 10;
			
			prev.next = curNode;
			prev = prev.next;
			l1 = ((l1 == null) ? l1 : l1.next);
			l2 = ((l2 == null) ? l2 : l2.next);
		}
		return head.next;
	}

}
