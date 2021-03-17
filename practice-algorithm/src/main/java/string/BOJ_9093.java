package string;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_9093 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = Integer.parseInt(br.readLine());

            while (T-- > 0) {
                String[] word = br.readLine().split(" ");

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < word.length; i++) {
                    sb.append(reverse(word[i])).append(" ");
                }

                System.out.println(sb.toString());
            }
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    private static String reverse(String word) {
        StringBuilder sb = new StringBuilder();
        for (int i = word.length() - 1; i >= 0; i--) {
            sb.append(word.charAt(i));
        }
        return sb.toString();
    }
}
