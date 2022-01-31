package segmenttree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12837 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, Q;
    private static int searchLeft, searchRight;
    private static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        SegmentTree tree = new SegmentTree(new int[N + 1]);
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (cmd == 1) { // 업데이트
                tree.update(1, N, 1, a, b);
            } else if (cmd == 2) { // 조회
                searchLeft = a;
                searchRight = b;
                sb.append(tree.query(1, N, 1)).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static class SegmentTree {
        int[] num;
        long[] tree;

        public SegmentTree(int[] num) {
            this.num = num;
            int height = (int) Math.ceil(Math.log(num.length) / Math.log(2));
            int size = (int) Math.pow(2, height + 1);
            tree = new long[size];
        }

        public long query(int curNodeLeft, int curNodeRight, int node) {
            if (curNodeRight < searchLeft || searchRight < curNodeLeft) return 0;
            if (searchLeft <= curNodeLeft && curNodeRight <= searchRight) return tree[node];
            int mid = (curNodeLeft + curNodeRight) / 2;
            return query(curNodeLeft, mid, node * 2) + query(mid + 1, curNodeRight, node * 2 + 1);
        }

        public void update(int curNodeLeft, int curNodeRight, int node, int idx, int val) {
            if (idx < curNodeLeft || curNodeRight < idx) return;
            if (curNodeLeft == curNodeRight) {
                tree[node] += val;
                return;
            }
            int mid = (curNodeLeft + curNodeRight) / 2;
            update(curNodeLeft, mid, node * 2, idx, val);
            update(mid + 1, curNodeRight, node * 2 + 1, idx, val);
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }
    }
}
