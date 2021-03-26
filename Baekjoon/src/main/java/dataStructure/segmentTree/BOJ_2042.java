package dataStructure.segmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2042 {

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

        // 2^k >= N를 만족하는 최소의 k를 찾아야 한다.
        // 양변에 log를 취하면,
        // k >= logN / log2
        // (logN / log2)의 값을 올림한 후 1을 더하면 k가 됨.
        // 위에서 구한 k를 제곱하면 세그먼트 트리의 size를 구할 수 있다.

        // int k = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
        // int size = (int) Math.pow(2, k);

        // tree = new long[size];

        // 세그먼트 트리의 사이즈를 구하는 위의 과정이 귀찮다면, N에 4를 곱해도 무방. (이건 왜지?)
        tree = new long[N * 4];

        // 루트 노드는 0번이 아니라 1번부터 시작한다.
        // 그래야 자기 자식 노드를 표현하기 편하기 때문.
        // 루트 노드가 1이면 왼쪽 자식은 1*2, 오른쪽 자식은 1*2+1
        init(1, N, 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) { // b번 째 수를 c로 수정한다.
                long diff = c - arr[b]; // 기존의 값과 변경할 값의 차이
                arr[b] = c;
                update(1, N, 1, b, diff);
            } else if (a == 2) { // b번째 수부터 c번째 수까지의 합을 출력한다.
                sb.append(sum(1, N, 1, b, (int) c)).append("\n");
            }
        }

        System.out.println(sb);
        br.close();
    }

    // node가 담당하고 있는 구간 : [start, end]
    private static long init(int start, int end, int node) {
        if (start == end) { // 시작과 끝이 같다는건 리프 노드라는 의미.
            return tree[node] = arr[start];
        }

        int mid = (start + end) / 2;

        // 재귀적으로 두 부분으로 나눈 뒤에 그 합을 자기 자신의 값으로 셋팅한다.
        return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }

    // node가 담당하고 있는 구간 : [start, end]
    // [left, right]: 합을 구해야하는 구간
    private static long sum(int start, int end, int node, int left, int right) {
        // 구하고자하는 합의 구간이 해당 노드가 가지는 합의 범위를 완전히 벗어난다면, 더이상 탐색할 필요가 없다.
        if (left > end || right < start) {
            return 0;
        }

        // 해당 노드가 가지는 합의 범위가 구하고자하는 합의 범위에 포함된다면, 더이상의 탐색이 무의미하다.
        if (left <= start && end <= right) {
            return tree[node];
        }

        // 자식 노드로 범위를 좁혀가며 탐색을 진행한다.
        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
    }

    // idx: 구간 합을 수정하고자 하는 노드
    // diff: 수정할 값
    private static void update(int start, int end, int node, int idx, long diff) {
        // 변경하고자하는 인덱스가 해당 노드의 범위에 포함되지 않는다면 더이상 탐색이 필요없다.
        if (idx < start || idx > end) {
            return;
        }

        // 범위 안에 있으면 내려가며 다른 원소도 갱신
        tree[node] += diff;
        if (start != end) {
            int mid = (start + end) / 2;
            update(start, mid, node * 2, idx, diff);
            update(mid + 1, end, node * 2 + 1, idx, diff);
        }
    }
}