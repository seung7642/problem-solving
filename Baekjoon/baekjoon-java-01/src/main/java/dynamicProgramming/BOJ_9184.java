package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_9184 {

    private static int d[][][] = new int[21][21][21];

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            StringBuilder sb = new StringBuilder();

            while (!"-1 -1 -1".equals((line = br.readLine()))) {
                int[] nums = Arrays.stream(line.split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();

                sb.append("w(" + nums[0] + ", " + nums[1] + ", " + nums[2] + ") = ")
                        .append(w(nums[0], nums[1], nums[2]))
                        .append("\n");
            }

            System.out.println(sb);
        }
    }

    private static int w(int a, int b, int c) {
        if (isSafeRange(a, b, c) && d[a][b][c] != 0)
            return d[a][b][c];

        if (a <= 0 || b <= 0 || c <= 0)
            return 1;

        if (a > 20 || b > 20 || c > 20)
            return d[20][20][20] = w(20, 20, 20);

        if (a < b && b < c)
            return d[a][b][c] = w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c);

        return d[a][b][c] = w(a - 1, b, c) + w(a - 1, b - 1, c) +
                    w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1);

    }

    private static boolean isSafeRange(int a, int b, int c) {
        return (0 <= a && a <= 20) && (0 <= b && b <= 20) && (0 <= c && c <= 20);
    }
}
