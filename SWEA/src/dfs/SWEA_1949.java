package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_1949 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int T, N, K, ans;
    static int[][] board;
    static boolean[][] visited;
    static List<Node> startList;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            board = new int[N][N];
            visited = new boolean[N][N];

            int max = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    max = Math.max(max, board[i][j]);
                }
            }

            findStart(max);

            ans = 0;
            for (Node start : startList) {
                visited[start.y][start.x] = true;
                dfs(start, max, 1, false);
                visited[start.y][start.x] = false;
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }

        System.out.println(sb);
    }

    // 1. 해당 지점을 이전에 이미 방문한 경우
    //   1.1 전에 방문할때, 해당지점까지 지형을 한 번 깎고 온 경우
    //     1.1.1 지금의 탐색경로가 지형을 깎지 않고 해당지점까지 갔다면 재탐색. (깎을 기회가 생기니 뒤의 결과값이 바뀐다.)
    //     1.1.2 지금의 탐색경로가 지형을 한 번 깎고 해당지점까지 갔다면 패스. (같은 조건이기 때문에 탐색해도 뒤의 결과값이 같다.)
    //   1.2 전에 방문할때, 해당지점까지 지형을 깎지않고 온 경우 - 패스 (같은 조건이기 때문에 탐색해도 뒤의 결과값이 같다.)
    private static void dfs(Node node, int height, int depth, boolean cut) {
        ans = Math.max(ans, depth);

        for (int i = 0; i < 4; i++) {
            int nx = node.x + dx[i];
            int ny = node.y + dy[i];

            if (!isValid(nx, ny) || visited[ny][nx]) continue;

            if (height > board[ny][nx]) {
                visited[ny][nx] = true;
                dfs(new Node(nx, ny), board[ny][nx], depth + 1, cut);
                visited[ny][nx] = false;
            } else {
                if (!cut && height > board[ny][nx] - K) {
                    visited[ny][nx] = true;
                    dfs(new Node(nx, ny), height - 1, depth + 1, true);
                    visited[ny][nx] = false;
                }
            }
        }
    }

    private static boolean isValid(int x, int y) { return 0 <= x && x < N && 0 <= y && y < N; }

    private static void findStart(int val) {
        startList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == val) startList.add(new Node(j, i));
            }
        }
    }

    private static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
