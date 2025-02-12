# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

from collections import deque

class Solution:
    def flatten(self, root: Optional[TreeNode]) -> None:
        if not root:
            return None

        START, END = 1, 2

        tail_node = None
        stack = deque([(root, START)])

        while stack:
            current_node, recursion_state = stack.pop()

            # We reached a leaf node. Record this as a tail node and move on.
            if not current_node.left and not current_node.right:
                tail_node = current_node
                continue

            # If the node is in the START state, it means we still 
            # haven't processed it's left child yet.
            if recursion_state == START:
                if current_node.left:
                    stack.append((current_node, END))
                    stack.append((current_node.left, START))
                elif current_node.right:
                    stack.append((current_node.right, START))
            else:
                # If the current node is in the END recursion state,
                # that means we did process one of it's children. Left
                # if it existed, else right.
                right_node = current_node.right
                
                # If there was a left child, there must have been a leaf
                # node and so, we would have set the tail_node.
                if tail_node:
                    tail_node.right = current_node.right
                    current_node.right = current_node.left
                    current_node.left = None
                    right_node = tail_node.right

                if right_node:
                    stack.append((right_node, START))
