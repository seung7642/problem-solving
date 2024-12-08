package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_1983_0 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int T, N, K;
    private static String[] grades = {"A+", "A0", "A-", "B+", "B0", "B-", "C+", "C0", "C-", "D0"};
    private static int[] rate = {35, 45, 20};
    private static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(in.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            int[] students = new int[N + 1];
            int standard = 0;
            for (int i = 1; i <= N; i++) {
                int score = 0;
                st = new StringTokenizer(in.readLine());
                for (int j = 0; j < 3; j++) {
                    score += Integer.parseInt(st.nextToken()) * rate[j];
                }
                students[i] = score;
                if (K == i) standard = score;
            }

            int greatCnt = 0;
            for (int i = 1; i <= N; i++) {
                if (students[i] > standard) greatCnt++;
            }

            String ans = grades[greatCnt / (N / 10)];
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }
}
