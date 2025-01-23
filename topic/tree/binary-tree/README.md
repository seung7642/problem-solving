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

## Search
Binary Search Tree (BST)의 탐색 시간 복잡도는 트리의 구조에 따라 달라집니다.
- Balanced BST: O(log n)
  - 각 레벨을 내려갈 때마다 검색 범위가 절반으로 감소
  - 이진 탐색과 유사한 원리로 동작
- Unbalanced BST: O(n)
  - 최악의 경우, 트리가 한쪽으로 치우쳐진 경우
  - 연결 리스트와 유사한 형태가 될 수 있음

```python
def search_bst(root: TreeNode, target: int) -> TreeNode:
    if not root or root.val == target:
        return root

    if target < root.val:
        return search_bst(root.left, target)
    return search_bst(root.right, target)
```

## Insert

## Delete
