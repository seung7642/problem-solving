package dataStructure.segmentTree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2243 {

    private static final int MAX_N = 1_000_000;
    private static int[] arr = new int[MAX_N + 1];
    private static SegmentTree tree = new SegmentTree(arr);

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken()), C;

            if (A == 1) { // 사탕상자에서 사탕을 꺼내는 경우 (B번째 맛을 찾아야한다.)
                int result = tree.query(B, 1, 1, MAX_N);
                sb.append(result).append("\n");
                arr[result]--;
                tree.update(result, -1, 1, 1, MAX_N);
            } else { // 사탕을 넣는 경우
                C = Integer.parseInt(st.nextToken());
                arr[B] += C;
                tree.update(B, C, 1, 1, MAX_N);
            }
        }

        System.out.println(sb.toString());
    }

    private static class SegmentTree {

        int[] nums;
        int[] tree;

        public SegmentTree(int[] nums) {
            this.nums = nums;
            int N = nums.length;
            int k = (int) Math.ceil(Math.log(N) / Math.log(2));
            int height = k + 1;
            int size = (int) Math.pow(2, height);
            tree = new int[size];
        }

        public void update(int idx, int diff, int node, int start, int end) {
            if (idx < start || end < idx) return;
            tree[node] += diff;
            if (start != end) {
                int mid = (start + end) / 2;
                update(idx, diff, node * 2, start, mid);
                update(idx, diff, node * 2 + 1,mid + 1, end);
            }
        }

        public int query(int seq, int node, int start, int end) {
            if (start == end) return start;

            int mid = (start + end) / 2;
            if (tree[node * 2] >= seq) { // 왼쪽 자식의 값이 seq보다 크다면 왼쪽에 사탕이 있으므로 왼쪽으로 이동.
                return query(seq, node * 2, start, mid);
            } else { // 그렇지 않다면 오른쪽에 사탕이 있으므로 tree[node*2]만큼 빼준 후 오른쪽으로 이동.
                return query(seq - tree[node * 2], node * 2 + 1, mid + 1, end);
            }
        }
    }
}
