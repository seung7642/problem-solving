package prefixSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11659_0 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M, i, j;
    private static int[] arr, tree;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        tree = new int[N * 4];
        st = new StringTokenizer(in.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        init(1, N, 1);
        while (M-- > 0) {
            st = new StringTokenizer(in.readLine());
            i = Integer.parseInt(st.nextToken());
            j = Integer.parseInt(st.nextToken());
            int result = sum(1, N, 1, i, j);
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }

    private static int init(int start, int end, int nodeIdx) {
        if (start == end) {
            return tree[nodeIdx] = arr[start];
        }
        int mid = (start + end) / 2;
        return tree[nodeIdx] = init(start, mid, nodeIdx * 2) + init(mid + 1, end, nodeIdx * 2 + 1);
    }

    private static int sum(int start, int end, int nodeIdx, int left, int right) {
        if (right < start || end < left) {
            return 0;
        } else if (left <= start && end <= right) {
            return tree[nodeIdx];
        }
        int mid = (start + end) / 2;
        return sum(start, mid, nodeIdx * 2, left, right) + sum(mid + 1, end, nodeIdx * 2 + 1, left, right);
    }
}
