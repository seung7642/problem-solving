package graph.topologicalSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1766 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M, idx;
    private static List<Integer>[] list;
    private static int[] inDegree, result;
    private static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new List[N + 1];
        for (int i = 0; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        inDegree = new int[N + 1];
        result = new int[N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            list[v1].add(v2);
            inDegree[v2]++;
        }

        topologicalSort();
        for (int item : result) {
            sb.append(item).append(" ");
        }
        System.out.println(sb);
    }

    private static void topologicalSort() {
        Queue<Integer> q = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int item : list[cur]) {
                inDegree[item]--;
                if (inDegree[item] == 0) {
                    q.add(item);
                }
            }

            result[idx++] = cur;
        }
    }
}
