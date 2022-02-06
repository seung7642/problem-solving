package segmenttree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_9426 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, K;
    private static int[] arr, tree;

    private static final int SIZE = 65536;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        tree = new int[SIZE * 4];
        for (int i = 1; i < K; i++) {
            update(0, SIZE, 1, arr[i], 1);
        }

        int prev = 1;
        int mid = (K + 1) / 2;
        long ans = 0;
        for (int i = K; i <= N; i++) {
            update(0, SIZE, 1, arr[i], 1);
            ans += find(0, SIZE, 1, mid);
            update(0, SIZE, 1, arr[prev++], -1);
        }
        System.out.println(ans);
    }

    private static int update(int curNodeLeft, int curNodeRight, int node, int idx, int diff) {
        if (idx < curNodeLeft || curNodeRight < idx) return tree[node];
        if (curNodeLeft == curNodeRight) {
            return tree[node] += diff;
        }
        int mid = (curNodeLeft + curNodeRight) / 2;
        return tree[node] = update(curNodeLeft, mid, node * 2, idx, diff) +
                update(mid + 1, curNodeRight, node * 2 + 1, idx, diff);
    }

    private static int find(int curNodeLeft, int curNodeRight, int node, int k) {
        if (curNodeLeft == curNodeRight) {
            return curNodeLeft;
        }
        int mid = (curNodeLeft + curNodeRight) / 2;
        if (tree[node * 2] >= k) {
            return find(curNodeLeft, mid, node * 2, k);
        } else {
            return find(mid + 1, curNodeRight, node * 2 + 1, k - tree[2 * node]);
        }
    }
}
