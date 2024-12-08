package segmenttree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2042 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M, K;
    private static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        long[] num = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            num[i] = Long.parseLong(br.readLine());
        }

        SegmentTree tree = new SegmentTree(num);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {
                long diff = c - num[b];
                num[b] = c;
                tree.update(1, N, 1, b, diff);
            } else if (a == 2) {
                sb.append(tree.query(1, N, 1, b, (int) c)).append("\n");
            }
        }

        System.out.println(sb);
        br.close();
    }

    private static class SegmentTree {
        long[] num;
        long[] tree;

        public SegmentTree(long[] num) {
            this.num = num;

            // 2^height >= num.length 을 만족하는 최소의 height를 구해야 합니다.
            int height = (int) Math.ceil(Math.log(N) / Math.log(2));

            // 리프 노드의 갯수가 2^height 라는 의미이므로, 총 트리의 사이즈는 2^(height + 1)이 됩니다.
            // 예를 들어, 리프 노드가 2^5=32개 라면 그 위의 노드의 합은 1+2+4+8+16=31개 가 되기 때문입니다.
            int size = (int) Math.pow(2, height + 1);
            tree = new long[size];
            init(1, N, 1);
        }

        private long init(int curNodeLeft, int curNodeRight, int node) {
            if (curNodeLeft == curNodeRight) {
                return tree[node] = num[curNodeLeft];
            }
            int mid = (curNodeLeft + curNodeRight) / 2;
            return tree[node] = init(curNodeLeft, mid, node * 2) +
                    init(mid + 1, curNodeRight, node * 2 + 1);
        }

        /**
         *
         * @param curNodeLeft : 지금 탐색 중인 노드에 저장된 범위의 Left
         * @param curNodeRight : 지금 탐색 중인 노드에 저장된 범위의 Right
         * @param node : 지금 탐색 중인 노드의 번호 (루트노드는 1번부터 시작)
         * @param searchLeft : 찾고자하는 구간의 Left
         * @param searchRight : 찾고자하는 구간의 Right
         * @return [searchLeft, searchRight] 구간의 합
         */
        public long query(int curNodeLeft, int curNodeRight, int node, int searchLeft, int searchRight) {
            if (searchRight < curNodeLeft || curNodeRight < searchLeft) return 0;
            if (searchLeft <= curNodeLeft && curNodeRight <= searchRight) return tree[node];
            int mid = (curNodeLeft + curNodeRight) / 2;
            return query(curNodeLeft, mid, node * 2, searchLeft, searchRight) +
                    query(mid + 1, curNodeRight, node * 2 + 1, searchLeft, searchRight);
        }

        public void update(int curNodeLeft, int curNodeRight, int node, int idx, long diff) {
            if (idx < curNodeLeft || curNodeRight < idx) return;
            tree[node] += diff;
            if (curNodeLeft != curNodeRight) {
                int mid = (curNodeLeft + curNodeRight) / 2;
                update(curNodeLeft, mid, node * 2, idx, diff);
                update(mid + 1, curNodeRight, node * 2 + 1, idx, diff);
            }
        }
    }
}
