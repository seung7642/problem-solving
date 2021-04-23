package dynamicProgramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BOJ_12852 {

    private static int N;
    private static int[] cache, parent;
    private static StringBuilder sb = new StringBuilder();
    private static Stack<Integer> st = new Stack<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cache = new int[1000001];
        parent = new int[1000001];

        bfs(N);
        sb.append(cache[1]).append("\n");
        findParent(1);
        while (!st.isEmpty()) {
            sb.append(st.pop()).append(" ");
        }
        System.out.println(sb.toString());
    }

    private static void findParent(int idx) {
        st.push(idx);
        if (idx == N) return;
        findParent(parent[idx]);
    }

    private static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty()) {
            int n = q.poll();

            if (n == 1) {
                break;
            }

            if (n % 3 == 0 && cache[n / 3] == 0) {
                cache[n / 3] = cache[n] + 1;
                parent[n / 3] = n;
                q.add(n / 3);
            }
            if (n % 2 == 0 && cache[n / 2] == 0) {
                cache[n / 2] = cache[n] + 1;
                parent[n / 2] = n;
                q.add(n / 2);
            }
            if (cache[n - 1] == 0) {
                cache[n - 1] = cache[n] + 1;
                parent[n - 1] = n;
                q.add(n - 1);
            }
        }
    }
}
