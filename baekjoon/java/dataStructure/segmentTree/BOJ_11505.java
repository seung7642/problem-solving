package dataStructure.segmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11505 {

    private static final int MOD = 1_000_000_007;

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M, K;
    private static long[] arr, tree;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new long[N + 1];
        for (int i = 1; i <= N; i++)
            arr[i] = Long.parseLong(br.readLine());

        tree = new long[N * 4];
        init(1, N, 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == 1) { // b번째 수를 c로 바꾼다.
                arr[b] = c;
                update(1, N, 1, b, c);
            } else if (a == 2) { // b~c 구간의 곱을 구한다.
                sb.append(getMultiplication(1, N, 1, b, (int) c)).append("\n");
            }
        }

        System.out.println(sb);
        br.close();
    }

    private static long init(int start, int end, int node) {
        if (start == end) return tree[node] = arr[start];

        int mid = (start + end) / 2;
        return tree[node] = (init(start, mid, node * 2) * init(mid + 1, end, node * 2 + 1)) % MOD;
    }

    // i~j 구간의 곱을 구한다.
    private static long getMultiplication(int start, int end, int node, int i, int j) {
        if (j < start || i > end) return 1;

        if (i <= start && end <= j) return tree[node];

        int mid = (start + end) / 2;
        return (getMultiplication(start, mid, node * 2, i, j) * getMultiplication(mid + 1, end, node * 2 + 1, i, j)) % MOD;
    }

    // idx번째 값을 diff만큼 더한다.
    private static void update(int start, int end, int node, int idx, int newValue) {
        if (idx < start || idx > end) return;

        if (start == end) {
            tree[node] = newValue;
        } else {
            int mid = (start + end) / 2;
            update(start, mid, node * 2, idx, newValue);
            update(mid + 1, end, node * 2 + 1, idx, newValue);
            tree[node] = (tree[node * 2] * tree[node * 2 + 1]) % MOD;
        }
    }
}
