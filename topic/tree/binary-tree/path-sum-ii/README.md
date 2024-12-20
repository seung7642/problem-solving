# Path Sum II

## 🚀 Problem  
Given the `root` of a binary tree and an integer `targetSum`, return all **root-to-leaf** paths where the sum of the node values in the path equals `targetSum`. Each path should be returned as a list of the node **values**, not node references.

A **root-to-leaf** path is a path starting from the root and ending at any leaf node. A **leaf** is a node with no children.

## 📝 Examples  

### Example 1
```mermaid
graph TD
    a[5] --- b1[4]
    a --- b2[8]
    b1 --- c1[11]
    c1 --- d1[7]
    c1 --- d2[2]
    b2 --- e1[13]
    b2 --- e2[4]
    e2 --- f1[1]
```

```
Input: root = root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
Output: [[5,4,11,2],[5,8,4,5]]
Explanation: There are two paths whosw sum equals targetSum:
5 + 4 + 11 + 2 = 22
5 + 8 + 4 + 5 = 22
```


## ⚡ Constraints  
- The number of nodes in the tree is in the range `[0, 5000]`
- `-1000 <= Node.val <= 1000`
- `-1000 <= targetSum <= 1000`

## 📚 Related Topics  
- Binary tree
- DFS
- Backtracking

## 🔗 References  
- https://leetcode.com/problems/path-sum/
