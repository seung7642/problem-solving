package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1233 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int T, N;
    private static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        while (T++ < 10) {
            ans.append("#").append(T).append(" ");
            N = Integer.parseInt(br.readLine());
            BinaryTree tree = new BinaryTree();
            StringTokenizer st;
            boolean flag = false;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int idx = Integer.parseInt(st.nextToken());
                String data = st.nextToken();
                tree.insert(idx, data);

                int tokenCnt = st.countTokens();
                if (tokenCnt > 0) {
                    if (tokenCnt != 2 || !isOperator(data.charAt(0))) {
                        flag = true;
                        for (int j = i + 1; j < N; j++) {
                            br.readLine();
                        }
                        break;
                    }
                    int leftChildIdx = Integer.parseInt(st.nextToken());
                    int rightChildIdx = Integer.parseInt(st.nextToken());
                    tree.insert(idx, leftChildIdx, rightChildIdx);
                }
            }

            ans.append(flag ? 0 : 1).append("\n");
        }

        System.out.println(ans);
    }

    private static boolean isOperator(char c) {
        return (c == '+' || c == '-' || c == '*' || c == '/');
    }

    private static class BinaryTree {

        Node root;

        public void insert(int idx, String data) {
            if (root == null) {
                root = new Node(idx, data);
                return;
            }

            Node node = find(root, idx);
            node.data = data;
        }

        public void insert(int parentIdx, int leftChildIdx, int rightChildIdx) {
            Node node = find(root, parentIdx);
            node.leftChild = new Node(leftChildIdx, "");
            node.rightChild = new Node(rightChildIdx, "");
        }

        private static Node find(Node head, int idx) {
            if (head.idx == idx) return head;
            Node node = null;
            if (head.leftChild != null) {
                 node = find(head.leftChild, idx);
                 if (node != null) return node;
            }
            if (head.rightChild != null) {
                node = find(head.rightChild, idx);
                if (node != null) return node;
            }
            return node;
        }

        private static class Node {
            int idx;
            String data;
            Node leftChild, rightChild;

            public Node(int idx, String data) {
                this.idx = idx;
                this.data = data;
                leftChild = null;
                rightChild = null;
            }
        }
    }
}
