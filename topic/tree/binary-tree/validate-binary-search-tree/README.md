# Validate Binary Search Tree

## 🚀 Problem  
Given the `root` of a binary tree, determine if it is a valid binary search tree (BST).

A valid BST is defined as follows:
- The left subtree of a node contains only nodes with keys less than the node's key.
- The right subtree of a node contains only nodes with keys greater than the node's key.
- Both the left and right subtrees must also be binary search trees.

## 📝 Examples  

### Example 1

```
Input: root = [2,1,3]
Output: true
```

### Example 2

```
Input: root = [5,1,4,null,null,3,6]
Output: false
```

## ⚡ Constraints  
- The number of nodes in the tree is in the range `[1, 1000]`
- `-2^31 <= Node.val <= 2^31 - 1`

## 📚 Related Topics  
- Binary tree

## 🔗 References  
- https://leetcode.com/problems/validate-binary-search-tree/
