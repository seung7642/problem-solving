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
    
    private List<Integer> list = new ArrayList<>();
    
    public List<Integer> getLonelyNodes(TreeNode root) {
        traverse(root);
        return list;
    }
    
    private void traverse(TreeNode root) {
        if (root.left != null) {
            if (root.right == null) {
                list.add(root.left.val);
            }
            traverse(root.left);
        }
        if (root.right != null) {
            if (root.left == null) {
                list.add(root.right.val);
            }
            traverse(root.right);
        }
    }
}