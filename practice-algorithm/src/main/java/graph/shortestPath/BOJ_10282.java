package graph.shortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_10282 {

    private static final int INF = 1_000_000_000;

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int T, N, D, C;
    private static List<Node>[] edge;
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

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            init();

            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                int v = Integer.parseInt(st.nextToken());
                int u = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                edge[u].add(new Node(v, weight));
            }

            dijkstra(C);
            print();
        }

        br.close();
    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()){
            Node cur = pq.poll();

            for (Node next : edge[cur.v]) {
                if (dist[next.v] > dist[cur.v] + next.weight) {
                    dist[next.v] = dist[cur.v] + next.weight;
                    pq.add(new Node(next.v, dist[next.v]));
                }
            }
        }
    }

    private static void print() {
        int infectNum = 0, time = 0;
        for (int i = 1; i <= N; i++) {
            if (dist[i] != INF) {
                infectNum++;
                time = Math.max(time, dist[i]);
            }
        }
        System.out.println(infectNum + " " + time);
    }

    private static void init() {
        edge = new ArrayList[N + 1];
        dist = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            edge[i] = new ArrayList<>();
            dist[i] = INF;
        }
    }
}
