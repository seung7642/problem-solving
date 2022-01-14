package highScoreKit.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class A {

    public static void main(String[] args) {
        int n;
        int[][] edge;

        n = 6;
        edge = new int[][]{{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
        System.out.println(solution(n, edge)); // 답: 3

    }

    private static boolean[] visited;
    private static List<Integer>[] listArr;
    private static int len, cnt;

    public static int solution(int n, int[][] edge) {
        len = cnt = 0;

        visited = new boolean[n + 1];
        listArr = new List[n + 1];
        for (int i = 0; i < listArr.length; i++) {
            listArr[i] = new ArrayList<>();
        }

        for (int i = 0; i < edge.length; i++) {
            int v1 = edge[i][0];
            int v2 = edge[i][1];
            listArr[v1].add(v2);
            listArr[v2].add(v1);
        }

        bfs();

        return cnt;
    }

    private static void bfs() {
        Queue<Node> q = new LinkedList<>();
        visited[1] = true;
        q.add(new Node(1, 0));
        while (!q.isEmpty()) {
            Node node = q.poll();
            if (len < node.depth) {
                len = node.depth;
                cnt = 0;
            }
            cnt++;
            for (int v : listArr[node.num]) {
                if (visited[v]) continue;
                visited[v] = true;
                q.add(new Node(v, node.depth + 1));
            }
        }
    }

    private static class Node {
        int num, depth;

        public Node(int num, int depth) {
            this.num = num;
            this.depth = depth;
        }
    }
}
