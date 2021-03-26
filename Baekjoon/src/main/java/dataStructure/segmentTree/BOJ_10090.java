package dataStructure.segmentTree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Counting Inversions
public class BOJ_10090 {

    private static int N;
    private static long result;
    private static int[] segmentTree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        segmentTree = new int[N * 4];

        // 세그먼트 트리에서 각 노드의 값: 1~X 구간의 값들 중 지금까지 나온 값들의 갯수
        // 예를 들어, [4, 2, 7, 1, 5, 6, 3] 수열에서 현재 가리키는 값이 1일 때, 1~7 구간을 담당하는 1번 노드의 값은 3이다.
        // 현재 가리키는 1 이전의 수들 중 1~7 구간에 존재하는 값이 총 3개이기 때문이다.

        // 내가 현재 보는 수가 X라면 X보다 작은 수의 갯수는 X-1개다.
        // 먼저 나온 수들 중 현재 보고 있는 X보다 작은 수의 갯수가 a개라면 X에 의해 결정되는 경우의 수는 X-1-a 이다.
        // 따라서, 세그먼트 트리를 이용하여 수열을 받을 때마다 X보다 작은 수의 갯수를 구해주어 X-1-a를 결과값에 더해준 후,
        // 세그먼트 트리에서 X의 위치에 +1을 업데이트한다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int X = Integer.parseInt(st.nextToken());
            result += X - 1 - query(1, N, 1, 1, X - 1);
            update(1, N, 1, X);
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
