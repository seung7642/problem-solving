package blindRecruitment_2020;

public class A {

    public static void main(String[] args) {
        String s = "abcabcabcabcdededededede";
        int result = solution(s); // 답: 14
        System.out.println(result);

        s = "xababcdcdababcdcd";
        result = solution(s); // 답: 17
        System.out.println(result);
    }

    private static int len;

    public static int solution(String s) {
        len = s.length();
        int min = len;
        for (int i = 1; i <= len / 2; i++) {
            int compressLen = compress(s, i).length();
            min = Math.min(min, compressLen);
        }
        return min;
    }

    private static String compress(String s, int k) {
        StringBuilder compressStr = new StringBuilder();
        String pattern = "";
        String nowStr = "";
        int cnt = 1;
        for (int i = 0; i <= len + k; i += k) {
            if (i >= len) {
                nowStr = "";
            } else if (i + k > len) {
                nowStr = s.substring(i);
            } else {
                nowStr = s.substring(i, i + k);
            }

            if (i != 0) {
                if (nowStr.equals(pattern)) {
                    cnt++;
                } else if (cnt >= 2) {
                    compressStr.append(cnt).append(pattern);
                    cnt = 1;
                } else {
                    compressStr.append(pattern);
                }
            }
            pattern = nowStr;
        }
        return compressStr.toString();
    }
}
