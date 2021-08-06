package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_6109 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int T, N;
    static String cmd;
    static Node[][] board;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            cmd = st.nextToken();
            board = new Node[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = new Node(Integer.parseInt(st.nextToken()));
                }
            }

            if (cmd.equals("left")) {
                for (int y = 0; y < N; y++) {
                    boolean work = true;
                    while (work) {
                        work = false;
                        for (int x = 1; x < N; x++) {
                            if (board[y][x - 1].num == 0 && board[y][x].num != 0) {
                                board[y][x - 1] = board[y][x];
                                board[y][x] = new Node(0);
                                work = true;
                            } else if (board[y][x - 1].num == board[y][x].num) {
                                if (!board[y][x - 1].combine && !board[y][x].combine) {
                                    board[y][x - 1].num *= 2;
                                    board[y][x - 1].combine = true;
                                    board[y][x] = new Node(0);
                                    work = true;
                                }
                            }
                        }
                    }
                }
            } else if (cmd.equals("right")) {
                for (int y = 0; y < N; y++) {
                    boolean work = true;
                    while (work) {
                        work = false;
                        for (int x = N - 2; x >= 0; x--) {
                            if (board[y][x + 1].num == 0 && board[y][x].num != 0) {
                                board[y][x + 1] = board[y][x];
                                board[y][x] = new Node(0);
                                work = true;
                            } else if (board[y][x + 1].num == board[y][x].num) {
                                if (!board[y][x + 1].combine && !board[y][x].combine) {
                                    board[y][x + 1].num *= 2;
                                    board[y][x + 1].combine = true;
                                    board[y][x] = new Node(0);
                                    work = true;
                                }
                            }
                        }
                    }
                }
            } else if (cmd.equals("up")) {
                for (int x = 0; x < N; x++) {
                    boolean work = true;
                    while (work) {
                        work = false;
                        for (int y = 1; y < N; y++) {
                            if (board[y - 1][x].num == 0 && board[y][x].num != 0) {
                                board[y - 1][x] = board[y][x];
                                board[y][x] = new Node(0);
                                work = true;
                            } else if (board[y - 1][x].num == board[y][x].num) {
                                if (!board[y - 1][x].combine && !board[y][x].combine) {
                                    board[y - 1][x].num *= 2;
                                    board[y - 1][x].combine = true;
                                    board[y][x] = new Node(0);
                                    work = true;
                                }
                            }
                        }
                    }
                }
            } else if (cmd.equals("down")) {
                for (int x = 0; x < N; x++) {
                    boolean work = true;
                    while (work) {
                        work = false;
                        for (int y = N - 2; y >= 0; y--) {
                            if (board[y + 1][x].num == 0 && board[y][x].num != 0) {
                                board[y + 1][x] = board[y][x];
                                board[y][x] = new Node(0);
                                work = true;
                            } else if (board[y + 1][x].num == board[y][x].num) {
                                if (!board[y + 1][x].combine && !board[y][x].combine) {
                                    board[y + 1][x].num *= 2;
                                    board[y + 1][x].combine = true;
                                    board[y][x] = new Node(0);
                                    work = true;
                                }
                            }
                        }
                    }
                }
            }

            System.out.printf("#%d\n", tc);
            print();
        }
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(board[i][j].num).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    private static class Node {
        int num;
        boolean combine;

        public Node(int num) {
            this.num = num;
            combine = false;
        }

        public Node(int num, boolean combine) {
            this.num = num;
            this.combine = combine;
        }
    }
}
