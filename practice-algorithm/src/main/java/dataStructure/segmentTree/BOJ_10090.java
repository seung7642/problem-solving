package dataStructure.segmentTree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_10090 {

    private static int N;
    private static long result;
    private static int[] arr, segmentTree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        segmentTree = new int[N * 4];
        for (int i = 1; i <= N; i++) {
            result += arr[i - 1] - 1 - query(1, N, 1, 1, arr[i - 1] - 1);
            update(1, N, 1, arr[i - 1]);
        }

        System.out.println(result);
    }

    // [start, end] : 노드가 담당하는 구간
    private static int update(int start, int end, int node, int pos) {
        if (pos < start || end < pos) return segmentTree[node];
        if (start == end) return segmentTree[node] += 1;
        int mid = (start + end) >> 1;
        return segmentTree[node] = update(start, mid, node * 2, pos) + update(mid + 1, end, node * 2 + 1, pos);
    }

    // [left, right] : 구하고자하는 구간
    private static int query(int start, int end, int node, int left, int right) {
        if (right < start || end < left) return 0;
        if (left <= start && end <= right) return segmentTree[node];
        int mid = (start + end) >> 1;
        return query(start, mid, node * 2, left, right) + query(mid + 1, end, node * 2 + 1, left, right);
    }
}
