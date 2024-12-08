package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10775 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int G, P;
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        G = Integer.parseInt(in.readLine());
        P = Integer.parseInt(in.readLine());
        parent = new int[G + 1];
        for (int i = 1; i <= G; i++) {
            parent[i] = i;
        }

        int ans = 0;
        for (int i = 0; i < P; i++) {
            int g = Integer.parseInt(in.readLine());
            int emptyGate = find(g);
            if (emptyGate == 0) break;
            ans++;
            union(emptyGate - 1, emptyGate);
        }
        System.out.println(ans);
    }

    private static int find(int v) {
        if (parent[v] == v) return v;
        return parent[v] = find(parent[v]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        parent[b] = a;
    }
}
