package graph.minimumSpanningTree;

import java.io.*;
import java.util.*;

public class BOJ_17472 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, M;
    private static int[] parent;
    private static int[][] map;
    private static int[] dx = { 0, 0, 1, -1 };
    private static int[] dy = { 1, -1, 0, 0 };
    private static Queue<Node> q = new LinkedList<>();
    private static PriorityQueue<Node> pq = new PriorityQueue<>();

    static class Node implements Comparable<Node> {
        int x, y, val;

        public Node(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }

        @Override
        public int compareTo(Node o) {
            return val - o.val;
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) map[i][j] = -1;
            }
        }

        int islandNum = 1; // 총 구역의 수
        for (int i = 0; i < N; i++) { // 구역별로 새로 맵에 할당한다.
            for (int j = 0; j < M; j++) {
                if (map[i][j] == -1)
                    dfs(i, j, islandNum++);
            }
        }

        while (!q.isEmpty()) {
            Node node = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                int len = 0;

                // 가장자리에 있는 땅이 아니라면 넘어간다.
//                if (map[nx][ny] == map[node.x][node.y]) continue;

                // 다리는 한 방향으로만 계속 되어야 한다.
                while (nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] != map[node.x][node.y]) {
                    if (map[nx][ny] != 0) {
                        pq.add(new Node(map[node.x][node.y], map[nx][ny], len));
                        break;
                    }
                    nx += dx[i];
                    ny += dy[i];
                    len++;
                }
            }
        }

        parent = new int[islandNum - 1];
        Arrays.fill(parent, -1);

        int lenSum = 0;
        while (!pq.isEmpty()) {
            Node temp = pq.poll();
            if (temp.val > 1 && union(temp.x - 1, temp.y - 1))
                lenSum += temp.val;
        }

        if (isAllBridgeUnion()) bw.write(lenSum + "\n");
        else bw.write("-1\n");

        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int x, int y, int islandNum) {
        map[x][y] = islandNum;
        q.add(new Node(x, y, islandNum)); // 1번 구역 땅부터 차례로 담는다.

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                if (map[nx][ny] == -1)
                    dfs(nx, ny, islandNum);
            }
        }
    }

    private static boolean isAllBridgeUnion() {
        int num = 0;
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] < 0) num++;
        }

        if (num == 1) return true;
        else return false;
    }

    private static boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            parent[y] = x;
            return true;
        }
        return false;
    }

    private static int find(int x) {
        if (parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    private static boolean isSameParent(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) return true;
        else return false;
    }
}
