package slidingWindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3078 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, K;
    private static Queue<Integer>[] q = new Queue[21];

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 21; i++) {
            q[i] = new LinkedList<>();
        }

        long count = 0;
        for (int i = 0; i < N; i++) {
            int len = br.readLine().trim().length();

            while (!q[len].isEmpty() && i - q[len].peek() > K) {
                q[len].poll();
            }

            count += q[len].size();
            q[len].add(i);
        }

        System.out.println(count);
        br.close();
    }
}
