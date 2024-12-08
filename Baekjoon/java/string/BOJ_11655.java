package string;

import java.io.*;

public class BOJ_11655 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String str = br.readLine();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isLowerCase(c)) { // 소문자
                if (c + 13 > 'z') {
                    sb.append( (char)(c - 13) );
                } else {
                    sb.append( (char)(c + 13) );
                }
            } else if (Character.isUpperCase(c)) { // 대문자
                if (c + 13 > 'Z') {
                    sb.append( (char)(c - 13) );
                } else {
                    sb.append( (char)(c + 13) );
                }
            } else {
                sb.append(c);
            }
        }

        System.out.println(sb);

        br.close();
    }
}
