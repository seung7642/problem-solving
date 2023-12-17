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
    
    private int[] nums;
    
    public TreeNode helper(int left, int right) {
        if (left > right) {
            return null;
        }
        
        int p = (left + right) / 2;
        
        TreeNode root = new TreeNode(nums[p]);
        root.left = helper(left, p - 1);
        root.right = helper(p + 1, right);
        return root;
    }
    
    public TreeNode sortedArrayToBST(int[] nums) {
        this.nums = nums;
        return makeBinarySearchTree(0, nums.length - 1);
    }
    
    public TreeNode makeBinarySearchTree(int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right) / 2;
        
        TreeNode root = new TreeNode(nums[mid]);
        root.left = makeBinarySearchTree(left, mid - 1);
        root.right = makeBinarySearchTree(mid + 1, right);
        return root;
    }
}