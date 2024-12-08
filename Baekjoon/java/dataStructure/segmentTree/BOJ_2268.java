package dataStructure.segmentTree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2268 {

    private static int N, M;
    private static SegmentTree tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        tree = new SegmentTree();

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (cmd == 0) { // 구간의 합
                int left = Math.min(a, b);
                int right = Math.max(a, b);
                sb.append(tree.query(left, right, 1, 1, N)).append("\n");
            } else { // 업데이트
                tree.update(a, b, 1, 1, N);
            }
        }

        System.out.println(sb.toString());
    }

    private static class SegmentTree {

        int[] arr;
        long[] tree;

        public SegmentTree() {
            int k = (int) Math.ceil(Math.log(N) / Math.log(2));
            int height = k + 1;
            int size = (int) Math.pow(2, height);
            tree = new long[size];
        }

        public long update(int idx, int newValue, int node, int start, int end) {
            if (idx < start || end < idx) return tree[node];
            if (start == end) return tree[node] = newValue;
            int mid = (start + end) / 2;
            return tree[node] = update(idx, newValue, node * 2, start, mid) + update(idx, newValue, node * 2 + 1, mid + 1, end);
        }

        public long query(int left, int right, int node, int start, int end) {
            if (right < start || end < left) return 0;
            if (left <= start && end <= right) return tree[node];
            int mid = (start + end) / 2;
            return query(left, right, node * 2, start, mid) + query(left, right, node * 2 + 1, mid + 1, end);
        }
    }
}
