package chapter4;


/**
 * @author zhuwh
 * @date 2019/2/22 13:57
 * @desc
 */
public class BinaryTreeIsBalanaced {

    public static int getHeight(TreeNode root) {
        if(root == null) {
            return 0; //base case
        }
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }

    public static boolean isBalanced(TreeNode root) {
        if(root == null) { //base case
            return true;
        }

        int heightDiff = getHeight(root.left) - getHeight(root.right);
        if(Math.abs(heightDiff) > 1) {
            return false;
        }
        else { //recurse
            return isBalanced(root.left) && isBalanced(root.right);
        }
    }
}
