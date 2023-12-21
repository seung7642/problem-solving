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
    
    private List<List<Integer>> results = new ArrayList<>();
    
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        traverse(root, targetSum, new ArrayList<>());
        return results;
    }
    
    private void traverse(TreeNode root, int targetSum, List<Integer> list) {
        if (root == null) {
            return;
        }
        
        targetSum -= root.val;
        list.add(root.val);
        
        if (root.left == null && root.right == null) {
            if (targetSum == 0) {
                results.add(list);
            }
            return;
        }
        
        traverse(root.left, targetSum, new ArrayList<>(list));
        traverse(root.right, targetSum, new ArrayList<>(list));
    }
}