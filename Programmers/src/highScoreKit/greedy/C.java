package highScoreKit.greedy;

public class C {

    public static void main(String[] args) {
        String number;
        int k;

        number = "1924";
        k = 2;
        System.out.println(solution(number, k)); // 답: "94"

        number = "1231234";
        k = 3;
        System.out.println(solution(number, k)); // 답: "3234"

        number = "4177252841";
        k = 4;
        System.out.println(solution(number, k)); // 답: "775841"
    }

    public static String solution(String number, int k) {
        StringBuffer sb = new StringBuffer();
        int idx = 0;
        int max = 0;
        for (int i = 0; i < number.length() - k; i++) {
            max = 0;
            for (int j = idx; j <= k + i; j++) {
                int nextVal = number.charAt(j) - '0';
                if (max < nextVal) {
                    max = nextVal;
                    idx = j + 1;
                }
            }
            sb.append(max);
        }
        return sb.toString();
    }
}
