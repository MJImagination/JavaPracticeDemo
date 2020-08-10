package 二叉树;

public class Test {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] arr = new int[]{
                1, 2, 3, 4, 5, 6, 7
        };
        BinaryTree binaryTree = new BinaryTree();
        TreeNode root = binaryTree.getBinaryTree(arr, 0);
        new DFS().getDFS(root);
        System.out.println("\n深度优先遍历结束。。。");
        new BFS().getBFS(root);
        System.out.println("\n广度优先遍历结束。。。");

        System.out.println(getDeep(root, 0));
        System.out.println(treeDepth(root));
    }

    /**
     * 计算深度 （我自己的）
     *
     * @param treeNode
     * @param num
     * @return
     */
    public static int getDeep(TreeNode treeNode, int num) {
        if (treeNode.left != null) {
            return getDeep(treeNode.left, ++num);
        }

        if (treeNode.right != null) {
            return getDeep(treeNode.right, ++num);
        }

        if (treeNode.left == null && treeNode.right == null) {
            return num + 1;
        }

        return num;
    }

    /**
     * 计算深度 （网上的）
     *
     * @param root
     * @return
     */
    public static int treeDepth(TreeNode root) {
        if (root == null) {
            return 0;

        }
        int left = treeDepth(root.left) + 1;
        int right = treeDepth(root.right) + 1;
        return Math.max(left, right);
    }

}
