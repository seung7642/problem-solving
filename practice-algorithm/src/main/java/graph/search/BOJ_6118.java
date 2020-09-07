package graph.search;

import java.io.*;
import java.util.*;

public class BOJ_6118 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, M;
    private static List<List<Integer>> list;
    private static boolean[] visited;
    private static int number = 2;
    private static int maxDistance = -1;
    private static int count = 1;

    static class Pair {
        int v; // 헛간 (=정점)
        int distance; // 해당 헛간까지 오는데까지의 거리

        public Pair(int v, int distance) {
            this.v = v;
            this.distance = distance;
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 헛간의 수 (=정점의 수)
        M = Integer.parseInt(st.nextToken()); // 양방향 길의 수 (=간선의 수)

        visited = new boolean[N + 1];
        list = new ArrayList<>();
        for (int i = 0; i < N + 1; i++)
            list.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            list.get(v1).add(v2);
            list.get(v2).add(v1);
        }

        bfs(1);

        bw.write(number + " " + maxDistance + " " + count + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    private static void bfs(int start) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(start, 0));
        visited[start] = true;

        while (!q.isEmpty()) {
            Pair now = q.poll();

            if (maxDistance < now.distance) {
                maxDistance = now.distance;
                number = now.v;
                count = 1;
            } else if (maxDistance == now.distance) { // 최대거리가 같다면 카운터 +1
                count++;
                if (number > now.v) number = now.v;
            }

            for (Integer nextVertex : list.get(now.v)) {
                if (visited[nextVertex]) continue;

                q.add(new Pair(nextVertex, now.distance + 1));
                visited[nextVertex] = true;
            }
        }
    }
}
