package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA_1226 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int T, N = 16;
    private static int[][] board;
    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {-1, 1, 0, 0};
    private static StringBuilder sb = new StringBuilder();
    private static boolean isPossible = false;

    public static void main(String[] args) throws IOException {
        while (T++ < 10) {
            board = new int[N][N];

            br.readLine();
            int startX = 0, startY = 0;
            for (int y = 0; y < N; y++) {
                String str = br.readLine();
                for (int x = 0; x < N; x++) {
                    int num = Character.digit(str.charAt(x), 10);
                    if (num == 2) {
                        startX = x;
                        startY = y;
                    }
                    board[y][x] = num;
                }
            }

            isPossible = false;
            bfs(startX, startY);
            sb.append("#").append(T).append(" ").append(isPossible ? 1 : 0).append("\n");
        }
        System.out.println(sb);
    }

    private static void bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x, y));
        board[y][x] = 1;

        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (board[cur.y][cur.x] == 3) {
                isPossible = true;
                break;
            }
            board[cur.y][cur.x] = 1;

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (!isValid(nx, ny) || board[ny][nx] == 1) continue;
                q.add(new Node(nx, ny));
            }
        }
    }

    private static boolean isValid(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    private static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
