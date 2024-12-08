package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA_1267 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int T;
    private static List<Integer>[] list;
    private static int[] indegree;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        while (T++ < 10) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            list = new List[V + 1];
            for (int i = 0; i <= V; i++) {
                list[i] = new ArrayList<>();
            }

            indegree = new int[V + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < E; i++) {
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());
                list[v1].add(v2); // v1 -> v2로 가는 간선
                indegree[v2]++;
            }

            Queue<Integer> q = new LinkedList<>();
            for (int i = 1; i <= V; i++) {
                if (indegree[i] == 0) q.add(i);
            }

            sb.append("#").append(T).append(" ");
            bfs(q);
            sb.append("\n");
        }
        System.out.println(sb);
    }
    
    private static void bfs(Queue<Integer> q) {
        while (!q.isEmpty()) {
            int node = q.poll();
            sb.append(node).append(" ");
            for (int item : list[node]) {
                indegree[item]--;
                if (indegree[item] == 0) {
                    q.add(item);
                }
            }
        }
    }
}
