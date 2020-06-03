package offer;

import leetcode.tree.TreeNode;

public class BalancedTree {

    private boolean IsBalanced = true;

    public boolean IsBalanced(TreeNode root){
        hight(root);
        return IsBalanced;
    }

    private int hight(TreeNode root){
        if(root ==null||!IsBalanced){
            return 0;
        }
        int left = hight(root.left);
        int right = hight(root.right);
        if(Math.abs(left-right)>1){
            IsBalanced = false;
        }

        return 1+Math.max(left,right);
    }
}
