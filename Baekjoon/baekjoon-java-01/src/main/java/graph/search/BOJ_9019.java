package graph.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_9019 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int T, A, B;
    private static boolean[] visited;
    private static String[] cmd;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            visited = new boolean[10000];
            cmd = new String[10000];
            Arrays.fill(cmd, "");

            bfs(A);
            System.out.println(cmd[B]);
        }

        br.close();
    }

    private static int bfs(int startNum) {
        Queue<Integer> q = new LinkedList<>();
        q.add(startNum);
        visited[startNum] = true;

        while (!q.isEmpty()) {
            int now = q.poll();
            if (now == B) return now;

            int D = (now * 2) % 10000;
            int S = (now == 0) ? 9999 : now - 1;
            int L = (now % 1000) * 10 + now/1000;
            int R = (now % 10) * 1000 + now/10;

            if (!visited[D]) {
                q.add(D);
                visited[D] = true;
                cmd[D] = cmd[now] + "D";
            }
            if (!visited[S]) {
                q.add(S);
                visited[S] = true;
                cmd[S] = cmd[now] + "S";
            }
            if (!visited[L]) {
                q.add(L);
                visited[L] = true;
                cmd[L] = cmd[now] + "L";
            }
            if (!visited[R]) {
                q.add(R);
                visited[R] = true;
                cmd[R] = cmd[now] + "R";
            }
        }

        return -1;
    }
}
