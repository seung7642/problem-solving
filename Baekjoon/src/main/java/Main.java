import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int V;
    private static BinaryTree tree = new BinaryTree();

    public static void main(String[] args) throws IOException {
        V = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < V - 1; i++) {
            int parentIdx = Integer.parseInt(st.nextToken());
            int childIdx = Integer.parseInt(st.nextToken());
            tree.insert(parentIdx, childIdx);
        }

//        13
//        1 2 1 3 2 4 3 5 3 6 4 7 5 8 5 9 6 10 6 11 7 12 11 13
        tree.preOrder(tree.root);
    }

    private static class BinaryTree {

        Node root;

        public void insert(int parentIdx, int childIdx) {
            if (root == null) {
                root = new Node(parentIdx);
            }

            Node node = find(root, parentIdx);
            if (node.left == null) {
                node.left = new Node(childIdx);
            } else {
                node.right = new Node(childIdx);
            }
        }

        private Node find(Node head, int idx) {
            if (head.idx == idx) return head;
            if (head.left != null) {
                Node node = find(head.left, idx);
                if (node != null) return node;
            }
            if (head.right != null) {
                Node node = find(head.right, idx);
                if (node != null) return node;
            }
            return null;
        }

        public void preOrder(Node head) {
            System.out.print(head.idx + " ");
            if (head.left != null) {
                preOrder(head.left);
            }
            if (head.right != null) {
                preOrder(head.right);
            }
        }

        private static class Node {
            int idx;
            Node left, right;

            public Node(int idx) {
                this.idx = idx;
                left = right = null;
            }
        }
    }
}
