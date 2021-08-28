package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_17144 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int R, C, T, total;
    private static int[][] board;
    private static Queue<Node> fineDusts;
    private static int[] dx = {0, 0, -1, 1}; // 북 남 서 동
    private static int[] dy = {-1, 1, 0, 0};
    private static Node airCleaner1, airCleaner2;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(in.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        board = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < C; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == -1) {
                    if (airCleaner1 == null) {
                        airCleaner1 = new Node(j, i, 0);
                    } else {
                        airCleaner2 = new Node(j, i, 0);
                    }
                }
            }
        }

        while (T-- > 0) {
            fineDusts = new LinkedList<>();
            diffuse(); // 1. 미세먼지가 확산한다.
            airClean(); // 2. 공기청정기가 작동한다.
        }

        total = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] > 0) {
                    total += board[i][j];
                }
            }
        }

        System.out.println(total);
    }

    private static void diffuse() {
        findFineDust();
        bfs();
    }

    private static void bfs() {
        while (!fineDusts.isEmpty()) {
            Node fineDust = fineDusts.poll();

            int cnt = 0;
            int amount = fineDust.amount / 5;
            for (int i = 0; i < 4; i++) {
                int nx = fineDust.x + dx[i];
                int ny = fineDust.y + dy[i];
                if (!valid(nx, ny) || board[ny][nx] == -1) continue;
                board[ny][nx] += amount;
                cnt++;
            }

            board[fineDust.y][fineDust.x] -= amount * cnt;
        }
    }

    private static boolean valid(int x, int y) { return 0 <= x && x < C && 0 <= y && y < R; }

    private static void findFineDust() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] > 0) {
                    fineDusts.add(new Node(j, i, board[i][j]));
                }
            }
        }
    }

    private static void airClean() {
        int[] arr = {0, 3, 1, 2}; // 북 동 남 서
        int direction = 0;
        Node cur = new Node(airCleaner1.x, airCleaner1.y - 1);
        Node next = new Node(airCleaner1.x, airCleaner1.y - 2);
        while (true) {
            if (board[next.y][next.x] == -1) { // base case
                board[cur.y][cur.x] = 0;
                break;
            }
            board[cur.y][cur.x] = board[next.y][next.x];
            cur = next;
            int nx = next.x + dx[arr[direction]];
            int ny = next.y + dy[arr[direction]];
            if (!valid(nx, ny) || ny > airCleaner1.y) {
                direction++;
                nx = next.x + dx[arr[direction]];
                ny = next.y + dy[arr[direction]];
            }
            next = new Node(nx, ny);
        }

        arr = new int[]{1, 3, 0, 2}; // 남 동 북 서
        direction = 0;
        cur = new Node(airCleaner2.x, airCleaner2.y + 1);
        next = new Node(airCleaner2.x, airCleaner2.y + 2);
        while (true) {
            if (board[next.y][next.x] == -1) {
                board[cur.y][cur.x] = 0;
                break;
            }
            board[cur.y][cur.x] = board[next.y][next.x];
            cur = next;
            int nx = next.x + dx[arr[direction]];
            int ny = next.y + dy[arr[direction]];
            if (!valid(nx, ny) || ny < airCleaner2.y) {
                direction++;
                nx = next.x + dx[arr[direction]];
                ny = next.y + dy[arr[direction]];
            }
            next = new Node(nx, ny);
        }
    }

    private static class Node {
        int x, y, amount;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Node(int x, int y, int amount) {
            this.x = x;
            this.y = y;
            this.amount = amount;
        }
    }
}
