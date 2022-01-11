package highScoreKit.greedy;

import java.util.PriorityQueue;
import java.util.Queue;

public class E {

    public static void main(String[] args) {
        int n;
        int[][] costs;

        n = 4;
        costs = new int[][]{{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}};
        System.out.println(solution(n, costs)); // 답: 4
    }

    private static int[] parent;
    private static int conn, answer;

    public static int solution(int n, int[][] costs) {
        Queue<Node> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        for (int i = 0; i < costs.length; i++) {
            int v1 = costs[i][0];
            int v2 = costs[i][1];
            int cost = costs[i][2];
            pq.add(new Node(v1, v2, cost));
        }

        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        while (!pq.isEmpty() && conn < n - 1) {
            Node front = pq.poll();
            union(front.v1, front.v2, front.cost);
        }

        return answer;
    }

    private static void union(int v1, int v2, int cost) {
        int a = find(v1);
        int b = find(v2);
        if (a != b) {
            parent[b] = a;
            conn++;
            answer += cost;
        }
    }

    private static int find(int v) {
        if (v == parent[v]) {
            return v;
        }
        return parent[v] = find(parent[v]);
    }

    private static class Node {
        int v1, v2, cost;

        public Node(int v1, int v2, int cost) {
            this.v1 = v1;
            this.v2 = v2;
            this.cost = cost;
        }
    }
}
