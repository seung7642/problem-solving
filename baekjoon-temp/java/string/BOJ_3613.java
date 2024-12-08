package string;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_3613 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String key = br.readLine();
        int type = getType(key);
        System.out.println(solve(key, type));
    }

    private static int getType(String str) {
        // 잘못된 변수명인지 판단하기 위해 확인하는 사항
        //   1. 가장 첫 문자가 영어 소문자인지
        //   2. 언더바(_)가 연속해서 두 번 나오는지
        //   3. 마지막 문자가 언더바(_)인지
        //   4. 언더바(_) 다음 문자가 대문자인지

        if (!Character.isLowerCase(str.charAt(0))) return -1; // 1번 사항
        if (!Character.isAlphabetic(str.charAt(str.length() - 1))) return -1; // 3번 사항

        if (str.contains("_")) {
            int continuoutUnderbarCnt = 0;
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (Character.isUpperCase(c)) return -1; // 4번 사항
                if (c == '_') {
                    continuoutUnderbarCnt++;
                    if (continuoutUnderbarCnt == 2) return -1; // 2번 사항
                } else {
                    continuoutUnderbarCnt = 0;
                }
            }
            return 1; // C++ 변수명
        } else {
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (!Character.isAlphabetic(c)) return -1;
            }
            return 2; // java 변수명
        }
    }

    private static String solve(String key, int type) {
        if (type == 1) return convertCppToJava(key);
        else if (type == 2) return convertJavaToCpp(key);

        return "Error!";
    }

    private static String convertJavaToCpp(String key) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append("_").append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private static String convertCppToJava(String key) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (c == '_') {
                sb.append(Character.toUpperCase(key.charAt(++i)));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
