package graph.search;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13549 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, K;
    private static boolean[] visited = new boolean[100001];
    private static int min = Integer.MAX_VALUE;

    static class Pair {
        int n; // 수빈이의 위치
        int time; // 현재 위치까지 오는데 걸린 시간

        public Pair(int n, int time) {
            this.n = n;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        bfs(N, K);

        bw.write(min + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    private static void bfs(int n, int k) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(N, 0));

        while (!q.isEmpty()) {
            Pair pair = q.poll();

            // 수빈이가 동생을 만나면 종료.
            if (pair.n == K) {
                min = Math.min(min, pair.time);
                break;
            }

            if (visited[pair.n]) continue;
            visited[pair.n] = true;

            if (pair.n - 1 >= 0 && !visited[pair.n - 1]) // 1) x-1로 이동한다.
                q.add(new Pair(pair.n - 1, pair.time + 1));
            if (pair.n * 2 <= 100000 && !visited[pair.n * 2]) // 2) x*2로 이동한다.
                q.add(new Pair(pair.n * 2, pair.time));
            if (pair.n + 1 <= 100000 && !visited[pair.n + 1]) // 3) x+1로 이동한다.
                q.add(new Pair(pair.n + 1, pair.time + 1));
        }
    }
}
