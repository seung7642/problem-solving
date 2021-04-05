package dataStructure.segmentTree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2104 {

    private static int N;
    private static int[] arr;
    private static SegmentTree tree;
    private static long ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        tree = new SegmentTree(arr);
        searchMaxScore(1, N);

        System.out.println(ans);
    }

    private static void searchMaxScore(int i, int j) {
        int minScoreIdx = i;
        for (int k = i + 1; k <= j; k++) {
            if (arr[minScoreIdx] > arr[k]) {
                minScoreIdx = k;
            }
        }

        long result = tree.query(i, j, 1, 1, N) * arr[minScoreIdx];
        ans = Math.max(ans, result);
        if (i < minScoreIdx) { // 좌측 구간 탐색
            searchMaxScore(i, minScoreIdx - 1);
        }
        if (minScoreIdx < j) { // 우측 구간 탐색
            searchMaxScore(minScoreIdx + 1, j);
        }
    }

    private static class SegmentTree {

        int[] arr;
        long[] tree;

        public SegmentTree(int[] arr) {
            this.arr = arr;
            int k = (int) Math.ceil(Math.log(N) / Math.log(2));
            int size = (int) Math.pow(2, k + 1);
            tree = new long[size];
            init(1, 1, N);
        }

        public long init(int node, int start, int end) {
            if (start == end) return tree[node] =  arr[start];
            int mid = (start + end) / 2;
            long left = init(node * 2, start, mid);
            long right = init(node * 2 + 1, mid + 1, end);
            return tree[node] = left + right;
        }

        public long query(int left, int right, int node, int start, int end) {
            if (right < start || end < left) return 0;
            if (left <= start && end <= right) return tree[node];
            int mid = (start + end) / 2;
            return query(left, right, node * 2, start, mid) + query(left, right, node * 2 + 1, mid + 1, end);
        }
    }
}
