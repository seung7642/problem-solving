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
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        return traverse(root1, root2);
    }
    
    private TreeNode traverse(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return null;
        }
        
        int value = (root1 == null ? 0 : root1.val) + (root2 == null ? 0 : root2.val);
        TreeNode root = new TreeNode(value);
        
        if (root1 == null) {
            root.left = root2.left;
            root.right = root2.right;
        } else if (root2 == null) {
            root.left = root1.left;
            root.right = root1.right;
        } else {
            root.left = traverse(root1.left, root2.left);
            root.right = traverse(root1.right, root2.right);    
        }
        
        return root;
    }
}