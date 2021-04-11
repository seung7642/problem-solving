package graph.topologicalSort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1005 {

    private static int T, N, K, W;
    private static int[] inDegree, cost, result;
    private static List<Integer>[] list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 건물의 수
            K = Integer.parseInt(st.nextToken()); // 건물간의 건설 순서 규칙의 수

            init();
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                cost[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                list[from].add(to);
                inDegree[to]++;
            }

            W = Integer.parseInt(br.readLine());
            topologicalSort();
            System.out.println(result[W]);
        }
    }

    private static void init() {
        inDegree = new int[N + 1];
        cost = new int[N + 1];
        result = new int[N + 1];
        list = new List[N + 1];
        for (int i = 0; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
    }

    private static void topologicalSort() {
        Queue<Integer> q = new LinkedList<>();

        // 1. 진입 차수가 0인 노드를 큐에 추가한다.
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                q.add(i);
                result[i] = cost[i];
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int next : list[cur]) {
                if (--inDegree[next] == 0) {
                    q.add(next);
                }

                result[next] = Math.max(result[next], result[cur] + cost[next]);
            }
        }
    }
}
