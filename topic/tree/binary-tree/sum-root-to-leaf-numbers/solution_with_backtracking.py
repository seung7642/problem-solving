# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def sumNumbers(self, root: Optional[TreeNode]) -> int:
        return self.backtracking(root, "")

    def backtracking(self, root: TreeNode, acc: str) -> int:
        if not root:
            return 0

        acc += str(root.val)
        if not root.left and not root.right:
            return int(acc)

        return self.backtracking(root.left, acc) + self.backtracking(root.right, acc)
