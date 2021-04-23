package dynamicProgramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2502 {

    private static int D, K;
    private static int[] A1, A2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        D = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (D == 3) {
            sb.append("1").append("\n").append(K - 1);
        } else {
            A1 = new int[D + 1];
            A2 = new int[D + 1];

            A1[3] = A2[3] = 1;
            A1[4] = 1;
            A2[4] = 2;
            for (int i = 5; i <= D; i++) {
                A1[i] = A1[i - 1] + A1[i - 2];
                A2[i] = A2[i - 1] + A2[i - 2];
            }

            for (int i = 1; i <= K; i++) {
                int val = K - A1[D] * i;
                if (val % A2[D] == 0) {
                    sb.append(i).append("\n").append(val / A2[D]);
                    break;
                }
            }
        }

        System.out.println(sb.toString());
    }
}
