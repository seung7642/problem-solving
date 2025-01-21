# Binary Tree

## Traversal

### Pre-order Traversal
Visit the root node first, then traverse the left subtree, and finally traverse the right subtree.

```python
def preorder_traversal(root: TreeNode) -> List[int]:
    result = []

    def traversal(root: TreeNode) -> None:
        if not root:
            return

        result.append(root.val)
        traversal(root.left)
        traversal(root.right)

    traversal(root)
    return result
```

### In-order Traversal
Traverse the left subtree first, then visit the root node, and finally traverse the right subtree.

```python
def inorder_traversal(root: TreeNode) -> List[int]:
    result = []

    def traversal(root: TreeNode) -> None:
        if not root:
            return

        traversal(root.left)
        result.append(root.val)
        traversal(root.right)

    traversal(root)
    return result
```

### Post-order Traversal
Traverse both left and right subtrees before visiting the root node.

```python
def post_traversal(root: TreeNode) -> List[int]:
    result = []

    def traversal(root: TreeNode) -> None:
        if not root:
            return

        traversal(root.left)
        traversal(root.right)
        result.append(root.val)

    traversal(root)
    return result
```

## Insert

## Delete
