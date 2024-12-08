package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11005 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int N, B;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        int pow = 0, tmp = N;
        // 1. 최고차항 구하기.
        while (true) {
            tmp /= B;
            if (tmp == 0) break;
            pow++;
        }

        StringBuilder sb = new StringBuilder();
        tmp = N;
        while (true) {
            if (pow == -1) break;

            int quotient = (int) (tmp / Math.pow(B, pow)); // 최고차항으로 나눈 몫
            tmp %= Math.pow(B, pow);                       // 최고차항으로 나눈 나머지

            char ch;
            if (quotient >= 10) ch = (char) (quotient + 55);
            else ch = (char) (quotient + '0');
            sb.append(ch);

            pow--;
        }

        System.out.println(sb);
    }
}
