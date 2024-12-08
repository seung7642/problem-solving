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
    
    private List<String> list = new ArrayList<>();
    
    public int sumRootToLeaf(TreeNode root) {
        traverse(root, "");
        
        return list.stream().mapToInt(s -> Integer.parseInt(s, 2)).sum();
    }
    
    private void traverse(TreeNode root, String bi) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            list.add(bi + root.val);
            return;
        }
        
        traverse(root.left, bi + root.val);
        traverse(root.right, bi + root.val);
    }
}