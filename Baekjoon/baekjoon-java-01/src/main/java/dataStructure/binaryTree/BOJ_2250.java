package dataStructure.binaryTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2250 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, point = 1;
    private static List<Node> tree = new ArrayList<>();
    private static int[] levelMin, levelMax;
    private static int maxLevel = 0;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        levelMax = new int[N + 1];
        levelMin = new int[N + 1];
        int rootIdx = 0;

        for (int i = 0; i <= N; i++) {
            tree.add(new Node(i, -1, -1));
            levelMin[i] = N;
            levelMax[i] = 0;
        }

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            tree.get(idx).left = left;
            tree.get(idx).right = right;
            if (left != -1) tree.get(left).parentIdx = idx;
            if (right != -1) tree.get(right).parentIdx = idx;
        }

        for (int i = 1; i <= N; i++) {
            if (tree.get(i).parentIdx == -1) {
                rootIdx = i;
                break;
            }
        }

        inOrder(rootIdx, 1);

        // 완성된 levelMax[]와 levelMin[]을 가지고 값을 뽑아낸다.
        int ansLevel = 0, ansWidth = 0;
        for (int i = 1; i <= maxLevel; i++) {
            int width = levelMax[i] - levelMin[i] + 1;
            if (ansWidth < width) {
                ansWidth = width;
                ansLevel = i;
            }
        }

        System.out.println(ansLevel + " " + ansWidth);
    }

    private static void inOrder(int idx, int level) {
        Node node = tree.get(idx);
        maxLevel = Math.max(maxLevel, level);
        if (node.left != -1) {
            inOrder(node.left, level + 1);
        }

        // 현재 노드가 가장 왼쪽 노드라면 갱신한다.
        levelMin[level] = Math.min(levelMin[level], point);

        // 현재 노드는 이전 노드보다 항상 x좌표가 더 높기 때문에 갱신한다.
        levelMax[level] = point++;

        if (node.right != -1) {
            inOrder(node.right, level + 1);
        }
    }

    private static class Node {
        int parentIdx, idx;
        int left, right;

        public Node(int idx, int left, int right) {
            this.parentIdx = -1;
            this.idx = idx;
            this.left = left;
            this.right = right;
        }
    }
}
