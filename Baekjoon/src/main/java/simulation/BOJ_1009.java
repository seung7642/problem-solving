package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1009 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int T, a, b, result;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            result = 1;

            int k = 0;
            if (a % 10 == 0 || a % 10 == 1 || a % 10 == 5 || a % 10 == 6) {
                result = (a % 10);
            } else if (a % 10 == 4 || a % 10 == 9) {
                k = (b % 2);
                if (k == 0) { k = 2; }
            } else {
                k = (b % 4);
                if (k == 0) k = 4;
            }

            for (int i = 0; i < k; i++) { result = (result * a) % 10; }
            if (result == 0) { result = 10; }
            System.out.println(result);
        }
    }
}
