package offer;

import leetcode.tree.TreeNode;

/**
 * 二叉查找树的第 K 个结点
 */
public class KthNode {

    private TreeNode ret;
    private int cnt = 0;

    public TreeNode KthNode(TreeNode root ,int k){
        inOrder(root, k);
        return ret;
    }



    private void inOrder(TreeNode root, int k) {
        if (root == null || cnt >= k)
            return;
        inOrder(root.left, k);
        cnt++;
        if (cnt == k)
            ret = root;
        inOrder(root.right, k);
    }
}
