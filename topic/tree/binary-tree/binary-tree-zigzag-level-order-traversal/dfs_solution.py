# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

from collections import deque

class Solution:
    def zigzagLevelOrder(self, root: Optional[TreeNode]) -> List[List[int]]:
        if root is None:
            return []

        results = []
        
        def dfs(root: TreeNode, level: int) -> None:
            if level >= len(results):
                results.append(deque([root.val]))
            else:
                if level % 2 == 0:
                    results[level].append(root.val)
                else:
                    results[level].appendleft(root.val)

            for child in [root.left, root.right]:
                if child is not None:
                    dfs(child, level + 1)

        dfs(root, 0)
        return [list(d) for d in results]
        