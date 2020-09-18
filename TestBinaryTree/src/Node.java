
public class Node {

	private int data;
	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	Node left,right;
	
	public Node(int data){
		this.data = data;
		left = null;
		right = null;
	}
}
