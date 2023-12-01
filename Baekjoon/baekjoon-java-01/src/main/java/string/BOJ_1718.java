package string;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1718 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String plainText = br.readLine();
        String key = br.readLine();

        String ans = encrypt(plainText, key);
        System.out.println(ans);
    }

    private static String encrypt(String plainText, String key) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < plainText.length(); i++) {
            char c = plainText.charAt(i);

            int gap;
            if (Character.isSpaceChar(c)) {
                sb.append(" ");
            } else {
                gap = key.charAt(i % key.length()) - 'a' + 1; // a부터 1로 시작하기 때문에 +1
                char encryptText = (char) (c - gap < 'a' ? (c - gap + 26) : c - gap);
                sb.append(encryptText);
            }
        }
        return sb.toString();
    }
}
