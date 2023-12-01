package string;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_2744 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String str = br.readLine();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);

                if (Character.isLowerCase(c)) {
                    sb.append(Character.toUpperCase(c));
                } else if (Character.isUpperCase(c)) {
                    sb.append(Character.toLowerCase(c));
                }
            }

            System.out.println(sb.toString());
        } catch (Exception ex) {
            ex.getMessage();
        }
    }
}
