package graph.search;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 숨바꼭질 2 : BFS
public class BOJ_12851 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, K;
    private static boolean[] visited = new boolean[100001];
    private static int min = Integer.MAX_VALUE;
    private static int cnt = 0;

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

        bw.write(min + "\n" + cnt + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    private static void bfs(int n, int k) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(n, 0));

        int[] arr = new int[3];
        while (!q.isEmpty()) {
            Pair now = q.poll();
            arr[0] = now.n - 1;
            arr[1] = now.n + 1;
            arr[2] = now.n * 2;
            visited[now.n] = true;

            if (now.n == k) { // 수빈이가 동생을 찾았다면 종료한다.
                min = Math.min(min, now.time);
                if (min != now.time) break;
                cnt++;
            }

            for (int i = 0; i < 3; i++) {
                if (arr[i] >= 0 && arr[i] <= 100000 && !visited[arr[i]]) {
                    q.add(new Pair(arr[i], now.time + 1));
                }
            }
        }
    }
}
