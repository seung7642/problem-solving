package string;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_2754 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String str = br.readLine();
            double ans = getScore(str);

            System.out.println(ans);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    private static double getScore(String str) {
        char c1 = str.charAt(0);
        if (c1 == 'F') return 0.0;

        double result = 0;
        if (c1 == 'A') result = 4.0;
        else if (c1 == 'B') result = 3.0;
        else if (c1 == 'C') result = 2.0;
        else if (c1 == 'D') result = 1.0;

        char c2 = str.charAt(1);
        if (c2 == '+') result += 0.3;
        else if (c2 == '-') result -= 0.3;

        return result;
    }
}
