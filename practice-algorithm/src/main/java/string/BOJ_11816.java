package string;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_11816 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String str = br.readLine();

            int ans = 0;
            StringBuilder sb = new StringBuilder();
            if (str.charAt(0) == '0') {
                if (str.charAt(1) == 'x') {
                    for (int i = 2; i < str.length(); i++) {
                        sb.append(str.charAt(i));
                    }
                    ans = Integer.valueOf(sb.toString(), 16);
                } else {
                    for (int i = 1; i < str.length(); i++) {
                        sb.append(str.charAt(i));
                    }
                    ans = Integer.valueOf(sb.toString(), 8);
                }
            } else {
                ans = Integer.valueOf(str, 10);
            }

            System.out.println(ans);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }
}
