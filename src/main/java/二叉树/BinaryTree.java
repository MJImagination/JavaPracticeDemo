package 二叉树;

public class BinaryTree {
    //	private TreeNode root = new TreeNode();
    public TreeNode getBinaryTree(int[] arr, int index) {
        // TODO Auto-generated method stub
        TreeNode node = null;
        if (index < arr.length) {
            int value = arr[index];
            node = new TreeNode(value);
            node.left = getBinaryTree(arr, index * 2 + 1);
            node.right = getBinaryTree(arr, index * 2 + 2);
            return node;
        }
        return node;
    }
}
