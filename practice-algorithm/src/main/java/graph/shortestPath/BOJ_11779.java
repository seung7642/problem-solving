package graph.shortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_11779 {

    private static final int INF = 1_000_000_000;

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M, start, end, cityCnt;
    private static List<List<Node>> list;
    private static int[] dist, parent;
    private static Stack<Integer> path = new Stack<>();

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
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        init();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            list.get(u).add(new Node(v, weight));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        dijkstra(start);
        searchPath(start, end);
        print();

        br.close();
    }

    private static void dijkstra(int start) {
        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.v]) continue;
            visited[cur.v] = true;

            for (Node next : list.get(cur.v)) {
                if (dist[next.v] > dist[cur.v] + next.weight) {
                    parent[next.v] = cur.v;
                    dist[next.v] = dist[cur.v] + next.weight;
                    pq.add(new Node(next.v, dist[next.v]));
                }
            }
        }
    }

    private static void searchPath(int start, int end) {
        int cur = end;

        while (cur != start) {
            path.push(cur);
            cityCnt++;
            cur = parent[cur];
        }
        path.push(cur);
        cityCnt++;
    }

    private static void print() {
        System.out.println(dist[end] + "\n" + cityCnt);
        while (!path.isEmpty()) {
            System.out.print(path.pop() + " ");
        }
    }

    private static void init() {
        parent = new int[N + 1];
        dist = new int[N + 1];
        Arrays.fill(dist, INF);

        list = new ArrayList<>();
        for (int i = 0; i <= N; i++)
            list.add(new ArrayList<>());
    }
}
