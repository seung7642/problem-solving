package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_6087 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M, answer;
    private static int sourceX, sourceY, targetX, targetY;
    private static char[][] board;
    private static int[][] visited;
    private static int[] dx = {0, 0, 1, -1}; // 남 북 서 동
    private static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        visited = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], N * M);
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < str.length(); j++) {
                board[i][j] = str.charAt(j);
                if (board[i][j] == 'C') {
                    if (cnt == 0) {
                        cnt++;
                        sourceX = j;
                        sourceY = i;
                    } else {
                        targetX = j;
                        targetY = i;
                    }
                }
            }
        }

        bfs();
        System.out.println(answer);
    }

    private static void bfs() {
        Queue<Node> q = new PriorityQueue<>((o1, o2) -> o1.turnaboutCnt - o2.turnaboutCnt);
        q.add(new Node(sourceX, sourceY, 0, -1));
        visited[sourceY][sourceX] = 0;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (cur.x == targetX && cur.y == targetY) {
                answer = cur.turnaboutCnt;
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (!valid(nx, ny) || board[ny][nx] == '*') continue;

                if (cur.dir == i || cur.dir == -1) {
                    if (visited[ny][nx] >= cur.turnaboutCnt) {
                        visited[ny][nx] = cur.turnaboutCnt;
                        q.add(new Node(nx, ny, cur.turnaboutCnt, i));
                    }
                } else {
                    if (visited[ny][nx] >= cur.turnaboutCnt + 1) {
                        visited[ny][nx] = cur.turnaboutCnt + 1;
                        q.add(new Node(nx, ny, cur.turnaboutCnt + 1, i));
                    }
                }
            }
        }
    }

    private static boolean valid(int x, int y) {
        return 0 <= x && x < M && 0 <= y && y < N;
    }

    private static class Node {
        int x, y, turnaboutCnt, dir;

        public Node(int x, int y, int turnaboutCnt, int dir) {
            this.x = x;
            this.y = y;
            this.turnaboutCnt = turnaboutCnt;
            this.dir = dir;
        }
    }
}
