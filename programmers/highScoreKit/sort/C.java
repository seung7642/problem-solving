package highScoreKit.sort;

import java.util.Arrays;

public class C {

    public static void main(String[] args) {
        int[] citations;
        citations = new int[]{3, 0, 6, 1, 5};
        System.out.println(solution(citations)); // 3
    }

    public static int solution(int[] citations) {
        int answer = 0;

        Arrays.sort(citations);
        int len = citations.length;
        for (int i = 0; i < len; i++) {
            int citation = citations[i];
            int h = len - i;
            if (citation >= h) {
                answer = h;
                break;
            }
        }

        return answer;
    }
}
