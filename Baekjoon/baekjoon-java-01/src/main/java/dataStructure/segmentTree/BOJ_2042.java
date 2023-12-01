package dataStructure.segmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2042 {

    private static int N, M, K;
    private static long[] arr;
    private static SegmentTree tree;
    private static FenwickTree tree2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

//        tree = new SegmentTree(arr);
        tree2 = new FenwickTree(arr);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) { // b번 째 수를 c로 수정한다.
                long diff = c - arr[b]; // 기존의 값과 변경할 값의 차이
                arr[b] = c;
//                tree.update(1, N, 1, b, diff);
                tree2.update(b, diff);
            } else if (a == 2) { // b번째 수부터 c번째 수까지의 합을 출력한다.
//                long result = tree.query(1, N, 1, b, (int) c);
                long result = tree2.query(b, (int) c);
                sb.append(result).append("\n");
            }
        }

        System.out.println(sb);
        br.close();
    }

    private static class SegmentTree {

        long[] nums;
        long[] tree;

        public SegmentTree(long[] nums) {
            this.nums = nums;
            int k = (int) Math.ceil(Math.log(N) / Math.log(2));
            int height = k + 1;
            int size = (int) Math.pow(2, height);
            tree = new long[size];
            init(1, N, 1);
        }

        public long init(int start, int end, int node) {
            if (start == end) return tree[node] = nums[start];
            int mid = (start + end) / 2;
            return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
        }

        public void update(int start, int end, int node, int idx, long diff) {
            if (idx < start || idx > end) return;
            tree[node] += diff;
            if (start != end) {
                int mid = (start + end) / 2;
                update(start, mid, node * 2, idx, diff);
                update(mid + 1, end, node * 2 + 1, idx, diff);
            }
        }

        public long query(int start, int end, int node, int left, int right) {
            if (left > end || right < start) return 0;
            if (left <= start && end <= right) return tree[node];
            int mid = (start + end) / 2;
            return query(start, mid, node * 2, left, right) + query(mid + 1, end, node * 2 + 1, left, right);
        }
    }

    private static class FenwickTree {

        long[] tree;

        public FenwickTree(long[] nums) {
            tree = new long[N + 1];
            for (int i = 1; i <= N; i++) {
                update(i, nums[i]);
            }
        }

        public void update(int idx, long diff) {
            while (idx < tree.length) {
                tree[idx] += diff;
                idx += (idx & -idx);
            }
        }

        public long query(int left, int right) {
            return query(right) - query(left - 1);
        }

        public long query(int idx) {
            long result = 0;
            while (idx > 0) {
                result += tree[idx];
                idx &= idx - 1;
            }
            return result;
        }
    }
}
