package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2004 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long n = Long.parseLong(st.nextToken());
            long m = Long.parseLong(st.nextToken());

            long fiveCnt, twoCnt;
            fiveCnt = getNumberCount(5, n);
            twoCnt = getNumberCount(2, n);

            fiveCnt -= getNumberCount(5, n - m);
            twoCnt -= getNumberCount(2, n - m);
            fiveCnt -= getNumberCount(5, m);
            twoCnt -= getNumberCount(2, m);

            System.out.println(Math.min(fiveCnt, twoCnt));
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    private static long getNumberCount(long num, long n) {
        long cnt = 0;

        for (long i = num; i <= n; i *= num) {
            cnt += n / i;
        }
        return cnt;
    }
}
