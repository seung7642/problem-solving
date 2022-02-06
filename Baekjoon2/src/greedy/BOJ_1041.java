package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1041 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static long answer;
    private static int[] dice = new int[6];
    private static long[] num = new long[4];
    private static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        // 3면이 보이는 주사위, 2면이 보이는 주사위, 1면이 보이는 주사위로 나눌 수 있습니다.
        // 3면이 보이는 주사위의 갯수: 4개 (고정)
        // 2면이 보이는 주사위의 갯수: 4 + 8*(N-2) 개
        // 1면이 보이는 주사위의 갯수: 5*(N-2)^2 + 4*(N-2) 개

        num[1] = 5L * (N - 2) * (N - 2) + 4L * (N - 2);
        num[2] = 4 + 8L * (N - 2);
        num[3] = 4;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 6; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }

        if (N == 1) {
            Arrays.sort(dice);
            for (int i = 0; i < 5; i++) {
                answer += dice[i];
            }
        } else {
            long min = dice[0];
            for (int i = 1; i < 6; i++) {
                min = Math.min(min, dice[i]);
            }
            answer += num[1] * min;

            min = Long.MAX_VALUE;
            for (int i = 0; i < 6; i++) {
                for (int j = i + 1; j < 6; j++) {
                    if (j + i != 5) {
                        min = Math.min(min, dice[i] + dice[j]);
                    }
                }
            }
            answer += num[2] * min;

            int sum = 0;
            for (int i = 0; i < 3; i++) {
                sum += Math.min(dice[i], dice[5 - i]);
            }
            answer += num[3] * sum;
        }

        System.out.println(answer);
    }
}
