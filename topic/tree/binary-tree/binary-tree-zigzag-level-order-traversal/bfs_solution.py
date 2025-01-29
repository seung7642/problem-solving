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

        # start with the level 0 with a delimiter
        node_queue = deque([root, None])
        is_left_to_right = True

        while node_queue:
            current_node = node_queue.popleft()

            if current_node:
                if is_left_to_right:
                    level_list.append(current_node.val)
                else:
                    level_list.appendleft(current_node.val)

                for child in (current_node.left, current_node.right):
                    if child:
                        node_queue.append(child)
            else:
                # we finish one level
                result.append(list(level_list))

                if node_queue:
                    # add a delimiter to mark the level
                    node_queue.append(None)

                    # prepare for the next level
                    level_list = deque()
                    is_left_to_right = not is_left_to_right

        return result
