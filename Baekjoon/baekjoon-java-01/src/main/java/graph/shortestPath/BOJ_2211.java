package graph.shortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2211 {

    private static final int INF = 1_000_000_000;

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static List<Node>[] edge;
    private static int[] dist, path;
    private static HashSet<String> resultSet = new HashSet<>();

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

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        init();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edge[u].add(new Node(v, weight));
            edge[v].add(new Node(u, weight));
        }

        dijkstra(1);
        print();

        br.close();
    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            for (Node next : edge[cur.v]) {
                if (dist[next.v] > dist[cur.v] + next.weight) {
                    dist[next.v] = dist[cur.v] + next.weight;
                    pq.add(new Node(next.v, dist[next.v]));
                    path[next.v] = cur.v;
                }
            }
        }
    }

    private static void print() {
        for (int i = 2; i <= N; i++) {
            int end = i;
            while (path[end] != 0) {
                resultSet.add(end + " " + path[end]);
                end = path[end];
            }
        }
        System.out.println(resultSet.size());
        for (String str : resultSet) {
            System.out.println(str);
        }
    }

    private static void init() {
        path = new int[N + 1];
        dist = new int[N + 1];
        edge = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            dist[i] = INF;
            edge[i] = new ArrayList<>();
        }
    }
}
