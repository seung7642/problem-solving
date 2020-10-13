package graph.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2251 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] water = new int[3];
    private static boolean[][] visited = new boolean[201][201];
    private static boolean[] ans = new boolean[201];
    private static int[] from = {0, 0, 1, 1, 2, 2};
    private static int[] to   = {1, 2, 0, 2, 0, 1};

    private static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 3; i++)
            water[i] = Integer.parseInt(st.nextToken());

        bfs();

        for (int i = 0; i <= water[2]; i++) {
            if (ans[i])
                System.out.print(i + " ");
        }

        br.close();
    }

    private static void bfs() {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0, 0));
        visited[0][0] = true;
        ans[water[2]] = true;

        while (!q.isEmpty()) {
            Pair p = q.poll();
            int x = p.x;
            int y = p.y;
            int z = water[2] - (x + y);

            for (int k = 0; k < 6; k++) {
                int[] next = {x, y, z};
                next[to[k]] += next[from[k]]; // 목적지 물통에 담긴 물의 양 += 출발지 물통에 담긴 물의 양
                next[from[k]] = 0;

                // 목적지 물통의 용량과 물의 양을 비교한다. (물이 넘쳤는지 확인)
                if (next[to[k]] > water[to[k]]) {
                    next[from[k]] = next[to[k]] - water[to[k]];
                    next[to[k]] = water[to[k]];
                }

                if (!visited[next[0]][next[1]]) {
                    visited[next[0]][next[1]] = true;
                    q.add(new Pair(next[0], next[1]));
                    if (next[0] == 0) {
                        ans[next[2]] = true;
                    }
                }
            }
        }
    }
}
