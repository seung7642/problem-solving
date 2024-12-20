# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def pathSum(self, root: Optional[TreeNode], targetSum: int) -> List[List[int]]:
        result = []
        self.dfs(root, targetSum, result, [])
        return result

    def dfs(self, root: TreeNode, sum: int, result: List[List[int]], path: List[int]):
        if not root:
            return

        sum -= root.val
        path.append(root.val)
        
        if not root.left and not root.right and sum == 0:
            result.append(path[:])

        self.dfs(root.left, sum, result, path)
        self.dfs(root.right, sum, result, path)

        path.pop()
            