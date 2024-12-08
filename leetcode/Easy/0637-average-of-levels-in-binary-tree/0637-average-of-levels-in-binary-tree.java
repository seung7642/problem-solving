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
    
    private Map<Integer, List<Integer>> map = new TreeMap<>();
    
    public List<Double> averageOfLevels(TreeNode root) {
        traverse(root, 0);
        
        List<Double> result = new LinkedList<>();
        map.forEach((key, values) -> {
            int size = values.size();
            double sum = (double) values.stream().mapToLong(Integer::longValue).sum();
            
            result.add(sum / size);
        });
        
        return result;
    }
    
    private void traverse(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        
        List<Integer> list = map.getOrDefault(depth, new ArrayList<>());
        list.add(root.val);
        map.put(depth, list);
        
        traverse(root.left, depth + 1);
        traverse(root.right, depth + 1);
    }
}