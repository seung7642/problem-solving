package twoPointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//  유사 팰린드롬 찾기. (문자 하나를 지웠을 때, 팰린드롬이라면 이를 유사 팰린드롬이라 한다.)
public class BOJ_17609 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            sb.append(palindrome(br.readLine())).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static int palindrome(String str) {
        int result = 0;
        int left = 0, right = str.length() - 1;

        while (left <= right) {
            if (str.charAt(left) == str.charAt(right)) {
                left++;
                right--;
            } else {
                int l = left;
                int r = right;

                l++;
                while (l <= r) {
                    if (str.charAt(l) == str.charAt(r)) {
                        l++;
                        r--;
                    } else {
                        result++;
                        break;
                    }
                }

                l = left;
                r = right;

                r--;
                while (l <= r) {
                    if (str.charAt(l) == str.charAt(r)) {
                        l++;
                        r--;
                    } else {
                        result++;
                        break;
                    }
                }

                return result;
            }
        }

        return result;
    }
}
