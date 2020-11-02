package dataStructure.segmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10868 {

    private static final int INF = 2_000_000_000;

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static int[] arr, tree;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];
        for (int i = 1; i <= N; i++)
            arr[i] = Integer.parseInt(br.readLine());

        tree = new int[N * 4];
        init(1, N, 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(getMinValue(1, N, 1, a, b)).append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    private static int init(int start, int end, int node) {
        if (start == end) return tree[node] = arr[start];

        int mid = (start + end) / 2;
        return tree[node] = Math.min(init(start, mid, node * 2), init(mid + 1, end, node * 2 + 1));
    }

    private static int getMinValue(int start, int end, int node, int i, int j) {
        if (j < start || i > end) return INF;
        if (i <= start && end <= j) return tree[node];

        int mid = (start + end) / 2;
        return Math.min(getMinValue(start, mid, node * 2, i, j), getMinValue(mid + 1, end, node * 2 + 1, i, j));
    }
}
