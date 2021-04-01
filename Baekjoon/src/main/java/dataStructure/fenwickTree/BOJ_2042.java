package dataStructure.fenwickTree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2042 {

    private static int N, M, K;
    private static long[] nums;
    private static FenwickTree tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        nums = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            nums[i] = Long.parseLong(br.readLine());
        }

        tree = new FenwickTree(nums);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {
                long diff = c - nums[b];
                nums[b] = c;
                tree.update(b, diff);
            } else {
                long result = tree.query(b, (int) c);
                sb.append(result).append("\n");
            }
        }

        System.out.println(sb.toString());
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
                idx += (idx & -idx); // 마지막 비트 1의 위치를 알아낸다.
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
