package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// counting sort
public class SWEA_1221 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int T, N;
    private static String[] num = {"ZRO", "ONE", "TWO", "THR", "FOR", "FIV", "SIX", "SVN", "EGT", "NIN"};
    private static int[] cnt;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(in.readLine());
        for (int tc = 1; tc <= T; tc++) {
            cnt = new int[10];
            String[] strArr = in.readLine().split(" ");
            N = Integer.parseInt(strArr[1]);
            String[] inputArr = in.readLine().split(" ");
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < 10; j++) {
                    if (num[j].equals(inputArr[i])) {
                        cnt[j]++;
                        break;
                    }
                }
            }

            sb.append(strArr[0]).append("\n");
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < cnt[i]; j++) {
                    sb.append(num[i]).append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
