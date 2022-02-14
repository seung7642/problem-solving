package bfs;

import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2638 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M, cheeseCnt, answer;
    private static int[][] board;
    private static int[][] visited;
    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {-1, 1, 0, 0};
    private static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) {
                    cheeseCnt++;
                }
            }
        }

        while (cheeseCnt != 0) {
            visited = new int[N][M];
            bfs();
            answer++;
        }

        System.out.println(answer);
    }

    private static void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0));
        visited[0][0] = 1;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (!valid(nx, ny) || visited[ny][nx] == 1 || visited[ny][nx] == 3) continue;

                if (board[ny][nx] == 1) { // 치즈를 만났을 경우
                    if (visited[ny][nx] == 2) {
                        visited[ny][nx] = 3;
                        board[ny][nx] = 0;
                        cheeseCnt--;
                    } else {
                        visited[ny][nx] = 2;
                    }
                } else {
                    visited[ny][nx] = 1;
                    q.add(new Node(nx, ny));
                }
            }
        }
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
