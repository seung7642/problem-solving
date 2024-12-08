package graph.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 주어진 시작점 목록에서 가장 빠르게 탐색할 수 있는 M개의 시작점 찾기.
public class BOJ_17142 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M, cnt, ans = Integer.MAX_VALUE;
    private static int[][] map;
    private static boolean[] visited;
    private static List<Node> virus = new ArrayList<>(); // 바이러스 위치를 담을 리스트.
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};

    private static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) virus.add(new Node(j, i));
                if (map[i][j] == 0) cnt++;
            }
        }

        visited = new boolean[virus.size()];

        if (cnt != 0) comb(0, 0);
        else ans = 0;

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
        br.close();
    }

    // 최소시간이 되는 M개의 바이러스를 선택해야 한다. 따라서, 조합 알고리즘이 필요하다.
    private static void comb(int depth, int start) {
        if (depth == M) {
            int[][] copyMap = copy();
            bfs(copyMap, cnt);
            return;
        }

        for (int i = start; i < virus.size(); i++) {
            visited[i] = true;
            comb(depth + 1, i + 1);
            visited[i] = false;
        }
    }

    private static int[][] copy() {
        int[][] result = new int[N][N];
        for (int i = 0; i < N; i++)
            System.arraycopy(map[i], 0, result[i], 0, N);

        for (int i = 0; i < virus.size(); i++) {
            if (!visited[i]) {
                Node node = virus.get(i);
                result[node.y][node.x] = 3; // 비활성 바이러스는 3으로 바꾼다.
            }
        }
        return result;
    }

    private static void bfs(int[][] map, int cnt) {
        Queue<Node> queue = new LinkedList<>();

        for (int i = 0; i < virus.size(); i++) {
            if (visited[i]) queue.add(virus.get(i));
        }

        int time = 0;
        while (!queue.isEmpty()) {
            if (ans <= time) break;

            int len = queue.size();
            for (int t = 0; t < len; t++) {
                Node index = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = index.x + dx[i];
                    int ny = index.y + dy[i];

                    if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                    if (map[ny][nx] == 1 || map[ny][nx] == 2) continue; //

                    if (map[ny][nx] == 0) cnt--;
                    map[ny][nx] = 2;

                    queue.add(new Node(nx, ny));
                }
            }

            time++;
            if (cnt == 0) {
                ans = time;
                return;
            }
        }
    }
}
