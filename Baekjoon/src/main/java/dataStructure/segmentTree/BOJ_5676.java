package dataStructure.segmentTree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_5676 {

    private static SegmentTree tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String input = "";
        while (!(input = br.readLine()).equals("")) {
            st = new StringTokenizer(input);
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).map(x -> Integer.compare(x, 0)).toArray();
            tree = new SegmentTree(arr);

            while (K-- > 0) {
                st = new StringTokenizer(br.readLine());
                String cmd = st.nextToken();
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (cmd.equals("C")) { // 업데이트
                    b = Integer.compare(b, 0);
                    tree.update(a, b, 1, 1, N);
                } else { // 구간의 곱
                    int result = tree.query(a, b, 1, 1, N);
                    sb.append(result > 0 ? "+" : (result == 0 ? "0" : "-"));
                }
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    private static class SegmentTree {

        int[] arr;
        int[] tree;

        public SegmentTree(int[] arr) {
            this.arr = arr;
            int n = arr.length;
            int k = (int) Math.ceil(Math.log(n) / Math.log(2));
            int size = (int) Math.pow(2, k + 1);
            tree = new int[size];
            init(1, 1, n);
        }

        public int init(int node, int start, int end) {
            if (start == end) return tree[node] = arr[start - 1];
            int mid = (start + end) / 2;
            return tree[node] = init(node * 2, start, mid) * init(node * 2 + 1, mid + 1, end);
        }

        public int update(int idx, int newValue, int node, int start, int end) {
            if (idx < start || end < idx) return tree[node];
            if (start == end) return tree[node] = newValue;
            int mid = (start + end) / 2;
            return tree[node] = update(idx, newValue, node * 2, start, mid) * update(idx, newValue, node * 2 + 1, mid + 1, end);
        }

        public int query(int left, int right, int node, int start, int end) {
            if (right < start || end < left) return 1;
            if (left <= start && end <= right) return tree[node];
            int mid = (start + end) / 2;
            return query(left, right, node * 2, start, mid) * query(left, right, node * 2 + 1, mid + 1, end);
        }
    }
}
