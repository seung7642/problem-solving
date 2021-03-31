package dataStructure.segmentTree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1395 {

    private static SegmentTree tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        tree = new SegmentTree(N);

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            if (cmd == 0) { // left~right 범위 업데이트
                tree.updateRange(left, right, 1, 1, N);
            } else { // left~right 구간의 합
                int result = tree.query(left, right, 1, 1, N);
                sb.append(result).append("\n");
            }
        }

        System.out.println(sb.toString());
    }

    private static class SegmentTree {

        int[] nums;
        int[] tree;
        boolean[] lazy; // 게으른 전파(lazy propagation)를 위한 변수

        public SegmentTree(int n) {
            int k = (int) Math.ceil(Math.log(n) / Math.log(2));
            int height = k + 1;
            int size = (int) Math.pow(2, height);
            tree = new int[size];
            lazy = new boolean[size];
        }

        public void updateRange(int left, int right, int node, int start, int end) {
            updateLazy(node, start, end);
            if (right < start || end < left) return;

            if (left <= start && end <= right) {
                tree[node] = (end - start + 1) - tree[node]; // 구간의 갯수 - 대표값(현재 켜진 스위치의 수)로 현재 꺼져있는 스위치의 수로 업데이트한다.
                if (start != end) { // 리프 노드가 아니라면, 게으른 전파 목록에 추가한다.
                    lazy[node * 2] = !lazy[node * 2];
                    lazy[node * 2 + 1] = !lazy[node * 2 + 1];
                }
                return;
            }

            int mid = (start + end) / 2;
            updateRange(left, right, node * 2, start, mid);
            updateRange(left, right, node * 2 + 1, mid + 1, end);
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }

        public void updateLazy(int node, int start, int end) {
            if (lazy[node]) {
                tree[node] = (end - start + 1) - tree[node];
                if (start != end) {
                    lazy[node * 2] = !lazy[node * 2];
                    lazy[node * 2 + 1] = !lazy[node * 2 + 1];
                }
                lazy[node] = false;
            }
        }

        public int query(int left, int right, int node, int start, int end) {
            updateLazy(node, start, end);
            if (right < start || end < left) return 0;
            if (left <= start && end <= right) return tree[node];
            int mid = (start + end) / 2;
            return query(left, right, node * 2, start, mid) + query(left, right, node * 2 + 1, mid + 1, end);
        }
    }
}
