

public class BinaryTree {

    Node root; 
    
    public BinaryTree() { 
        root = null; 
    } 
    
    /**
     * Create a Binary Tree from Array
     * @param arr
     * @param root
     * @param i
     * @return
     */
    public Node insertLevelOrder(int[] arr, Node root, 
            int i) { 
		// Base case for recursion 
		if (i < arr.length) { 
		Node temp = new Node(arr[i]); 
		root = temp; 
		
		// insert left child 
		root.left = insertLevelOrder(arr, root.left, 
		         2 * i + 1); 
		
		// insert right child 
		root.right = insertLevelOrder(arr, root.right, 
		           2 * i + 2); 
		} 
		return root; 
		} 
    /**
     * Pre order traversal
     * @param node
     */
    public void printPreorder(Node node)  { 
        if (node == null) 
            return; 
  
        /* first print data of node */
        System.out.print(node.data + " "); 
  
        /* then recur on left sutree */
        printPreorder(node.left); 
  
        /* now recur on right subtree */
        printPreorder(node.right); 
    } 
 
    /**
     * In Order traversal
     * @param node
     */
    public void printInorder(Node node) { 
	        if (node == null) 
	            return; 
	  
	        /* first recur on left child */
	        printInorder(node.left); 
	  
	        /* then print the data of node */
	        System.out.print(node.data + " "); 
	  
	        /* now recur on right child */
	        printInorder(node.right); 
	    } 
    
    /**
     * Post Order traversal
     * @param node
     */
    public void printPostorder(Node node) { 
	        if (node == null) 
	            return; 
	  
	        // first recur on left subtree 
	        printPostorder(node.left); 
	  
	        // then recur on right subtree 
	        printPostorder(node.right); 
	  
	        // now deal with the node 
	        System.out.print(node.data + " "); 
	    } 

    /**
     * Print nodes at K distance
     * @param node
     * @param k
     */
    public void printKDistant(Node node, int k)  { 
        if (node == null) 
            return; 
        if (k == 0)  
        { 
            System.out.print(node.data + " "); 
        }  
        else 
        { 
            printKDistant(node.left, k - 1); 
            printKDistant(node.right, k - 1); 
        } 
    } 
    

    /**
	 * Is a valid binary search tree - left < root < right
	 * @param root
	 * @return
	 */
	public static boolean isValidBST(Node root) {
        boolean validBst = true;
		if(root == null){
			return validBst;
		}
		if(null != root.left || null != root.right ){
			System.out.println("----");
			System.out.println("root.data "+root.data);
			
			if(null != root.left && root.left.data >= root.data){
				System.out.println("root.left.data "+root.left.data);
				validBst = false;
				System.out.println("--validBst "+validBst);
				return validBst;
			}else if(null != root.right && root.right.data <= root.data){
				System.out.println("root.right.data "+root.right.data);
				validBst = false;
				return validBst;
			}else{
				if(null != root.left)
					validBst = isValidBST(root.left);
				if(null != root.right)
					validBst = isValidBST(root.right);
			}
		}
		return validBst;
    }
	 
	/**
	 * Max height of tree from the node
	 * @param node
	 * @return
	 */
	public static int height(Node node)   { 
        /* base case tree is empty */
        if (node == null) 
            return 0; 
   
        /* If tree is not empty then height = 1 + max of left 
         height and right heights */
        return 1 + Math.max(height(node.left), height(node.right)); 
    } 
	
	/**
	 * Is tree balanced - If height diff of left and right node is not more than 1 
	 * @param node
	 * @return
	 */
	public static boolean isBalanced(Node node)  
    { 
        int lh;    
        int rh;
   
        /* If tree is empty then return true */
        if (node == null) 
            return true; 
   
        /* Get the height of left and right sub trees */
        lh = height(node.left); 
        rh = height(node.right); 
   
        if (Math.abs(lh - rh) <= 1
                && isBalanced(node.left) 
                && isBalanced(node.right))  
            return true; 
   
        return false; 
    } 
	
	public int maxDepth(Node node,int depth,int maxDepth){
		//int maxDepth =0;
		if(node == null){
			return maxDepth;
		}
		if(node.left == null && node.right == null){
			maxDepth = Math.max(maxDepth, depth);
			System.out.println("maxDepth "+maxDepth);
			return maxDepth;
		}
		maxDepth = maxDepth(node.left,depth+1,maxDepth);
		maxDepth = maxDepth(node.right,depth+1,maxDepth);
		
		return maxDepth;
	}
	
	@Override
	public String toString() {
		return "BinaryTree [root=" + root + "]";
	}

    
}
