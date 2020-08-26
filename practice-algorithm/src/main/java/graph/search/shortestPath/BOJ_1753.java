package graph.search.shortestPath;

import java.util.*;

// 문제: 최단 경로
public class BOJ_1753 {

    private static final int INF = 1000000000;
    private static int V, E, K;
    private static List<List<Node>> list;
    private static int[] dist;

    static class Node implements Comparable<Node> {

        int v, weight;

        public Node(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return weight - o.weight;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        V = sc.nextInt(); E = sc.nextInt(); K = sc.nextInt();
        list = new ArrayList<>();
        dist = new int[V + 1];

        Arrays.fill(dist, INF);
        for (int i = 0; i < V + 1 ; i++)
            list.add(new ArrayList<>());

        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int weight = sc.nextInt();

            list.get(u).add(new Node(v, weight));
        }

        dijkstra(list, dist, K);

        for (int i = 1; i < V + 1; i++)
            System.out.println(dist[i] == INF ? "INF" : dist[i]);
    }

    static void dijkstra(List<List<Node>> list, int[] dist, int start) {
        boolean[] visited = new boolean[V + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();

        dist[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            int edge = pq.poll().v;

            if (visited[edge]) continue;
            visited[edge] = true;

            for (Node node : list.get(edge)) {
                if (dist[node.v] > dist[edge] + node.weight) {
                    dist[node.v] = dist[edge] + node.weight;
                    pq.add(new Node(node.v, dist[node.v]));
                }
            }
        }
    }
}