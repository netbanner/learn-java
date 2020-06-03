package offer;

import leetcode.tree.TreeNode;

/**
 * 树中两个节点的最低公共祖先
 * 二叉查找树中，两个节点 p, q 的公共祖先 root 满足 root.val >= p.val && root.val <= q.val。
 */
public class LowestCommonAncestor {

    public TreeNode lowestCommonAncestor(TreeNode root,TreeNode p1,TreeNode p2){
        if(root ==null){
            return root;
        }
        if(root.val>p1.val&&root.val>p2.val){
            return lowestCommonAncestor(root.left,p1,p2);
        }
        if(root.val<p1.val&&root.val<p2.val){
            return lowestCommonAncestor(root.right,p1,p2);
        }
        return root;
    }


    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }
}
