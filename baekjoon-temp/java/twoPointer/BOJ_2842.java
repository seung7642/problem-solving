package twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2842 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, houseNum, startX, startY, ans = 1_000_000;
    private static char[][] map;
    private static int[][] heightMap;
    private static List<Integer> heightList = new ArrayList<>();
    private static boolean[][] visited;
    private static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    private static int[] dy = {0, 0, 1, -1, -1, 1, 1, -1};

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        heightMap = new int[N][N];
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'K') {
                    houseNum++;
                }
                if (map[i][j] == 'P') {
                    startX = j;
                    startY = i;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                heightMap[i][j] = Integer.parseInt(st.nextToken());
                if (!heightList.contains(heightMap[i][j])) heightList.add(heightMap[i][j]);
            }
        }

        Collections.sort(heightList);

        bfs();
        br.close();
    }

    public static void bfs() {
        int low = 0, high = 0;

        while (low < heightList.size()) {
            visited = new boolean[N][N];
            Queue<Node> queue = new LinkedList<>();
            int val = heightMap[startY][startX];

            if (heightList.get(low) <= val && val <= heightList.get(high)) {
                visited[startY][startX] = true;
                queue.add(new Node(startX, startY));
            }

            int count = 0;
            while (!queue.isEmpty()) {
                Node cur = queue.poll();

                if (map[cur.y][cur.x] == 'K') count++;

                for (int d = 0; d < 8; d++) {
                    int nx = cur.x + dx[d];
                    int ny = cur.y + dy[d];

                    if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                    if (visited[ny][nx]) continue;

                    int nextVal = heightMap[ny][nx];

                    // 탐색의 조건에 해당한다.
                    // high 고도 - low 고도의 높이 차에 해당하는 땅만 밝아가며 탐색한다는 의미.
                    if (heightList.get(low) <= nextVal && nextVal <= heightList.get(high)) {
                        visited[ny][nx] = true;
                        queue.add(new Node(nx, ny));
                    }
                }
            }

            if (houseNum == count) {
                ans = Math.min(ans, heightList.get(high) - heightList.get(low));
                low++;
            } else if (high + 1 < heightList.size()) {
                high++;
            } else
                break;
        }

        System.out.println(ans);
    }
}
