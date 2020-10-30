package slidingWindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_2075 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            pq.add(Integer.parseInt(st.nextToken()));

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());

                // 우선순위 큐의 최우선 값이 num보다 작다면 교체.
                if (pq.peek() < num) {
                    pq.poll();
                    pq.add(num);
                }
            }
        }

        System.out.println(pq.peek());
        br.close();
    }
}
