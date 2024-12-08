package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_15686 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, result = Integer.MAX_VALUE, chickenStoreCnt;
    static int[][] board;
    static boolean[] visited;
    static List<Node> chickenStores = new ArrayList<>();
    static List<Node> homes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N + 1][N + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) {
                    homes.add(new Node(j, i));
                } else if (board[i][j] == 2) {
                    chickenStores.add(new Node(j, i));
                }
            }
        }

        chickenStoreCnt = chickenStores.size();
        visited = new boolean[chickenStoreCnt];

        // 1. 존재하는 치킨집 중에서 M개를 선택한다. 순서 상관X. 즉, 조합 nCr이다.
        // 2. 선택된 M개의 치킨집과 집 사이의 최소 거리를 구한다.
        backtracking(0, 0);

        System.out.println(result);
    }

    private static void backtracking(int idx, int depth) {
        if (depth == M) { // Base case
            getDist();
        }

        for (int i = idx; i < chickenStoreCnt; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            backtracking(i + 1, depth + 1);
            visited[i] = false;
        }
    }

    private static void getDist() {
        int sum = 0;
        for (Node home : homes) {
            int minDist = Integer.MAX_VALUE;
            for (int i = 0; i < chickenStoreCnt; i++) {
                if (visited[i]) {
                    Node chickenPos = chickenStores.get(i);
                    minDist = Math.min(minDist, Math.abs(home.x - chickenPos.x) + Math.abs(home.y - chickenPos.y));
                }
            }
            sum += minDist;
        }
        result = Math.min(result, sum);
    }

    private static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
