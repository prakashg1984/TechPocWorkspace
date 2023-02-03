
public class MainClassBinaryTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 }; 
        MyBinaryTree tree1 = new MyBinaryTree(); 
        tree1.root = tree1.createBinaryTree(arr, tree1.root, 0);
        tree1.printPreOrderTraversal(tree1.root);
        tree1.printInOrderTraversal(tree1.root);
        tree1.printPostOrderTraversal(tree1.root);
        tree1.printLevelOrder(tree1.root);

        int height = tree1.height(tree1.root);
        System.out.println("Height "+height);
        
        tree1.nodeAtKDistance(tree1.root, 3);
        
        System.out.println(tree1.isValidBST(tree1.root));
        
        int arr2[] = { 2, 1, 3, 0, 5, 2, 7 }; 
        MyBinaryTree tree2 = new MyBinaryTree(); 
        tree2.root = tree2.createBinaryTree(arr2, tree2.root, 0);
        System.out.println(tree2.isValidBST(tree2.root));

        
        MyBinaryTree finalTree = new MyBinaryTree(); 
        int[] inOrder = { 4, 2, 5, 1, 6, 7, 3 ,8 }; 
        int[] preOrder = { 1, 2, 4, 5, 3, 7 , 6 , 8 }; 
        int len = inOrder.length; 
        Node root = finalTree.buildTree(preOrder,inOrder, 0, len - 1); 
        finalTree.printInOrderTraversal(root);
        System.out.println("\n");
        finalTree.printPreOrderTraversal(root);
        System.out.println("\n");
        finalTree.printLevelOrder(root);
	}

}
