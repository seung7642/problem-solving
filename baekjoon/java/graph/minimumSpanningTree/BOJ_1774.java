package graph.minimumSpanningTree;

import java.io.*;
import java.util.*;

public class BOJ_1774 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static PriorityQueue<Edge> pq;
    private static List<Point> list;
    private static int[] parents;

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge> {
        int u, v;
        double weight;

        public Edge(int u, int v, double weight) {
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
        int V = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken()); // 이미 연결된 간선의 수 (고정 간선)

        list = new ArrayList<>();
        parents = new int[V];
        Arrays.fill(parents, -1);


        for (int i = 0; i < V; i++) { // 행성들의 좌표
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.add(new Point(x, y));
        }

        for (int i = 0; i < M; i++) { // 비용과 상관없이 고정된 간선
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            union(u - 1, v - 1);
        }

        pq = new PriorityQueue<>();
        for (int i = 0; i < V - 1; i++) {
            for (int j = i + 1; j < V; j++) {
                double weight = Math.sqrt(Math.pow(list.get(i).x - list.get(j).x, 2)
                        + Math.pow(list.get(i).y - list.get(j).y, 2));
                pq.offer(new Edge(i, j, weight));
            }
        }

        double sum = 0;
        int size = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (union(edge.u, edge.v)) {
                sum += edge.weight;
                size++;
            }
            if (size == V - 1) break;
        }

        bw.write(String.format("%.2f", sum) + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean union(int v1, int v2) {
        v1 = find(v1);
        v2 = find(v2);
        if (v1 != v2) {
            parents[v2] = v1;
            return true;
        }
        return false;
    }

    private static int find(int v) {
        if (parents[v] < 0) return v;
        return parents[v] = find(parents[v]);
    }
}
