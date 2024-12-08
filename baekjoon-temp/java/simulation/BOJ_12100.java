package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12100 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int N, result;
    private static Node[][] board;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(in.readLine());
        board = new Node[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = new Node(Integer.parseInt(st.nextToken()), false);
            }
        }

        // 중복 순열
        multisetPermutation(0, "");
        System.out.println(result);
    }

    private static void multisetPermutation(int depth, String str) throws CloneNotSupportedException {
        if (depth == 5) {
//            Node[][] tmpBoard = board.clone();
            Node[][] tmpBoard = new Node[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    tmpBoard[i][j] = board[i][j].clone();
                }
            }

            for (int i = 0; i < 5; i++) {
                play(tmpBoard, str.charAt(i));
                initCombineCnt(tmpBoard);
            }
            result = Math.max(result, findMaxBlock(tmpBoard));
            return;
        }

        for (int i = 0; i < 4; i++) {
            multisetPermutation(depth + 1, str + i);
        }
    }

    private static int findMaxBlock(Node[][] board) {
        int maxVal = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                maxVal = Math.max(maxVal, board[i][j].val);
            }
        }
        return maxVal;
    }

    private static void initCombineCnt(Node[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j].isCombine = false;
            }
        }
    }

    private static void play(Node[][] board, char c) {
        if (c == '0') { // 상
            for (int x = 0; x < N; x++) {
                boolean move = true;
                while (move) {
                    move = false;
                    for (int y = 1; y < N; y++) {
                        if (board[y - 1][x].val == 0 && board[y][x].val != 0) {
                            board[y - 1][x].val = board[y][x].val;
                            board[y - 1][x].isCombine = board[y][x].isCombine;
                            board[y][x].init();
                            move = true;
                        } else if (board[y - 1][x].val == board[y][x].val) {
                            if (!board[y - 1][x].isCombine && !board[y][x].isCombine) {
                                board[y - 1][x].val += board[y][x].val;
                                board[y - 1][x].isCombine = true;
                                board[y][x].init();
                                move = true;
                            }
                        }
                    }
                }
            }
        } else if (c == '1') { // 하
            for (int x = 0; x < N; x++) {
                boolean move = true;
                while (move) {
                    move = false;
                    for (int y = N - 2; y >= 0; y--) {
                        if (board[y + 1][x].val == 0 && board[y][x].val != 0) {
                            board[y + 1][x].val = board[y][x].val;
                            board[y + 1][x].isCombine = board[y][x].isCombine;
                            board[y][x].init();
                            move = true;
                        } else if (board[y + 1][x].val == board[y][x].val) {
                            if (!board[y + 1][x].isCombine && !board[y][x].isCombine) {
                                board[y + 1][x].val *= 2;
                                board[y + 1][x].isCombine = true;
                                board[y][x].init();
                                move = true;
                            }
                        }
                    }
                }
            }
        } else if (c == '2') { // 좌
            for (int y = 0; y < N; y++) {
                boolean move = true;
                while (move) {
                    move = false;
                    for (int x = 1; x < N; x++) {
                        if (board[y][x - 1].val == 0 && board[y][x].val != 0) {
                            board[y][x - 1].val = board[y][x].val;
                            board[y][x - 1].isCombine = board[y][x].isCombine;
                            board[y][x].init();
                            move = true;
                        } else if (board[y][x - 1].val == board[y][x].val) {
                            if (!board[y][x - 1].isCombine && !board[y][x].isCombine) {
                                board[y][x - 1].val *= 2;
                                board[y][x - 1].isCombine = true;
                                board[y][x].init();
                                move = true;
                            }
                        }
                    }
                }
            }
        } else if (c == '3') { // 우
            for (int y = 0; y < N; y++) {
                boolean move = true;
                while (move) {
                    move = false;
                    for (int x = N - 2; x >= 0; x--) {
                        if (board[y][x + 1].val == 0 && board[y][x].val != 0) {
                            board[y][x + 1].val = board[y][x].val;
                            board[y][x + 1].isCombine = board[y][x].isCombine;
                            board[y][x].init();
                            move = true;
                        } else if (board[y][x + 1].val == board[y][x].val) {
                            if (!board[y][x + 1].isCombine && !board[y][x].isCombine) {
                                board[y][x + 1].val *= 2;
                                board[y][x + 1].isCombine = true;
                                board[y][x].init();
                                move = true;
                            }
                        }
                    }
                }
            }
        }
    }

    private static class Node implements Cloneable {
        int val;
        boolean isCombine;

        public Node(int val, boolean isCombine) {
            this.val = val;
            this.isCombine = isCombine;
        }

        @Override
        protected Node clone() throws CloneNotSupportedException {
//            return (Node) super.clone();
            return new Node(this.val, false);
        }

        private void init() {
            this.val = 0;
            this.isCombine = false;
        }
    }
}
