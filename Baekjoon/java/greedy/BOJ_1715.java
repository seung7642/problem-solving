package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class BOJ_1715 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, ans;
    private static Queue<Integer> q = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            q.add(num);
        }

        while (q.size() >= 2) {
            int a = q.poll();
            int b = q.poll();
            ans += a + b;
            q.add(a + b);
        }

        System.out.println(ans);
    }
}
