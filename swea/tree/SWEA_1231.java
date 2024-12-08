package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1231 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int T;
    private static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        while (T++ < 10) {
            ans.append("#").append(T).append(" ");

            BinaryTree<Character> tree = new BinaryTree<>();
            int N = Integer.parseInt(br.readLine());

            StringTokenizer st;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                st.nextToken();
                sb.append(st.nextToken());
            }

            Character[] arr = sb.toString().chars().mapToObj(c -> (char) c).toArray(Character[]::new);
            tree.root = tree.insert(arr, tree.root, 0);
            tree.inorderTree(tree.root);
            ans.append("\n");
        }
        System.out.println(ans);
    }

    private static class BinaryTree<T> {

        private Node<T> root = null;

        public Node<T> insert(T[] arr, Node<T> root, int i) {
            if (i < arr.length) {
                root = new Node<>(arr[i]);
                root.leftChild = insert(arr, root.leftChild, 2 * i + 1);
                root.rightChild = insert(arr, root.rightChild, 2 * i + 2);
            }
            return root;
        }

        public void inorderTree(Node<T> root) {
            if (root != null) {
                inorderTree(root.leftChild);
                ans.append(root.data);
                inorderTree(root.rightChild);
            }
        }

        private static class Node<T> {
            private T data;
            private Node<T> leftChild;
            private Node<T> rightChild;

            public Node(T data) {
                this.data = data;
                leftChild = null;
                rightChild = null;
            }
        }
    }
}
