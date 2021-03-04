package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10166 {

    private static boolean[][] check = new boolean[2001][2001];

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int d1 = Integer.parseInt(st.nextToken());
            int d2 = Integer.parseInt(st.nextToken());
            long ans = 0;

            for (int i = d1; i <= d2; i++) {
                for (int j = 1; j <= i; j++) {
                    int gcd = gcd(i, j);
                    if (check[i / gcd][j / gcd])
                        continue;

                    ans++;
                    check[i / gcd][j / gcd] = true;
                }
            }

            System.out.println(ans);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
