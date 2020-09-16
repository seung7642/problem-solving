package graph.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13459 {

    public static int N, M;
    public static char[][] map;
    public static boolean[][][][] visited;
    public static int[] dirX = new int[] { 0, 0, 1, -1 }; // 동서남북
    public static int[] dirY = new int[] { 1, -1, 0, 0 };
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static class Node {
        int rRow, rCol, bRow, bCol, cnt;

        public Node(int rRow, int rCol, int bRow, int bCol, int cnt) {
            this.rRow = rRow;
            this.rCol = rCol;
            this.bRow = bRow;
            this.bCol = bCol;
            this.cnt = cnt;
        }

        public Node() {
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new boolean[10][10][10][10];

        Node node = new Node();
        node.cnt = 0;

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'R') {
                    node.rRow = i;
                    node.rCol = j;
                } else if (map[i][j] == 'B') {
                    node.bRow = i;
                    node.bCol = j;
                }
            }
        }
        int ans = bfs(node);
        System.out.println(ans);
    }

    private static int bfs(Node start) {
        Queue<Node> q = new LinkedList<>();
        q.offer(start);

        while (!q.isEmpty()) {
            Node node = q.poll();
            visited[node.rRow][node.rCol][node.bRow][node.bCol] = true;

            if (node.cnt >= 10) return 0;

            for (int i = 0; i < 4; i++) {
                int nbRow = node.bRow, nrRow = node.rRow;
                int nbCol = node.bCol, nrCol = node.rCol;

                while (map[nbRow + dirX[i]][nbCol + dirY[i]] != '#') {
                    nbRow += dirX[i];
                    nbCol += dirY[i];
                    if (map[nbRow][nbCol] == 'O') break;
                }

                while (map[nrRow + dirX[i]][nrCol + dirY[i]] != '#') {
                    nrRow += dirX[i];
                    nrCol += dirY[i];
                    if (map[nrRow][nrCol] == 'O') break;
                }

                if (map[nbRow][nbCol] == 'O') continue;

                if (map[nrRow][nrCol] == 'O') return 1;

                if (nrRow == nbRow && nrCol == nbCol) {
                    switch (i) {
                        case 0:
                            if (node.rCol > node.bCol) nbCol -= 1;
                            else nrCol -= 1;
                            break;
                        case 1:
                            if (node.rCol > node.bCol) nrCol += 1;
                            else nbCol += 1;
                            break;
                        case 2: // 남
                            if (node.rRow > node.bRow) nbRow -= 1;
                            else nrRow -= 1;
                            break;
                        case 3: // 북
                            if (node.rRow > node.bRow) nrRow += 1;
                            else nbRow += 1;
                            break;
                    }
                }

                if (!visited[nrRow][nrCol][nbRow][nbCol])
                    q.offer(new Node(nrRow, nrCol, nbRow, nbCol, node.cnt + 1));
            }
        }

        return 0;
    }
}
