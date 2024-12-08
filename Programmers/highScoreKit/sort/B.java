package highScoreKit.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class B {

    public static void main(String[] args) {
        int[] numbers;

        numbers = new int[]{6, 10, 2};
        System.out.println(solution(numbers));// 답: "6210"

        numbers = new int[]{3, 30, 34, 5, 9};
        System.out.println(solution(numbers));// 답: "9534330"
    }

    public static String solution(int[] numbers) {
        StringBuffer answer = new StringBuffer();
        String[] strArr = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            int number = numbers[i];
            strArr[i] = String.valueOf(number);
        }

        Arrays.sort(strArr, (a, b) -> (b + a).compareTo(a + b));
        if (strArr[0].equals("0")) {
            return "0";
        }

        for (int i = 0; i < strArr.length; i++) {
            String s = strArr[i];
            answer.append(s);
        }

        return answer.toString();
    }
}
