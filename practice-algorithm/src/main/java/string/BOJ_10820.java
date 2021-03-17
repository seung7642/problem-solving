package string;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_10820 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder sb;
            String str;
            while ((str = br.readLine()).length() != 0) {
                int lowerCase = 0, upperCase = 0, digit = 0, space = 0;

                for (int i = 0; i < str.length(); i++) {
                    char c = str.charAt(i);
                    if (Character.isLowerCase(c)) {
                        lowerCase++;
                    } else if (Character.isUpperCase(c)) {
                        upperCase++;
                    } else if (Character.isDigit(c)) {
                        digit++;
                    } else if (Character.isSpaceChar(c)) {
                        space++;
                    }
                }

                sb = new StringBuilder();
                sb.append(lowerCase).append(" ")
                        .append(upperCase).append(" ")
                        .append(digit).append(" ")
                        .append(space).append("\n");

                System.out.print(sb.toString());
            }

        } catch (Exception ex) {
            ex.getMessage();
        }
    }
}
