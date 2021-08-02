package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_4796 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int L, P, V, T = 1;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            P = Integer.parseInt(st.nextToken());
            V = Integer.parseInt(st.nextToken());
            if (L == 0 && P == 0 && V == 0) break;

            sb.append("Case ").append(T++).append(": ")
                    .append((V / P) * L + Math.min(L, V % P)).append("\n");

        }

        System.out.println(sb);
    }
}
