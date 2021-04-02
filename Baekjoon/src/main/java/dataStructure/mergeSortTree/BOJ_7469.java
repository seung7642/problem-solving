package dataStructure.mergeSortTree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// 특정 구간의 정렬된 수열에서 k번째 수 찾기.
public class BOJ_7469 {

    private static int N, M;
    private static int[] nums;
    private static MergeSortTree tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        tree = new MergeSortTree(nums);

        int i, j, k;
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            i = Integer.parseInt(st.nextToken());
            j = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            // k번째 수가 X라면 i~j 구간에서 X보다 작은 수의 갯수는 k-1개다.
            // 가능한 X의 범위는 [-10^9, 10^9] 이다. X를 이분탐색으로 찾아나간다.
            int left = (int) -1e9, right = (int) 1e9;
            while (left <= right) {
                int mid = (left + right) / 2;
                int result = tree.query(mid, i, j, 1, 1, N);
                if (result < k) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            sb.append(left).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static class MergeSortTree {

        int[] arr;
        List<Integer>[] tree;

        public MergeSortTree(int[] arr) {
            this.arr = arr;
            int k = (int) Math.ceil(Math.log(N) / Math.log(2));
            int height = k + 1;
            int size = (int) Math.pow(2, height);
            tree = new List[size];
            init(1, 1, N);
        }

        public List<Integer> init(int node, int start, int end) {
            if (start == end) {
                tree[node] = new ArrayList<>();
                tree[node].add(arr[start - 1]);
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

        public int query(int x, int left, int right, int node, int start, int end) {
            if (right < start || end < left) return 0;
            if (left <= start && end <= right) return upperBound(tree[node], x);
            int mid = (start + end) / 2;
            return query(x, left, right, node * 2, start, mid) + query(x, left, right, node * 2 + 1, mid + 1, end);
        }

        // x보다 큰 원소가 나오는 첫 번째 위치를 반환한다.
        public int upperBound(List<Integer> node, int x) {
            int length = node.size();
            int left = 0, right = length - 1, mid = 0;
            while (left < right) {
                if (node.get(mid) <= x) left = mid + 1;
                else right = mid;
                mid = (left + right) / 2;
                if (mid == right) {
                    if (node.get(mid) <= x) return length;
                    else return right;
                }
            }
            if (node.get(left) > x) return 0;
            return left + 1;
        }
    }
}
