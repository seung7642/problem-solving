package dataStructure.segmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 히스토그램
public class BOJ_1725 {

    private static final int INF = 2_000_000_001;

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[] arr, tree;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        for (int i = 1; i <= N; i++)
            arr[i] = Integer.parseInt(br.readLine());

        tree = new int[N * 4];
        init(1, N, 1);

        System.out.println( getMaxArea(1, N) );
        br.close();
    }

    private static int init(int start, int end, int node) {
        if (start == end) return tree[node] = start;

        int mid = (start + end) / 2;
        int leftMinIndex = init(start, mid, node * 2);
        int rightMinIndex = init(mid + 1, end, node * 2 + 1);
        return tree[node] = arr[leftMinIndex] < arr[rightMinIndex] ? leftMinIndex : rightMinIndex;
    }

    // i~j 구간에서 최소 높이를 가지는 값의 인덱스를 구한다.
    private static int query(int start, int end, int node, int i, int j) {
        if (j < start || i > end) return INF;
        if (i <= start && end <= j) return tree[node];

        int mid = (start + end) / 2;
        int leftMinIndex = query(start, mid, node * 2, i, j);
        int rightMinIndex = query(mid + 1, end, node * 2 + 1, i, j);

        if (leftMinIndex == INF) return rightMinIndex;
        else if (rightMinIndex == INF) return leftMinIndex;

        return arr[leftMinIndex] < arr[rightMinIndex] ? leftMinIndex : rightMinIndex;
    }

    private static int getMaxArea(int i, int j) {
        int minIndex = query(1, N, 1, i, j);

        int maxArea = (j - i + 1) * arr[minIndex];

        if (i < minIndex)
            maxArea = Math.max(maxArea, getMaxArea(i, minIndex - 1));

        if (minIndex < j)
            maxArea = Math.max(maxArea, getMaxArea(minIndex + 1, j));

        return maxArea;
    }
}
