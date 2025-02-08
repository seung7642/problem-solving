# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def flatten(self, root: Optional[TreeNode]) -> None:
        """
        Do not return anything, modify root in-place instead.
        """
        self.flatten_tree(root)

    def flatten_tree(self, root: TreeNode):
        if not root:
            return None
            
        if not root.left and not root.right:
            return root.val
        
        
