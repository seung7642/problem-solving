package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1439 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static String str;
    private static int zeroCnt, oneCnt;

    public static void main(String[] args) throws IOException {
        str = br.readLine();
        boolean isZero = false, isOne = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '0') {
                if (isOne) {
                    isOne = false;
                    oneCnt++;
                }
                isZero = true;
            } else {
                if (isZero) {
                    isZero = false;
                    zeroCnt++;
                }
                isOne = true;
            }
        }

        if (isZero) zeroCnt++;
        if (isOne) oneCnt++;

        int result = Math.min(zeroCnt, oneCnt);
        System.out.println(result);
    }
}
