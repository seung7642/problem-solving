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
    
    private TreeNode tree = new TreeNode();
    private TreeNode next = tree;
    
    public TreeNode increasingBST(TreeNode root) {
        traverse(root);
        return tree.right;
    }
    
    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        
        traverse(root.left);
        next.right = new TreeNode(root.val);
        next = next.right;
        traverse(root.right);
    }
}