package graph.minimumSpanningTree;

import java.io.*;
import java.util.*;

public class BOJ_6497 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static PriorityQueue<Edge> pq;
    private static int[] parents;

    static class Edge implements Comparable<Edge> {
        int v1, v2, weight;

        public Edge(int v1, int v2, int weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken()); // 집의 수 (정점)
            int E = Integer.parseInt(st.nextToken()); // 길의 수 (간선)
            if (V == 0 && E == 0) break;

            pq = new PriorityQueue<>();
            parents = new int[V];
            Arrays.fill(parents, -1);

            int totalWeight = 0;
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                totalWeight += weight;

                pq.offer(new Edge(u, v, weight));
            }

            int sum = 0;
            int size = 0;
            while (size < V - 1) {
                Edge edge = pq.poll();
                if (find(edge.v1) != find(edge.v2)) {
                    sum += edge.weight;
                    union(edge.v1, edge.v2);
                    size++;
                }
            }

            bw.write(totalWeight - sum + "\n");
        }

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
