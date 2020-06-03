package offer;

import leetcode.tree.TreeNode;

/**
 * .二叉树的下一个节点
 * 题目描述
 * 给定一个二叉树和其中的一个节点，请找出中序遍历顺序的下一个节点并且返回。注意，树中的节点不仅包含左右子节点，同时包含指向父节点的指针。
 */
public class NextBinaryTreeNode {


    public BinaryTreeNode getNext(BinaryTreeNode pNode){
        //判断节点是否为空
        if(pNode==null)
        {
            return null;
        }
        //存储节点的下一个节点
        BinaryTreeNode pNext=null;
        //节点有右子树
        if(pNode.right!=null)
        {
            BinaryTreeNode pRight=pNode.right;
            while(pRight.left!=null)
            {
                pRight=pRight.left;
            }
            pNext=pRight;
        }
        //节点没有右子树
        else if(pNode.father!=null)
        {
            BinaryTreeNode pCurrent=pNode;
            BinaryTreeNode pParent=pNode.father;
            while(pParent!=null&&pParent.right==pCurrent)
            {
                pCurrent=pParent;
                pParent=pParent.father;
            }
            pNext=pParent;
        }
        return pNext;
    }

}
