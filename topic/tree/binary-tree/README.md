# Binary Tree

## Search
Binary Tree를 탐색하는 방법은 크게 두 가지로 나눌 수 있다.
1. DFS (Depth-First Search)
   - DFS 탐색은 다음 세 가지 방식으로 나눌 수 있다.
     - Pre-order
     - In-order
     - Post-order
2. BFS (Breadth-First Search)

### DFS (Depth-First Search)

#### Pre-order Traversal

```python
def preorder_traversal(root: TreeNode) -> List[int]:
    if not root:
        return
    return [root.val] + preorder_traversal(root.left) + preorder_traversal(root.right)
```

#### In-order Traversal

```python
def inorder_traversal(root: TreeNode) -> List[int]:
    if not root:
        return
    return inorder_traversal(root.left) + [root.val] + inorder_traversal(root.right)
```

#### Post-order Traversal

```python
def post_traversal(root: TreeNode) -> List[int]:
    if not root:
        return
    return postorder_traversal(root.left) + postorder_traversal(root.right) + [root.val]
```

### BFS (Breadth-First Search)
```python
from collections import deque

def levelorder(root: TreeNode) -> List[int]:
    if not root:
        return []

    result = []
    queue = deque([root])

    while queue:
        node = queue.popleft()
        result.append(node.val)
        if node.left:
            queue.append(node.left)
        if node.right:
            queue.append(node.right)
    
    return result
```

### Inorder 순회 배열과 Postorder 순회 배열을 가지고 역으로 원본 Binary Tree를 추출하는 방법
1. Postorder 순회 결과 배열의 가장 마지막 값을 찾는다.
    - Postorder 순회에서 마지막에 있는 값은 root 값이다.
2. 1번 단계에서 찾은 값을 기준으로 Inorder 순회 결과 배열을 분리한다.
    - Inorder 순회에서 root 값을 기준으로 좌측은 Left subtree, 우측은 Right subtree이다.
3. 2번 단계에서 분리한 Left subtree의 크기를 기준으로 Postorder 순회 배열도 분리한다.
    - Postorder 순회는 Left subtree가 먼저 순회한 뒤에 Right subtree를 순회하기 때문에 Left subtree(또는 Right subtree)의 크기를 안다면 분리할 수 있다.
4. 분리한 Left/Right subtree에 대해 1~3번 단계를 반복한다.

### BST(Binary Search Tree)의 Search
Binary Search Tree (BST)의 탐색 시간 복잡도는 트리의 구조에 따라 달라진다.
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

## Insert (작성 예정)

## Delete (작성 예정)
