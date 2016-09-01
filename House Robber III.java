/*
The thief has found himself a new place for his thievery again. 
There is only one entrance to this area, called the "root."
Besides the root, each house has one and only one parent house.
After a tour, the smart thief realized that "all houses in this place forms a binary tree". 
It will automatically contact the police if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example 1:
     3
    / \
   2   3
    \   \ 
     3   1
Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
Example 2:
     3
    / \
   4   5
  / \   \ 
 1   3   1
Maximum amount of money the thief can rob = 4 + 5 = 9.



*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public int rob(TreeNode root) {
        int []ans = robInTree(root);
        return Math.max(ans[0],ans[1]);
    }
    public int[] robInTree(TreeNode root)
    {
        if(root == null)
        {
            return new int[]{0,0};
        }
        int []leftSubTreeVal = robInTree(root.left);
        int []rightSubTreeVal = robInTree(root.right);
        int []curTreeVal = new int[2];
        //if current node robbed, left and right child can not be robbed.
        curTreeVal[0] = leftSubTreeVal[1] + rightSubTreeVal[1]+ root.val;
        
        //if current node not robbed, left robbed + right robbed, left robbed + right notrobbed
        //left not robbed + right robbed, left not robbed + right not robbed.
        curTreeVal[1] = leftSubTreeVal[0] + rightSubTreeVal[0];
        curTreeVal[1] = Math.max(curTreeVal[1],leftSubTreeVal[1] + rightSubTreeVal[1]);
        curTreeVal[1] = Math.max(curTreeVal[1],leftSubTreeVal[0] + rightSubTreeVal[1]);
        curTreeVal[1] = Math.max(curTreeVal[1],leftSubTreeVal[1] + rightSubTreeVal[0]);
        return curTreeVal;
    }
}