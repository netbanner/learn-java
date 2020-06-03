package offer;

import leetcode.tree.TreeNode;

/**
 * 二叉树的深度
 */
public class TreeDept {

    public int TreeDept(TreeNode root){
        return root ==null?0:1+Math.max(TreeDept(root.left),TreeDept(root.right));
    }
}
