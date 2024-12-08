package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15683 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M, cctvCnt, result = Integer.MAX_VALUE;
    private static int[][] board;
    private static List<Node> cctvs = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (0 < board[i][j] && board[i][j] < 6) {
                    cctvs.add(new Node(j, i, board[i][j]));
                }
            }
        }

        cctvCnt = cctvs.size();
        multisetPermutation(0, "");
        System.out.println(result);
    }

    private static void multisetPermutation(int depth, String str) {
        if (depth == cctvCnt) { // Base case
            int[][] tmpBoard = new int[N][M];
            for (int i = 0; i < N; i++) {
                System.arraycopy(board[i], 0, tmpBoard[i], 0, M);
            }

            for (int i = 0; i < cctvCnt; i++) {
                Node cctv = cctvs.get(i);
                char direction = str.charAt(i);
                setMonitorArea(tmpBoard, cctv, direction);
            }
            result = Math.min(result, findBlindSpot(tmpBoard));
            return;
        }

        for (int i = 0; i < 4; i++) {
            multisetPermutation(depth + 1, str + i);
        }
    }

    private static int findBlindSpot(int[][] board) {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 0) cnt++;
            }
        }
        return cnt;
    }

    private static void setMonitorArea(int[][] board, Node cctv, char direction) {
        if (cctv.type == 1) {
            if (direction == '0') {
                right(board, cctv);
            } else if (direction == '1') {
                down(board, cctv);
            } else if (direction == '2') {
                left(board, cctv);
            } else if (direction == '3') {
                up(board, cctv);
            }
        } else if (cctv.type == 2) {
            if (direction == '0' || direction == '2') {
                left(board, cctv);
                right(board, cctv);
            } else if (direction == '1' || direction == '3') {
                up(board, cctv);
                down(board, cctv);
            }
        } else if (cctv.type == 3) {
            if (direction == '0') {
                up(board, cctv);
                right(board, cctv);
            } else if (direction == '1') {
                right(board, cctv);
                down(board, cctv);
            } else if (direction == '2') {
                down(board, cctv);
                left(board, cctv);
            } else if (direction == '3') {
                left(board, cctv);
                up(board, cctv);
            }
        } else if (cctv.type == 4) {
            if (direction == '0') {
                left(board, cctv);
                up(board, cctv);
                right(board, cctv);
            } else if (direction == '1') {
                up(board, cctv);
                right(board, cctv);
                down(board, cctv);
            } else if (direction == '2') {
                right(board, cctv);
                down(board, cctv);
                left(board, cctv);
            } else if (direction == '3') {
                down(board, cctv);
                left(board, cctv);
                up(board, cctv);
            }
        } else if (cctv.type == 5) {
            up(board, cctv);
            down(board, cctv);
            left(board, cctv);
            right(board, cctv);
        }
    }

    private static void up(int[][] board, Node cctv) {
        for (int y = cctv.y - 1; y >= 0; y--) {
            if (board[y][cctv.x] == 6) break;
            board[y][cctv.x] = -1;
        }
    }

    private static void down(int[][] board, Node cctv) {
        for (int y = cctv.y + 1; y < N; y++) {
            if (board[y][cctv.x] == 6) break;
            board[y][cctv.x] = -1;
        }
    }

    private static void left(int[][] board, Node cctv) {
        for (int x = cctv.x - 1; x >= 0; x--) {
            if (board[cctv.y][x] == 6) break;
            board[cctv.y][x] = -1;
        }
    }

    private static void right(int[][] board, Node cctv) {
        for (int x = cctv.x + 1; x < M; x++) {
            if (board[cctv.y][x] == 6) break;
            board[cctv.y][x] = -1;
        }
    }

    private static class Node {
        int x, y, type;

        public Node(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }
}
