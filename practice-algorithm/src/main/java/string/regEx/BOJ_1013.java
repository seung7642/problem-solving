package string.regEx;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BOJ_1013 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            String result = solve(br.readLine());
            sb.append(result).append("\n");
        }

        System.out.println(sb.toString());
    }

    // (100+1+ | 01)+
    private static String solve(String input) {
        Pattern pattern = Pattern.compile("((100+1+)|(01))+");
        Matcher matcher = pattern.matcher(input);

        return matcher.matches() ? "YES" : "NO";
    }
}
