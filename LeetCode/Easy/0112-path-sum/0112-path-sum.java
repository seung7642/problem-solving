/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return traverse(root, targetSum, 0);
    }
    
    private boolean traverse(TreeNode root, int targetSum, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return targetSum == (sum + root.val);
        }
        
        return traverse(root.left, targetSum, sum + root.val) || traverse(root.right, targetSum, sum + root.val);
    }
}