package segmenttree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14438 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] num = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        SegmentTree tree = new SegmentTree(num);
        tree.init(1, N, 1);

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (cmd == 1) { // 수정
                tree.update(1, N, 1, a, b);
            } else if (cmd == 2) { // 조회
                searchLeft = a;
                searchRight = b;
                sb.append(tree.query(1, N, 1)).append("\n");
            }
        }

        System.out.println(sb);
    }

    private static int searchLeft, searchRight;

    private static class SegmentTree {
        int[] num;
        int[] tree;

        public SegmentTree(int[] num) {
            this.num = num;
            // 2^k >= num.length 를 만족하는 최소 k 값 찾기.
            int height = (int) Math.ceil((Math.log(num.length) / Math.log(2)));
            // 리프노드의 수가 2^height 란 의미이므로, 전체 트리의 사이즈는 2^(height+1)
            // 만약 리프노드의 수가 32개(높이가 5)라면, 루트노드(높이 0)부터 높이 4까지의 노드의 합은 31개입니다.
            int size = (int) Math.pow(2, height + 1);
            tree = new int[size];
        }

        public int init(int curNodeLeft, int curNodeRight, int node) {
            if (curNodeLeft == curNodeRight) return tree[node] = num[curNodeLeft];
            int mid = (curNodeLeft + curNodeRight) / 2;
            return tree[node] = Math.min(init(curNodeLeft, mid, node * 2),
                    init(mid + 1, curNodeRight, node * 2 + 1));
        }

        public int query(int curNodeLeft, int curNodeRight, int node) {
            if (curNodeRight < searchLeft || searchRight < curNodeLeft) return Integer.MAX_VALUE;
            if (searchLeft <= curNodeLeft && curNodeRight <= searchRight) return tree[node];
            int mid = (curNodeLeft + curNodeRight) / 2;
            return Math.min(query(curNodeLeft, mid, node * 2),
                    query(mid + 1, curNodeRight, node * 2 + 1));
        }

        public int update(int curNodeLeft, int curNodeRight, int node, int idx, int val) {
            if (idx < curNodeLeft || curNodeRight < idx) return tree[node];
            if (curNodeLeft == curNodeRight) return tree[node] = val;
            int mid = (curNodeLeft + curNodeRight) / 2;
            return tree[node] = Math.min(update(curNodeLeft, mid, node * 2, idx, val),
                    update(mid + 1, curNodeRight, node * 2 + 1, idx, val));
        }
    }
}
