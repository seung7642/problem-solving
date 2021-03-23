package string.regEx;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BOJ_2671 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(solve(br.readLine()));
    }

    private static String solve(String input) {
        Pattern pattern = Pattern.compile("((100+1+)|(01))+");
        Matcher matcher = pattern.matcher(input);

        return matcher.matches() ? "SUBMARINE" : "NOISE";
    }
}
