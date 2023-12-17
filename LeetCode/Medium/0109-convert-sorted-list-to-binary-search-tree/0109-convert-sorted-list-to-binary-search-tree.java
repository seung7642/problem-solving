/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
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
    
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        do {
            list.add(head.val);
        } while ((head = head.next) != null);
        
        return makeBinarySearchTree(0, list.size() - 1);
    }
    
    public TreeNode makeBinarySearchTree(int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(list.get(mid));
        root.left = makeBinarySearchTree(left, mid - 1);
        root.right = makeBinarySearchTree(mid + 1, right);
        return root;
    }
}