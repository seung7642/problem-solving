package graph.search.shortestPath;

import java.util.*;

// 문제: 특정한 최단 경로
public class BOJ_1504 {

    private static final int INF = 100000000;
    private static int V, E;
    private static List<List<Node>> list = new ArrayList<>();
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
        V = sc.nextInt(); E = sc.nextInt();
        dist = new int[V + 1];

        for (int i = 0; i < V + 1 ; i++)
            list.add(new ArrayList<>());

        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int weight = sc.nextInt();

            // 양방향이기 때문에 두 정점에 모두 추가해준다.
            list.get(u).add(new Node(v, weight));
            list.get(v).add(new Node(u, weight));
        }

        int require1 = sc.nextInt();
        int require2 = sc.nextInt();

        System.out.println(solve(require1, require2));

        sc.close();
    }

    private static int solve(int require1, int require2) {
        int result1 = 0;
        int result2 = 0;

        result1 += dijkstra(1, require1);
        result1 += dijkstra(require1, require2);
        result1 += dijkstra(require2, V);

        result2 += dijkstra(1, require2);
        result2 += dijkstra(require2, require1);
        result2 += dijkstra(require1, V);

        if(result1 >= INF && result2 >= INF) return -1;
        else return Math.min(result1, result2);
    }

    /**
     * u 정점에서 v 정점까지의 최단 경로를 반환한다.
     * @param u
     * @param v
     */
    private static int dijkstra(int u, int v) {
        Arrays.fill(dist, INF);

        boolean[] visited = new boolean[V + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();

        dist[u] = 0;
        pq.add(new Node(u, 0));

        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();

            if (visited[currentNode.v]) continue;
            visited[currentNode.v] = true;

            for (Node node : list.get(currentNode.v)) {
                if (dist[node.v] > dist[currentNode.v] + node.weight) {
                    dist[node.v] = dist[currentNode.v] + node.weight;
                    pq.add(new Node(node.v, dist[node.v]));
                }
            }
        }

        return dist[v];
    }
}
