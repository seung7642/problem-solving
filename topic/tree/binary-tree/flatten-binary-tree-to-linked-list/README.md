# Flatten Binary Tree to Linked List

## 🚀 Problem  
Given the `root` of a binary tree, flatten the tree into a "linked list":
- The "linked list" should use the same `TreeNode` class where the `right` child pointer points to the next node in the list and the `left` child pointer is always `null`.
- The "linked list" should be in the same order as a pre-order traversal of the binary tree.

## 📝 Examples  

### Example 1
```
Input: root = [1,2,5,3,4,null,6]
Output: [1,null,2,null,3,null,4,null,5,null,6]
```

### Example 2
```
Input: root = []
Output: []
```

## ⚡ Constraints  
- The number of nodes in the tree is in the range `[0, 2000]`
- `-100 <= Node.val <= 100`

## 📚 Related Topics  
- Binary tree
- DFS

## 🔗 References  
- https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
