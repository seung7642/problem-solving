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
    
    private boolean result = false;
    
    public boolean hasPathSum(TreeNode root, int targetSum) {
        traverse(root, targetSum, 0);
        return result;
    }
    
    private void traverse(TreeNode root, int targetSum, int sum) {
        if (root == null || result) {
            return;
        }
        if (root.left == null && root.right == null) {
            if (targetSum == (sum + root.val)) {
                result = true;
            }
            return;
        }
        
        traverse(root.left, targetSum, sum + root.val);
        traverse(root.right, targetSum, sum + root.val);
    }
}