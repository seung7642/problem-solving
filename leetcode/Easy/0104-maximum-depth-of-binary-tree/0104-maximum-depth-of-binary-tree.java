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
    
    private int depth;
    
    public int maxDepth(TreeNode root) {
        traverse(root, 1);
        return depth;
    }
    
    public void traverse(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) { // leaf node
            if (this.depth < depth) {
                this.depth = depth;
            }
            return;
        }
        traverse(root.left, depth + 1);
        traverse(root.right, depth + 1);
    }
}