package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17143 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int R, C, M, ans;
    private static Node[][] board;
    private static Queue<Node> q = new LinkedList<>();
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(in.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new Node[R + 1][C + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken()); // 속력
            int direction = Integer.parseInt(st.nextToken()) - 1; // 방향
            int size = Integer.parseInt(st.nextToken()); // 크기
            q.add(new Node(x, y, speed, direction, size));
        }

        for (int x = 1; x <= C; x++) {
            // 1. catch
            catchingTheShark(x);

            // 2. The shark moves.
            moveShark();
        }
        System.out.println(ans);
    }

    private static void moveShark() {
        while (!q.isEmpty()) {
            Node node = q.poll();
            int speed = node.speed;
            int direction = node.direction;
            if (direction == 0 || direction == 1) { // 상하
                speed %= (R * 2 - 2);
            } else { // 좌우
                speed %= (C * 2 - 2);
            }

            int nx = node.x;
            int ny = node.y;
            for (int i = 0; i < speed; i++) {
                nx += dx[direction];
                ny += dy[direction];
                if (!valid(nx, ny)) {
                    if (direction % 2 == 0) direction++;
                    else direction--;
                    nx += dx[direction] * 2;
                    ny += dy[direction] * 2;
                }
            }

            // 해당 위치에 상어가 없다면 추가하고, 있다면 크기를 비교해 기존께 작다면 새로 바꾼다.
            if (board[ny][nx] == null || board[ny][nx].size < node.size) {
                board[ny][nx] = new Node(nx, ny, node.speed, direction, node.size);
            }
        }

        // 맵을 순회하면서 상어를 큐에 다시 담는다.
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (board[i][j] == null) continue;
                q.add(board[i][j]);
                board[i][j] = null;
            }
        }
    }

    private static boolean valid(int x, int y) {
        return 0 < x && x <= C && 0 < y && y <= R;
    }

    private static void catchingTheShark(int x) {
        // 해당 x축에 있는 상어들을 y값 오름차순으로 우선순위 큐에 담는다. (가장 앞 쪽의 상어를 잡는다.)
        Queue<Node> tmp = new PriorityQueue<>((o1, o2) -> o1.y - o2.y);
        int len = q.size();
        while (len-- > 0) {
            Node node = q.poll();
            if (node.x == x) tmp.add(node);
            else q.add(node);
        }
        if (!tmp.isEmpty()) {
            ans += tmp.poll().size;
        }
        while (!tmp.isEmpty()) {
            q.add(tmp.poll());
        }
    }

    private static class Node {
        int x, y;
        int speed, direction, size;

        public Node(int x, int y, int speed, int direction, int size) {
            this.x = x;
            this.y = y;
            this.speed = speed;
            this.direction = direction;
            this.size = size;
        }
    }
}
