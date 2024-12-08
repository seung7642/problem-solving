package dataStructure.segmentTree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ_2517 {

    private static int[] ans;
    private static List<Pair> list = new ArrayList<>();
    private static SegmentTree tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        for (int i = 1; i <= N; i++) {
            list.add(new Pair(i, Integer.parseInt(br.readLine())));
        }

        // 1. 실력을 기준으로 오름차순 정렬한다.
        Collections.sort(list, (a, b) -> {
            if (a.num > b.num) return 1; // 1을 반환하면 스왑.
            else if (a.num == b.num) return 0;
            else return -1;
        });

        ans = new int[N + 1];
        tree = new SegmentTree(N);

        // 2. 1~idx-1 구간에서 자기보다 낮은 인덱스의 갯수(구간 합)를 구한다.
        for (Pair item : list) {
            int idx = item.idx;
            ans[idx] = idx - tree.query(1, idx - 1, 1, 1, N);
            tree.update(idx, 1, 1, 1, N);
        }

        for (int i = 1; i <= N; i++) {
            sb.append(ans[i]).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static class Pair {
        int idx, num;

        public Pair(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }
    }

    private static class SegmentTree {

        int[] arr;
        int[] tree;

        public SegmentTree(int n) {
            int k = (int) Math.ceil(Math.log(n) / Math.log(2));
            int height = k + 1;
            int size = (int) Math.pow(2, height);
            tree = new int[size];
        }

        public int update(int idx, int newValue, int node, int start, int end) {
            if (idx < start || end < idx) return tree[node];
            if (start == end) return tree[node] = newValue;
            int mid = (start + end) / 2;
            return tree[node] = update(idx, newValue, node * 2, start, mid) + update(idx, newValue, node * 2 + 1, mid + 1, end);
        }

        public int query(int left, int right, int node, int start, int end) {
            if (right < start || end < left) return 0;
            if (left <= start && end <= right) return tree[node];
            int mid = (start + end) / 2;
            return tree[node] = query(left, right, node * 2, start, mid) + query(left, right, node * 2 + 1, mid + 1, end);
        }
    }
}
