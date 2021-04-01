package dataStructure.mergeSortTree;

import java.io.*;
import java.util.*;

public class BOJ_13544 {

    private static int N, M;
    private static int[] nums;
    private static MergeSortTree tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        tree = new MergeSortTree(nums);

        M = Integer.parseInt(br.readLine());
        int left, right, k, ans = 0;
        while (M-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            left = Integer.parseInt(st.nextToken()) ^ ans;
            right = Integer.parseInt(st.nextToken()) ^ ans;
            k = Integer.parseInt(st.nextToken()) ^ ans;

            ans = tree.query(k, left - 1, right - 1, 1, 0, N - 1);
            sb.append(ans).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static class MergeSortTree {

        int[] arr;
        List<Integer>[] tree; // 각 노드가 리스트를 담고 있다.

        public MergeSortTree(int[] arr) {
            this.arr = arr;
            int k = (int) Math.ceil(Math.log(N) / Math.log(2));
            int height = k + 1;
            int size = (int) Math.pow(2, height);
            tree = new List[size];
            init(1, 0, N - 1);
        }

        public List<Integer> init(int node, int start, int end) {
            if (start == end) {
                tree[node] = new ArrayList<>();
                tree[node].add(arr[start]);
                return tree[node];
            }
            int mid = (start + end) / 2;
            return tree[node] = merge(init(node * 2, start, mid), init(node * 2 + 1, mid + 1, end));
        }

        public List<Integer> merge(List<Integer> left, List<Integer> right) {
            List<Integer> result = new ArrayList<>();
            int i = 0, j = 0;
            while (i < left.size() && j < right.size()) {
                if (left.get(i) <= right.get(j)) {
                    result.add(left.get(i++));
                } else {
                    result.add(right.get(j++));
                }
            }
            while (i < left.size()) {
                result.add(left.get(i++));
            }
            while (j < right.size()) {
                result.add(right.get(j++));
            }
            return result;
        }

        public int query(int k, int left, int right, int node, int start, int end) {
            if (right < start || end < left) return 0;
            if (left <= start && end <= right) return tree[node].size() - upperBound(tree[node], k);
            int mid = (start + end) / 2;
            return query(k, left, right, node * 2, start, mid) + query(k, left, right, node * 2 + 1, mid + 1, end);
        }

        // k보다 큰 원소가 나오는 첫 번째 위치를 반환한다.
        public int upperBound(List<Integer> list, int k) {
            int length = list.size();
            int left = 0, right = length - 1, mid = 0;
            while (left < right) {
                if (list.get(mid) <= k) left = mid + 1;
                else right = mid;
                mid = (left + right) / 2;
                if (mid == right) {
                    if (list.get(mid) <= k) return length;
                    else return right;
                }
            }
            if (list.get(left) > k) return 0;
            return left + 1;
        }
    }
}
