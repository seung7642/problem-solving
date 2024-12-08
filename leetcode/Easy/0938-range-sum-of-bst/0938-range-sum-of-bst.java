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
    public int rangeSumBST(TreeNode root, int low, int high) {
        int val = root.val;
        return cal(root, low, high);
    }
    
    private int cal(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }
        
        if (low <= root.val && root.val <= high) {
            return root.val + cal(root.left, low, high) + cal(root.right, low, high);
        } else if (root.val < low) {
            return cal(root.right, low, high);
        } else if (root.val > high) {
            return cal(root.left, low, high);
        }
        return 0;
    }
}