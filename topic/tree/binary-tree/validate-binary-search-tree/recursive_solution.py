# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def isValidBST(self, root: Optional[TreeNode]) -> bool:
        def dfs(root: TreeNode) -> (bool, int, int):
            if not root:
                return True, None, None
            
            l_result, l_min, l_max = dfs(root.left)
            r_result, r_min, r_max = dfs(root.right)
            
            if not l_result or not r_result:
                return False, None, None

            if l_max is not None and l_max >= root.val:
                return False, None, None

            if r_min is not None and r_min <= root.val:
                return False, None, None

            return (
                True, 
                l_min if l_min is not None else root.val, 
                r_max if r_max is not None else root.val
            )

        result, _, _ = dfs(root)
        return result
        