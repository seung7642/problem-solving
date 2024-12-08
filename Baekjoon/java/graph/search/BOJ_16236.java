package graph.search;

import java.io.*;
import java.util.*;

public class BOJ_16236 {

    public static final int max_val = 401, max_int = 21;
    public static int n, shark_x, shark_y, min_dist, min_x, min_y, result, eat_cnt = 0, shark_size = 2;
    public static int[][] board, check;
    public static int[] dx = {0, 0, 1, -1}, dy = {-1, 1, 0, 0};

    private static class info {
        int x, y;

        info(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        board = new int[n + 1][n + 1];
        check = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());

                if (board[i][j] == 9) {
                    shark_x = i;
                    shark_y = j;
                    board[i][j] = 0;
                }
            }
        }

        while (true) {
            init_check();
            bfs(shark_x, shark_y); // 1. 완전 탐색으로 먹을 수 있는 물고기를 찾는다.

            if (min_x != max_int && min_y != max_int) { // 먹을 수 있는 물고기를 찾은 경우
                result += check[min_x][min_y];
                eat_cnt++;

                if (eat_cnt == shark_size) {
                    shark_size++;
                    eat_cnt = 0;
                }

                board[min_x][min_y] = 0;

                shark_x = min_x;
                shark_y = min_y;
            } else { // 더이상 먹을 수 있는 물고기가 없는 경우
                break;
            }
        }

        System.out.println(result);
        br.close();
    }

    private static void init_check() {
        min_dist = max_val;
        min_x = max_int;
        min_y = max_int;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                check[i][j] = -1;
            }
        }
    }

    private static void bfs(int x, int y) {
        Queue<info> q = new LinkedList<>();
        check[x][y] = 0; // 아기 상어의 처음 위치에서의 시간은 0
        q.add(new info(x, y));

        while (!q.isEmpty()) {
            info cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 1 || nx > n || ny < 1 || ny > n) continue;
                if (check[nx][ny] != -1 || board[nx][ny] > shark_size) continue;

                check[nx][ny] = check[cur.x][cur.y] + 1;

                if (board[nx][ny] != 0 && board[nx][ny] < shark_size) { // 먹을 수 있는 물고기인 경우
                    if (min_dist > check[nx][ny]) { // 현재 물고기까지의 이동 시간이 더 짧은 경우
                        min_x = nx;
                        min_y = ny;
                        min_dist = check[nx][ny];
                    } else if (min_dist == check[nx][ny]) { // 현재 물고기까지의 이동 시간이 같다면, 1) 가장 위쪽, 가장 왼쪽을 찾는다.
                        if (min_x == nx) {
                            if (min_y > ny) {
                                min_x = nx;
                                min_y = ny;
                            }
                        } else if (min_x > nx) {
                            min_x = nx;
                            min_y = ny;
                        }
                    }
                }

                q.add(new info(nx, ny)); // 물고기의 위치를 큐에 담는다.
            }
        }
    }
}