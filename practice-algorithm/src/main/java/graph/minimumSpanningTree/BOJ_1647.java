package graph.minimumSpanningTree;

import java.io.*;
import java.util.*;

public class BOJ_1647 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static PriorityQueue<Edge> pq;
    private static int[] parent;

    static class Edge implements Comparable<Edge> {
        int u, v, weight;

        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            if (weight < o.weight) return -1;
            else if (weight == o.weight) return 0;
            else return 1;
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        for (int i = 1; i < n + 1; i++)
            parent[i] = i;

        pq = new PriorityQueue<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            pq.offer(new Edge(u, v, weight));
        }

        int sum = 0;
        int size = 0;
        while (size < n - 2) {
            Edge edge = pq.poll();
            if (!isSameParent(edge.u, edge.v)) {
                sum += edge.weight;
                union(edge.u, edge.v);
                size++;
            }
        }

        bw.write(sum + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) parent[y] = x;
    }

    private static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    private static boolean isSameParent(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) return true;
        else return false;
    }
}
