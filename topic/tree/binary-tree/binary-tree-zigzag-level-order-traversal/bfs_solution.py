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

        result = []
        level_list = deque()
        node_queue = deque([root, None])
        is_left_to_right = True
        while node_queue:
            cur_node = node_queue.popleft()

            if cur_node is None:
                if node_queue:
                    node_queue.append(None)

                result.append(list(level_list))
                level_list = deque()
                is_left_to_right = not is_left_to_right
                continue
            
            if is_left_to_right:
                level_list.append(cur_node.val)
            else:
                level_list.appendleft(cur_node.val)
            
            for child in (cur_node.left, cur_node.right):
                if child:
                    node_queue.append(child)

        return result