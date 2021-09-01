package mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1251 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int T, N;
    private static double E;
    private static Node[] nodes;
    private static int[] parent;
    private static Queue<Edge> q;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(in.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(in.readLine());
            nodes = new Node[N + 1];
            parent = new int[N];
            StringTokenizer st = new StringTokenizer(in.readLine());
            StringTokenizer st2 = new StringTokenizer(in.readLine());
            E = Double.parseDouble(in.readLine());
            for (int i = 0; i < N; i++) {
                parent[i] = i; // Make_Set 연산
                nodes[i] = new Node();
                nodes[i].x = Integer.parseInt(st.nextToken());
                nodes[i].y = Integer.parseInt(st2.nextToken());
            }

            q = new PriorityQueue<>();
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    double dist = Math.pow(nodes[i].x - nodes[j].x, 2) + Math.pow(nodes[i].y - nodes[j].y, 2);
                    q.add(new Edge(i, j, dist));
                }
            }

            double sum = 0;
            int edgeCount = 0;
            while (true) {
                if (edgeCount == N - 1) break;
                Edge edge = q.poll();
                if (find(edge.u) != find(edge.v)) {
                    union(edge.u, edge.v);
                    edgeCount++;
                    sum += E * edge.dist;
                }
            }
            long ans = Math.round(sum);
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static int find(int v) {
        if (parent[v] == v) return v;
        return find(parent[v]);
    }

    private static void union(int v1, int v2) {
        v1 = find(v1);
        v2 = find(v2);
        parent[v2] = v1;
    }

    private static class Edge implements Comparable<Edge> {
        int u, v;
        double dist;

        public Edge(int u, int v, double dist) {
            this.u = u;
            this.v = v;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return (int) (this.dist - o.dist);
        }
    }

    private static class Node {
        int x, y;
    }
}
