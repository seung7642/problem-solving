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
        
        left_child_leaf = self.flatten_tree(root.left)
        right_child_leaf = self.flatten_tree(root.right)

        if left_child_leaf:
            left_child_leaf.right = root.right
            root.right = root.left
            root.left = None

        return right_child_leaf if right_child_leaf else left_child_leaf
