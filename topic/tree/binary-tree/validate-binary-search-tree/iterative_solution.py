from collections import deque

# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def isValidBST(self, root: Optional[TreeNode]) -> bool:
        if not root:
            return True

        q = deque([(root, -math.inf, math.inf)])
        while q:
            root, lower, upper = q.popleft()
            if not root:
                continue
            
            val = root.val
            if val <= lower or val >= upper:
                return False

            q.append((root.right, val, upper))
            q.append((root.left, lower, val))

        return True
        