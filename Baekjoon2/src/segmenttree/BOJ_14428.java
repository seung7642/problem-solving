package segmenttree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14428 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static int searchLeft, searchRight;
    private static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        int[] num = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        SegmentTree tree = new SegmentTree(num);
        tree.init(1, N, 1);

        M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
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
        int[] tree;

        public SegmentTree(int[] num) {
            this.num = num;
            int height = (int) Math.ceil(Math.log(num.length) / Math.log(2));
            int size = (int) Math.pow(2, height + 1);
            tree = new int[size];
        }

        public void init(int curNodeLeft, int curNodeRight, int node) {
            if (curNodeLeft == curNodeRight) {
                tree[node] = curNodeLeft;
                return;
            }
            int mid = (curNodeLeft + curNodeRight) / 2;
            init(curNodeLeft, mid, node * 2);
            init(mid + 1, curNodeRight, node * 2 + 1);
            if (num[tree[node * 2]] <= num[tree[node * 2 + 1]]) {
                tree[node] = tree[node * 2];
            } else {
                tree[node] = tree[node * 2 + 1];
            }
        }

        public int query(int curNodeLeft, int curNodeRight, int node) {
            if (curNodeRight < searchLeft || searchRight < curNodeLeft) return -1;
            if (searchLeft <= curNodeLeft && curNodeRight <= searchRight) return tree[node];
            int mid = (curNodeLeft + curNodeRight) / 2;
            int idx1 = query(curNodeLeft, mid, node * 2);
            int idx2 = query(mid + 1, curNodeRight, node * 2 + 1);
            if (idx1 == -1) {
                return idx2;
            } else if (idx2 == -1) {
                return idx1;
            } else {
                if (num[idx1] < num[idx2]) {
                    return idx1;
                } else if (num[idx1] == num[idx2]) {
                    return Math.min(idx1, idx2);
                } else {
                    return idx2;
                }
            }
        }

        public void update(int curNodeLeft, int curNodeRight, int node, int idx, int val) {
            if (idx < curNodeLeft || curNodeRight < idx) return;
            if (curNodeLeft == curNodeRight) {
                num[idx] = val;
                return;
            }
            int mid = (curNodeLeft + curNodeRight) / 2;
            update(curNodeLeft, mid, node * 2, idx, val);
            update(mid + 1, curNodeRight, node * 2 + 1, idx, val);
            if (num[tree[node * 2]] <= num[tree[node * 2 + 1]]) {
                tree[node] = tree[node * 2];
            } else {
                tree[node] = tree[node * 2 + 1];
            }
        }
    }
}
