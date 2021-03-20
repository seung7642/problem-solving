package string;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_15904 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String ans = solution(str);
        System.out.println(ans);
    }

    private static String solution(String str) {
        char[] ucpc = {'U', 'C', 'P', 'C'};
        int idx = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i); // 단어의 맨 앞 글자만 대문자라는 가정하에.
            if (c == ucpc[idx]) {
                idx++;
            }

            if (idx == 4) {
                return "I love UCPC";
            }
        }

        return "I hate UCPC";
    }
}
