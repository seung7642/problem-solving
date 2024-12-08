package winterCoding_2020;

import java.util.LinkedList;
import java.util.Queue;

public class C {

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        // 무: 0, 고구마: 1, 감자: 2
        // 각 구역의 수를 구하시오.
        int[][] map = {{0,0,1,1},{1,1,1,1},{2,2,2,1},{0,0,0,2}};
        int[] result = solution(map); // result: [2, 1, 2]

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            sb.append(result[i]).append(" ");
        }
        System.out.println(sb);
    }

    private static int[] solution(int[][] v) {
        int[] answer = new int[3];
        int len = v.length;
        boolean[][] visited;

        for (int k = 0; k < 3; k++) {
            visited = new boolean[len][len];

            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    if (visited[i][j]) continue;
                    if (v[i][j] == k) {
                        bfs(j, i, v, visited, k);
                        answer[k]++;
                    }
                }
            }
        }

        return answer;
    }

    private static void bfs(int x, int y, int[][] map, boolean[][] visited, int k) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        int size = map.length;

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x, y));
        visited[y][x] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || nx >= size || ny < 0 || ny >= size) continue;
                if (map[ny][nx] != k) continue;
                if (visited[ny][nx]) continue;

                visited[ny][nx] = true;
                q.add(new Node(nx, ny));
            }
        }
    }
}
