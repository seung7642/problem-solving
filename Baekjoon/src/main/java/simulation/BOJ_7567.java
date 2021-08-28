package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_7567 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static String str;
    private static int result;

    public static void main(String[] args) throws IOException {
        str = in.readLine();
        char prev = str.charAt(0);
        result = 10;
        for (int i = 1; i < str.length(); i++) {
            char c = str.charAt(i);
            if (prev == c) {
                result += 5;
            } else {
                prev = c;
                result += 10;
            }
        }
        System.out.println(result);
    }
}
