package offer;

import leetcode.tree.TreeNode;

public class SymmetricalTree {

    public boolean isSymmetrical(TreeNode root){
        if(root ==null){
            return  true;
        }
        return isSymmetrical(root.left,root.right);
    }

    public boolean isSymmetrical(TreeNode p1,TreeNode p2){
        if(p1==null&&p2==null){
            return true;
        }
        if(p1==null||p2==null){
            return false;
        }
        if(p1.val !=p2.val){
            return false;
        }
        return isSymmetrical(p1.right,p2.left)&&isSymmetrical(p1.left,p2.right);
    }
}
