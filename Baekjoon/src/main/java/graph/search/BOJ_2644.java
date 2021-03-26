package graph.search;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2644 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, M, a, b, ans = -1;
    private static List<List<Integer>> list;
    private static int[][] relation;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        StringTokenizer st;
        N = Integer.parseInt(br.readLine()); // 사람의 수

        relation = new int[N + 1][N + 1];
        visited = new boolean[N + 1];
        list = new ArrayList<>();
        for (int i = 0; i < N + 1; i++)
            list.add(new ArrayList<>());

        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(br.readLine()); // 관계의 수
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            list.get(v1).add(v2);
            list.get(v2).add(v1);
        }

        dfs(a, 0);
        bw.write(ans + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int start, int distance) {
        if (start == b) {
            ans = distance;
            return;
        }
        visited[start] = true;
        for (Integer next : list.get(start)) {
            if (!visited[next]) {
                visited[next] = true;
                dfs(next, distance + 1);
            }
        }
    }
}
