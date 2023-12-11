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
    
    public TreeNode searchBST(TreeNode root, int val) {
        return traverse(root, val);
    }
    
    private TreeNode traverse(TreeNode root, int val) {
        if (root.val == val) {
            return root;
        }
        if (root.left != null) {
            TreeNode res1 = traverse(root.left, val);
            if (res1 != null) {
                return res1;
            }
        }
        if (root.right != null) {
            TreeNode res2 = traverse(root.right, val);
            if (res2 != null) {
                return res2;
            }
        }
        return null;
    }
}