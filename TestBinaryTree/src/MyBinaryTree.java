
public class MyBinaryTree {

	Node root;
	
	public static Node createBinaryTree(int[] data,Node thisRoot, int level){
		
		if(level < data.length){
			Node temp = new Node(data[level]); 
			thisRoot = temp;
			
			thisRoot.left = createBinaryTree(data, thisRoot.left, 2*level + 1);
			thisRoot.right = createBinaryTree(data, thisRoot.right, 2*level + 2);
		}
		return thisRoot;
	}
	
	public static void printPreOrderTraversal(Node thisRoot){
		
		if (thisRoot == null) 
            return; 
		
		System.out.println(thisRoot.getData());
		
		printPreOrderTraversal(thisRoot.left);
		
		printPreOrderTraversal(thisRoot.right);
	}
	
	public static void printInOrderTraversal(Node thisRoot){
		
		if (thisRoot == null) 
            return; 
		
		printInOrderTraversal(thisRoot.left);
		
		System.out.println(thisRoot.getData());
		
		printInOrderTraversal(thisRoot.right);
	}
	
	public static void printPostOrderTraversal(Node thisRoot){
		
		if (thisRoot == null) 
            return; 
		
		printPostOrderTraversal(thisRoot.left);
				
		printPostOrderTraversal(thisRoot.right);
		
		System.out.println(thisRoot.getData());

	}
	
	public static int height(Node rootNode){
		if (rootNode == null){
			return 0;
		}
		
		return 1 + Math.max(height(rootNode.left), height(rootNode.right));
	}
	
	public static void nodeAtKDistance(Node rootNode , int distance){
	  if (rootNode == null) 
            return; 
		if(distance == 0){
			System.out.println("nodeAtKDistance "+rootNode.getData());
		}else{
			nodeAtKDistance(rootNode.left, distance - 1);
			nodeAtKDistance(rootNode.right, distance - 1);

		}
	}
	
	public static boolean isValidBST(Node rootNode){
		boolean validBst = true;
		
		if(null == rootNode){
			return validBst;
		}
		if(null != rootNode.left || null != rootNode.right ){
			
			if(null != rootNode.left && rootNode.left.getData() >= rootNode.getData()){
				validBst = false;
				return validBst;
			}else if(null != rootNode.right && rootNode.right.getData() <= rootNode.getData()){
				validBst = false;
				return validBst;
			}else {
				if(null != rootNode.left){
					validBst = isValidBST(rootNode.left);
				}
				if(null != rootNode.right){
					validBst = isValidBST(rootNode.right);
				}
			}
		}
		return validBst;
	}
	
	private static int preOrderIndex = 0;
	public Node buildTree(int[] preOrder, int[] inOrder, int startIndex, int endIndex){
		
		if(startIndex > endIndex){
			return null;
		}
		System.out.println("startIndex "+startIndex);
		System.out.println("endIndex "+endIndex);
		Node rootNode = new Node(preOrder[preOrderIndex++]);
		
		int inOrderIndex = search(inOrder,startIndex,endIndex,rootNode.getData());
		
		rootNode.left = buildTree(preOrder, inOrder, startIndex, inOrderIndex-1);
		rootNode.right = buildTree(preOrder, inOrder, inOrderIndex+1, endIndex);
		
		return rootNode;
	}
	
	private static int search(int[] inOrder, int startIndex, int endIndex,int data){
		for(int i=startIndex; i<= endIndex ; i++){
			if(inOrder[i] == data){
				return i;
			}
		}
		return 0;
	}
	
	public static void printLevelOrder(Node rootNode){
		int height = height(rootNode);
		for(int i=0;i<=height;i++){
			nodeAtKDistance(rootNode, i);
		}
		
	}
}
