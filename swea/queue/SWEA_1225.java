package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1225 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int T;
    private static Queue<Integer> q;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        while (T++ < 10) {
            br.readLine();
            q = new LinkedList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 8; i++) {
                q.add(Integer.parseInt(st.nextToken()));
            }

            int increaseNum = 0;
            while (true) {
                increaseNum = (increaseNum % 5) + 1;
                int val = q.poll();
                val = (Math.max(val - increaseNum, 0));
                q.add(val);
                if (val == 0) break;
            }

            sb.append("#").append(T).append(" ");
            while (!q.isEmpty()) {
                sb.append(q.poll()).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
