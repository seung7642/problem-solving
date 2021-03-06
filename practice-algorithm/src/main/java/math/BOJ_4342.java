package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_4342 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder sb = new StringBuilder();

            while (true) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (a == 0 && b == 0) break;

                sb.append(gcd(a, b) ? "A wins\n" : "B wins\n");
            }

            System.out.println(sb.toString());
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    private static boolean gcd(int a, int b) {
        // 파라미터로 들어오는 수는 무조건 a > b로 맞춰준다.
        if (a < b) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        // 첫 번째 케이스: a % b == 0
        // 선공이 바로 0으로 만들어 이길 수 있다.
        if (a % b == 0) {
            return true;
        }

        // 두 번째 케이스: (a - b) < b
        // 두 번째 케이스는 1, 3번째 케이스가 될 수 없다. 따라서 게임이 끝날 때까지 2번째 케이스만 반복된다.
        if (a - b < b) {
            return !gcd(a - b, b);
        }

        // 세 번째 케이스: (a - b) > b
        // 이 경우 2번째 케이스로 만들어주기 위한 경우의 수가 여러 개 존재하는데,
        // 그 중 자기가 이기는 케이스가 무조건 존재한다. 따라서, 무조건 선공이 이긴다.
        return true;
    }
}
