# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def isSameTree(self, p: Optional[TreeNode], q: Optional[TreeNode]) -> bool:
        def check(p: TreeNode, q: TreeNode) -> bool:
            if p is None and q is None:
                return True
            if p is None or q is None:
                return False
            if p.val != q.val:
                return False
            return True

        queue = []        
        queue.append((p, q))
        while queue:
            p1, q1 = queue.pop()

            if not check(p1, q1):
                return False

            if p1:
                queue.append((p1.left, q1.left))
                queue.append((p1.right, q1.right))            

        return True
