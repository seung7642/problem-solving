package graph.minimumSpanningTree;

import java.io.*;
import java.util.*;

public class BOJ_2887 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int[] parent;
    private static List<Edge> edgeList;
    private static List<Point> list;

    static class Point {
        int x, y, z, id;

        public Point(int x, int y, int z, int id) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.id = id;
        }
    }

    static class Edge implements Comparable<Edge> {
        int u, v;
        int weight;

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
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        parent = new int[n];
        for (int i = 0; i < n; i++)
            parent[i] = i;

        list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            list.add(new Point(x, y, z, i));
        }

        edgeList = new ArrayList<>();

        Collections.sort(list, (p1, p2) -> p1.x - p2.x);
        for (int i = 0; i < n - 1; i++) {
            int weight = Math.abs(list.get(i).x - list.get(i + 1).x);
            edgeList.add(new Edge(list.get(i).id, list.get(i + 1).id, weight));
        }

        Collections.sort(list, (p1, p2) -> p1.y - p2.y);
        for (int i = 0; i < n - 1; i++) {
            int weight = Math.abs(list.get(i).y - list.get(i + 1).y);
            edgeList.add(new Edge(list.get(i).id, list.get(i + 1).id, weight));
        }

        Collections.sort(list, (p1, p2) -> p1.z - p2.z);
        for (int i = 0; i < n - 1; i++) {
            int weight = Math.abs(list.get(i).z - list.get(i + 1).z);
            edgeList.add(new Edge(list.get(i).id, list.get(i + 1).id, weight));
        }

        Collections.sort(edgeList);
        int sum = 0;
        for (Edge edge : edgeList) {
            if (!isSameParent(edge.u, edge.v)) {
                sum += edge.weight;
                union(edge.u, edge.v);
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
        if (x < y) parent[y] = x;
        else parent[x] = y;
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
