package dataStructure.segmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10999 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M, K;
    private static long[] arr, tree, lazy;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        tree = new long[N * 4];
        lazy = new long[N * 4];
        arr = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        init(1, N, 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == 1) { // b번째 수부터 c번째 수에 d를 더한다.
                long d = Long.parseLong(st.nextToken());
                updateRange(1, N, 1, b, c, d);
            } else if (a == 2) { // b부터 c까지의 합
                sb.append(sum(1, N, 1, b, (int) c)).append("\n");
            }
        }

        System.out.println(sb);
        br.close();
    }

    private static long init(int start, int end, int node) {
        if (start == end)
            return tree[node] = arr[start];

        int mid = (start + end) / 2;
        return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }

    // i번째 값부터 j번째 갑 까지의 합을 구하는 메서드
    private static long sum(int start, int end, int node, int i, int j) {
        if (lazy[node] != 0) {
            tree[node] += (end - start + 1) * lazy[node];
            if (start != end) {
                lazy[node * 2] += lazy[node];
                lazy[node * 2 + 1] += lazy[node];
            }
            lazy[node] = 0;
        }

        if (i > end || j < start) return 0;
        if (i <= start && end <= j) return tree[node];

        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, i, j) + sum(mid + 1, end, node * 2 + 1, i, j);
    }

    // i~j 구간에 diff만큼 더해줄 때 SegmentTree를 업데이트하는 메서드
    private static void updateRange(int start, int end, int node, int i, int j, long diff) {
        // lazy가 남아있을 때
        if (lazy[node] != 0) {
            tree[node] += (end - start + 1) * lazy[node];
            if (start != end) {
                lazy[node * 2] += lazy[node];
                lazy[node * 2 + 1] += lazy[node];
            }
            lazy[node] = 0;
        }

        if (j < start || i > end) return;

        // 대표 구간을 찾았을 때
        if (i <= start && end <= j) {
            tree[node] += (end - start + 1) * diff;
            if (start != end) {
                lazy[node * 2] += diff;
                lazy[node * 2 + 1] += diff;
            }
            return;
        }
        updateRange(start, (start + end) / 2, node * 2, i, j, diff);
        updateRange((start + end) / 2 + 1, end, node * 2 + 1, i, j, diff);

        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }
}
