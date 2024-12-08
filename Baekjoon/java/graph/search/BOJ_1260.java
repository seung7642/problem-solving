package graph.search;

import java.util.*;

// 문제: DFS와 BFS
public class BOJ_1260 {

    private static int N, M, V;
    private static List<Integer>[] list;
    private static boolean[] check;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); M = sc.nextInt(); V = sc.nextInt();

        list = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++)
            list[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            list[u].add(v);
            list[v].add(u);
        }

        for (int i = 1; i < N + 1; i++)
            Collections.sort(list[i]);

        check = new boolean[N + 1];
        dfs(V);
        System.out.println();

        check = new boolean[N + 1];
        bfs(V);
        System.out.println();

        sc.close();
    }

    public static void dfs(int x) {
        if (check[x]) return;

        check[x] = true;
        System.out.print(x + " ");
        for (int y : list[x]) {
            if (!check[y]) dfs(y);
        }
    }

    public static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        check[start] = true;

        while (!queue.isEmpty()) {
            int x = queue.poll();
            System.out.print(x + " ");

            for (int y : list[x]) {
                if (!check[y]) {
                    check[y] = true;
                    queue.add(y);
                }
            }
        }
    }
}
