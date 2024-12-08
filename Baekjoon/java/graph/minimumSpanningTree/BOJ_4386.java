package graph.minimumSpanningTree;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_4386 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int[] parent;
    private static List<Edge> list = new ArrayList<>();

    static class Point {
        int num;
        double x, y;

        public Point(int num, double x, double y) {
            this.num = num;
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
            else return 1;
        }
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        Point[] points = new Point[n];

        parent = new int[n];
        for (int i = 0; i < n; i++)
            parent[i] = i;

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());

            points[i] = new Point(i, x, y);
        }

        // 모든 별들 간의 간선과 가중치 정보를 list에 담는다.
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double weight = distance(points[i], points[j]);
                list.add(new Edge(points[i].num, points[j].num, weight));
            }
        }

        Collections.sort(list);

        double sum = 0;

        // 크루스칼 알고리즘
        for (Edge edge : list) {
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

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) parent[y] = x;
    }

    public static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    public static boolean isSameParent(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) return true;
        else return false;
    }

    private static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }
}
