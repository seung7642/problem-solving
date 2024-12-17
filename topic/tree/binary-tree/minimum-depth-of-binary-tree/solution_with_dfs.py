# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def minDepth(self, root: Optional[TreeNode]) -> int:
        return self.traversal(root, 1)

    def traversal(self, root: TreeNode, depth: int) -> int:
        if not root:
            return 0

        leftChildDepth = self.traversal(root.left, depth)
        rightChildDepth = self.traversal(root.right, depth)
        if leftChildDepth == 0:
            return 1 + rightChildDepth
        elif rightChildDepth == 0:
            return 1 + leftChildDepth
        else:
            return 1 + min(leftChildDepth, rightChildDepth)
        