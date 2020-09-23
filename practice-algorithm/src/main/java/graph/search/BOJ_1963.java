package graph.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1963 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int T;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            System.out.println(bfs(A, B));
        }

        br.close();
    }

    private static boolean[] getPrimeNumber() {
        boolean[] prime = new boolean[10000];

        for (int i = 2; i < 10000; i++)
            prime[i] = true;

        for (int i = 2; i * i < 10000; i++) {
            if (prime[i]) {
                for (int j = i * i; j < 10000; j += i)
                    prime[j] = false;
            }
        }

        return prime;
    }

    private static String bfs(int a, int b) {
        boolean[] prime = getPrimeNumber();
        int[] check = new int[10000];
        Queue<Integer> q = new LinkedList<>();
        q.add(a);
        check[a] = 1;

        while (!q.isEmpty()) {
            int now = q.poll();
            if (now == b) return String.valueOf(check[now] - 1);

            for (int i = 0; i < 4; i++) {
                for (int j = (i == 0 ? 1 : 0); j <= 9; j++) {
                    int next = getNextValue(now, i, j);
                    if (prime[next] && check[next] == 0) {
                        check[next] = check[now] + 1;
                        q.add(next);
                    }
                }
            }
        }

        return "Impossible";
    }

    // now 값의 idx번 째 자리 값을 val만큼 증가시킨다.
    private static int getNextValue(int now, int idx, int val) {
        StringBuffer sb = new StringBuffer(String.valueOf(now));
        sb.setCharAt(idx, (char)(val + '0'));
        return Integer.parseInt(sb.toString());
    }
}
