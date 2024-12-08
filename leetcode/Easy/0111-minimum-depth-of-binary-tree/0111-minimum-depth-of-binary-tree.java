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
    
    private int depth = Integer.MAX_VALUE;
    
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        traverse(root, 1);
        return depth;
    }
    
    private void traverse(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            this.depth = Math.min(this.depth, depth);
            return;
        }
        
        traverse(root.left, depth + 1);
        traverse(root.right, depth + 1);
    }
}