package dataStructure.segmentTree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1275 {

    private static int[] nums;
    private static SegmentTree tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        nums = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        tree = new SegmentTree(nums);

        StringBuilder sb = new StringBuilder();
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int left = Math.min(x, y);
            int right = Math.max(x, y);
            sb.append(tree.query(left, right, 1, 1, N)).append("\n");
            tree.update(a, b, 1, 1, N);
        }

        System.out.println(sb.toString());
    }

    private static class SegmentTree {

        int[] nums; // 실제 값들
        long[] tree; // 부분의 대표값을 담을 세그먼트 트리
        int height = 0, leafCount; // 높이, 리프 노드의 갯수

        public SegmentTree(int[] nums) {
            this.nums = nums;

            // 리프 노드의 갯수가 N 일때의 트리의 높이를 구해야 한다.
            // 2^k >= N 를 만족하는 최소의 k를 찾아야 한다.
            // 양변에 로그를 취하면 k >= (logN / log2)
            // logN/log2 를 구한 후 올림 해주면 k를 구할 수 있다.
            // 트리의 높이는 1부터 시작하기 때문에 위에서 구한 k의 +1을 해주면 트리의 높이가 나온다.
            // 2^height - 1이 트리의 사이즈이지만, 세그먼트 트리는 노드 번호(인덱스)를 1부터 시작한다.
            // 따라서 트리의 사이즈는 2^height 이다.

            int N = nums.length - 1;
            int k = (int) Math.ceil(Math.log(N) / Math.log(2));
            height = k + 1;
            int size = (int) Math.pow(2, height);
            tree = new long[size];
            initialize(1, 1, N);
        }

        private long initialize(int node, int start, int end) {
            if (start == end) return tree[node] = nums[start];

            int mid = (start + end) / 2;
            return tree[node] = initialize(node * 2, start, mid) + initialize(node * 2 + 1, mid + 1, end);
        }

        public long update(int idx, long val, int node, int start, int end) {
            if (idx < start || end < idx) return tree[node];

            if (start == end) return tree[node] = val;

            int mid = (start + end) / 2;
            return tree[node] = update(idx, val, node * 2, start, mid) + update(idx, val, node * 2 + 1, mid + 1, end);
        }

        // [left, right] : 구하고자하는 구간
        public long query(int left, int right, int node, int start, int end) {
            if (right < start || end < left) return 0;
            if (left <= start && end <= right) return tree[node];

            int mid = (start + end) / 2;
            return query(left, right, node * 2, start, mid) + query(left, right, node * 2 + 1, mid + 1, end);
        }
    }
}
