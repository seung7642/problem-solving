package string;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1212 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder sb = new StringBuilder();
            String str = br.readLine();

            for (int i = 0; i < str.length(); i++) {
                int val = Character.digit(str.charAt(i), 10);

                int a = 4;
                while (a > 0) {
                    if (val / a == 1) {
                        val %= a;
                        sb.append("1");
                    } else {
                        sb.append("0");
                    }
                    a /= 2;
                }
            }

            for (int i = 0; i < 2; i++) {
                if (sb.charAt(0) == '0') {
                    sb.deleteCharAt(0);
                }
            }

            System.out.println(sb.toString());
        } catch (Exception ex) {
            ex.getMessage();
        }
    }
}
