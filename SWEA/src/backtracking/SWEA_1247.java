package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1247 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int T, N, ans;
    private static int[] arr;
    private static boolean[] visited;
    private static Node[] pos, clientPos;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(in.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(in.readLine());
            arr = new int[N + 1];
            visited = new boolean[N + 1];
            pos = new Node[2];
            clientPos = new Node[N + 1];
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < 2; i++) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                pos[i] = new Node(x, y);
            }
            for (int i = 1; i <= N; i++) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                clientPos[i] = new Node(x, y);
            }

            ans = Integer.MAX_VALUE;
            permutation(1);
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static void permutation(int depth) {
        if (depth > N) { // base case
            getDist();
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (visited[i]) continue;
            arr[depth] = i;
            visited[i] = true;
            permutation(depth + 1);
            arr[depth] = 0;
            visited[i] = false;
        }
    }

    private static void getDist() {
        int dist = 0;
        int prevX = pos[0].x;
        int prevY = pos[0].y;
        for (int i = 1; i <= N; i++) {
            int nx = clientPos[arr[i]].x;
            int ny = clientPos[arr[i]].y;
            dist += Math.abs(prevX - nx) + Math.abs(prevY - ny);
            prevX = nx;
            prevY = ny;
        }
        dist += Math.abs(prevX - pos[1].x) + Math.abs(prevY - pos[1].y);

        ans = Math.min(ans, dist);
    }

    private static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
