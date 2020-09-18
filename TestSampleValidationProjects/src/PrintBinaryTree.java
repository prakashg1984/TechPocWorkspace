
public class PrintBinaryTree {

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	        
			BinaryTree tree = new BinaryTree(); 
	        /* Let us construct the tree shown in above diagram */
	        tree.root = new Node(1); 
	        tree.root.left = new Node(2); 
	        tree.root.right = new Node(3); 
	        tree.root.left.left = new Node(4); 
	        tree.root.left.right = new Node(5); 

	        BinaryTree tree2 = new BinaryTree(); 
	        tree2.root = new Node(1); 
	        tree2.root.left = new Node(0); 
	        tree2.root.right = new Node(6); 
	        tree2.root.left.left = new Node(0); 
	        tree2.root.left.right = new Node(2); 
	        tree2.root.right.left = new Node(1); 
	        tree2.root.right.right = new Node(7); 

	        
	        System.out.println(tree.toString());
	        
	        System.out.println("\nPre order traversal is :" );
	        tree.printPreorder(tree.root);
	        
	        System.out.println("\nIn order traversal is :" );
	        tree.printInorder(tree.root);
	        
	        System.out.println("\nPost order traversal is :" );
	        tree.printPostorder(tree.root);

	        System.out.println("\nisValidBST : "+isValidBST(tree2.root));
	        
	        System.out.println("\nHeight of tree2 : "+height(tree2.root));
	        System.out.println("\nHeight of tree1 : "+height(tree.root));
	        
	        System.out.println("\nisBalanced tree2 : "+isBalanced(tree2.root));
	        System.out.println("\nisBalanced tree1 : "+isBalanced(tree.root));
	        
	        System.out.println("\nprintKDistant tree2 : ");
	        tree2.printKDistant(tree2.root, 2);
	        System.out.println("\nprintKDistant tree1 : ");
	        tree.printKDistant(tree.root, 1);
	        
	        int arr[] = { 1, 2, 3, 4, 5, 6, 6, 6, 6 }; 
	        BinaryTree tree3 = new BinaryTree(); 
	        tree3.root = tree3.insertLevelOrder(arr, tree3.root, 0);
	        System.out.println("\n"+tree3.toString());
	        tree3.printInorder(tree3.root);

	        System.out.println("Depth :"+ tree3.maxDepth(tree3.root, 0, 0));
	        System.out.println("Depth :"+ tree.maxDepth(tree.root, 0, 0));

	}
	
	/**
	 * Is a valid binary search tree
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
	 * Max height of tree 
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
	 * Is tree balanced
	 * @param node
	 * @return
	 */
	public static boolean isBalanced(Node node)  
    { 
        int lh; /* for height of left subtree */
   
        int rh; /* for height of right subtree */
   
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
   
        /* If we reach here then tree is not height-balanced */
        return false; 
    } 


}
