# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def recoverTree(self, root: Optional[TreeNode]) -> None:
        def inorder_traverse(root: TreeNode) -> List[int]:
            return inorder_traverse(root.left) + [root.val] + inorder_traverse(root.right) if root else []

        def find_two_swapped(nums: List[int]) -> (int, int):
            n = len(nums)
            x = y = (
                None # Initialize x and y as a value that cannot be the value of a node.
            )

            for i in range(n - 1):
                if nums[i + 1] < nums[i]:
                    y = nums[i + 1]
                    if x is None:
                        x = nums[i]
                    else:
                        break
            return x, y

        def recover(root: TreeNode, count: int) -> None:
            if not root:
                return

            if root.val == x or root.val == y:
                root.val = y if root.val == x else x
                count -= 1
                if count == 0:
                    return

            recover(root.left, count)
            recover(root.right, count)

        nums = inorder_traverse(root)
        x, y = find_two_swapped(nums)
        recover(root, 2)
        