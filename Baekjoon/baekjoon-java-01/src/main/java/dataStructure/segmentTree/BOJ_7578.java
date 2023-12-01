package dataStructure.segmentTree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_7578 {

    private static int[] nums;
    private static SegmentTree tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        nums = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Map<Integer, Integer> map = new HashMap<>(); // [식별 번호, 인덱스]
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int val = Integer.parseInt(st.nextToken());
            map.put(val, i);
        }

        tree = new SegmentTree(nums);
        long ans = 0;
        for (int i = 1; i <= N; i++) {
            int a = nums[i];
            int idx = map.get(a); // a와 같은 식별 번호의 인덱스를 가져온다.

            // idx보다 큰 인덱스 중에 방문한 적이 있는 식별번호가 있다면 그 수만큼 더해준다.
            ans += tree.query(idx + 1, N, 1, 1, N);

            // 해당 인덱스를 방문했다는 의미로 +1 업데이트 해준다.
            tree.update(idx, 1, 1, 1, N);
        }

        System.out.println(ans);
    }

    private static class SegmentTree {

        int[] nums;
        int[] tree;

        public SegmentTree(int[] nums) {
            this.nums = nums;
            int N = nums.length - 1;
            int k = (int) Math.ceil(Math.log(N) / Math.log(2));
            int height = k + 1;
            int size = (int) Math.pow(2, height);
            tree = new int[size];
        }

        public long query(int left, int right, int node, int start, int end) {
            if (right < start || end < left) return 0;
            if (left <= start && end <= right) return tree[node];
            int mid = (start + end) / 2;
            return query(left, right, node * 2, start, mid) + query(left, right, node * 2 + 1, mid + 1, end);
        }

        public void update(int idx, int diff, int node, int start, int end) {
            if (idx < start || end < idx) return;
            tree[node] += diff;
            if (start != end) {
                int mid = (start + end) / 2;
                update(idx, diff, node * 2, start, mid);
                update(idx, diff, node * 2 + 1, mid + 1, end);
            }
        }
    }
}
