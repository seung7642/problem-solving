package dataStructure.binaryTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_5639 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String str = "";
        BinarySearchTree tree = new BinarySearchTree();
        while (true) {
            str = br.readLine();
            if (str == null || str.equals("")) break;
            int num = Integer.parseInt(str);
            tree.insert(num);
        }
        tree.postorderTree(tree.root, 0);
    }

    private static class BinarySearchTree {

        Node root = null;

        public void insert(int element) {
            if (root == null) {
                root = new Node(element);
                return;
            }

            Node head = root;
            Node current;

            while (true) {
                current = head;

                // 현재의 루트보다 작은 경우 왼쪽으로 탐색을 한다.
                if (head.data > element) {
                    head = head.leftChild;

                    // 왼쪽 자식 노드가 비어있는 경우 해당 위치에 추가할 노드를 삽입한다.
                    if (head == null) {
                        current.leftChild = new Node(element);
                        break;
                    }
                } else { // 현재의 루트보다 큰 경우 오른쪽으로 탐색을 한다.
                    head = head.rightChild;

                    // 오른쪽 자식 노드가 비어있는 경우 해당 위치에 추가할 노드를 삽입한다.
                    if (head == null) {
                        current.rightChild = new Node(element);
                        break;
                    }
                }
            }
        }

        public boolean remove(int element) {
            Node removeNode = root;
            Node parentOfRemoveNode = null;

            while (removeNode.data != element) {
                parentOfRemoveNode = removeNode;

                // 1. 현재 가리키는 노드와 삭제할 요소의 값을 비교 후 왼쪽 자식으로 갈지, 오른쪽 자식으로 갈지 결정한다.
                if (removeNode.data > element) {
                    removeNode = removeNode.leftChild;
                } else {
                    removeNode = removeNode.rightChild;
                }

                // 위에서 값 대소 비교 후 왼쪽 또는 오른쪽 노드로 이동을 했는데 null이라면,
                // 위에서 비교한 노드가 최종적인 리프 노드라는 의미이므로 탐색 실패.
                if (removeNode == null) return false;
            }

            // 삭제 대상 노드의 자식 노드 여부를 확인한다.
            if (removeNode.leftChild == null && removeNode.rightChild == null) {
                if (removeNode == root) {
                    root = null;
                } else if (removeNode == parentOfRemoveNode.rightChild) {
                    parentOfRemoveNode.rightChild = null;
                } else {
                    parentOfRemoveNode.leftChild = null;
                }
            } else if (removeNode.leftChild == null) {
                if (removeNode == root) {
                    root = removeNode.rightChild;
                } else if (removeNode == parentOfRemoveNode.rightChild) {
                    parentOfRemoveNode.rightChild = removeNode.rightChild;
                } else {
                    parentOfRemoveNode.leftChild = removeNode.rightChild;
                }
            } else if (removeNode.rightChild == null) {
                if (removeNode == root) {
                    root = removeNode.leftChild;
                } else if (removeNode == parentOfRemoveNode.rightChild) {
                    parentOfRemoveNode.rightChild = removeNode.leftChild;
                } else {
                    parentOfRemoveNode.leftChild = removeNode.leftChild;
                }
            } else {
                // 삭제 대상 노드에 두 개의 자식이 존재하는 경우,
                // 삭제 대상 노드의 왼쪽 서브 트리에서 가장 큰 값을 올리거나,   <---- 1번
                // 삭제 대상 노드의 오른쪽 서브 트리에서 가장 작은 값을 올린다.  <---- 2번
                // 해당 코드는 2번을 구현한다.

                // 삭제 대상 노드의 자식 노드 중에서 대체될 노드(replaceNode)를 찾는다.
                Node parentOfReplaceNode = removeNode;

                // 삭제 대상 노드의 오른쪽 서브 트리 탐색 지정
                Node replaceNode = parentOfReplaceNode.rightChild;

                while (replaceNode.leftChild != null) {
                    // 가장 작은 값을 찾기 위해 왼쪽 자식 노드로 탐색한다.
                    parentOfReplaceNode = replaceNode;
                    replaceNode = replaceNode.leftChild;
                }

                if (replaceNode != removeNode.rightChild) {
                    // 가장 작은 값을 선택하기 때문에 대체 노드의 왼쪽 자식은 빈 노드가 된다.
                    parentOfReplaceNode.leftChild = replaceNode.rightChild;

                    // 대체할 노드의 오른쪽 자식 노드를 삭제할 노드의 오른쪽으로 지정한다.
                    replaceNode.rightChild = removeNode.rightChild;
                }

                // 삭제할 노드가 루트 노드인 경우 대체할 노드로 바꾼다.
                if (removeNode == root) {
                    root = replaceNode;
                } else if (removeNode == parentOfRemoveNode.rightChild) {
                    parentOfRemoveNode.rightChild = replaceNode;
                } else {
                    parentOfRemoveNode.leftChild = replaceNode;
                }

                // 삭제 대상 노드의 왼쪽 자식을 잇는다.
                replaceNode.leftChild = removeNode.leftChild;
            }

            return true;
        }

        // 중위 순회
        public void inorderTree(Node root, int depth) {
            if (root == null) return;

            inorderTree(root.leftChild, depth + 1);
            for (int i = 0; i < depth; i++) {
                System.out.print("ㄴ");
            }
            System.out.println(root.data);
            inorderTree(root.rightChild, depth + 1);
        }

        // 후위 순회
        public void postorderTree(Node root, int depth) {
            if (root == null) return;

            postorderTree(root.leftChild, depth + 1);
            postorderTree(root.rightChild, depth + 1);
//            for (int i = 0; i < depth; i++) {
//                System.out.print("ㄴ");
//            }
            System.out.println(root.data);
        }

        // 전위 순회
        public void preorderTree(Node root, int depth) {
            if (root == null) return;

            for (int i = 0; i < depth; i++) {
                System.out.print("ㄴ");
            }
            System.out.println(root.data);
            preorderTree(root.leftChild, depth + 1);
            preorderTree(root.rightChild, depth + 1);
        }

        private class Node {
            int data;
            Node leftChild;
            Node rightChild;

            public Node(int data) {
                this.data = data;
                leftChild = null;
                rightChild = null;
            }
        }
    }
}
