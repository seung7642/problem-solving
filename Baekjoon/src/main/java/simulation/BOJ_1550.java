package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1550 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String str = in.readLine();
        int len = str.length(), result = 0;
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            result += Character.digit(c, 16) * Math.pow(16, len - i - 1);
        }

        System.out.println(result);
    }
}
