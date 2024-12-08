package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1953 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int T, N, M, R, C, L;
    private static int[][] board, tmpBoard;
    private static boolean[][] visited;
    private static int[][] dx = {
            {0}, // 인덱스를 1부터 시작하기 위한 padding
            {0, 0, -1, 1}, // 상 하 좌 우
            {0, 0},        // 상 하
            {1, -1},       // 좌 우
            {0, 1},        // 상 우
            {0, 1},        // 하 우
            {0, -1},       // 하 좌
            {0, -1},       // 상 좌
    };
    private static int[][] dy = {
            {0},
            {-1, 1, 0, 0},
            {1, -1},
            {0, 0},
            {-1, 0},
            {1, 0},
            {1, 0},
            {-1, 0},
    };
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(in.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            N = Integer.parseInt(st.nextToken()); // 지도 크기
            M = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken()); // 시작좌표 (R, C)
            C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken()); // 시간

            visited = new boolean[N][M];
            board = new int[N][M];
            tmpBoard = new int[N][M];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(in.readLine());
                for (int j = 0; j < M; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            bfs(C, R);
            int ans = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (tmpBoard[i][j] != 0 && tmpBoard[i][j] <= L) ans++;
                }
            }
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static void bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x, y));
        visited[y][x] = true;
        tmpBoard[y][x] = 1;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            int type = board[cur.y][cur.x];
            int len = dx[type].length;
            for (int i = 0; i < len; i++) {
                int nx = cur.x + dx[type][i];
                int ny = cur.y + dy[type][i];
                if (!valid(nx, ny) || visited[ny][nx] || board[ny][nx] == 0) continue;

                String direction = "";
                if (dx[type][i] == 0) {
                    direction = dy[type][i] == 1 ? "down" : "up";
                } else {
                    direction = dx[type][i] == 1 ? "right" : "left";
                }
                if (!possibleToGo(nx, ny, direction)) continue;

                visited[ny][nx] = true;
                tmpBoard[ny][nx] = tmpBoard[cur.y][cur.x] + 1;
                q.add(new Node(nx, ny));
            }
        }
    }

    private static boolean possibleToGo(int x, int y, String direction) {
        int pipeType = board[y][x];
        switch (direction) {
            case "up":
                return pipeType == 1 || pipeType == 2 || pipeType == 5 || pipeType == 6;
            case "down":
                return pipeType == 1 || pipeType == 2 || pipeType == 4 || pipeType == 7;
            case "left":
                return pipeType == 1 || pipeType == 3 || pipeType == 4 || pipeType == 5;
            case "right":
                return pipeType == 1 || pipeType == 3 || pipeType == 6 || pipeType == 7;
        }
        return false;
    }

    private static boolean valid(int x, int y) {
        return 0 <= x && x < M && 0 <= y && y < N;
    }

    private static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
