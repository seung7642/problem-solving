package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1240 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int T, N, M;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        String str, tmp, sub = "";
        int[] decimalNum = new int[8];
        String[] code = { "0001101", "0011001", "0010011", "0111101", "0100011"
                , "0110001", "0101111", "0111011", "0110111", "0001011" };

        for (int tc = 1; tc <= T; tc++) {
            int count = 0;
            boolean isFind = false;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            for (int i = 0; i < N; i++) {
                str = br.readLine();
                for (int j = M - 1; j >= 0 && !isFind; j--) {
                    if (str.charAt(j) == '1') {
                        sub = str.substring(j - 55, j + 1);
                        isFind = true;
                    }
                }
            }

            for (int i = 0; i < sub.length(); i += 7) {
                tmp = sub.substring(i, i + 7);
                for (int j = 0; j < code.length; j++) {
                    if (tmp.equals(code[j])) {
                        decimalNum[count++] = j;
                    }
                }
            }

            int answer = (decimalNum[0] + decimalNum[2] + decimalNum[4] + decimalNum[6]) * 3
                    + decimalNum[1] + decimalNum[3] + decimalNum[5] + decimalNum[7];
            if (answer % 10 == 0) {
                answer = 0;
                for (int i = 0; i < decimalNum.length; i++) {
                    answer += decimalNum[i];
                }
            } else {
                answer = 0;
            }
            System.out.println("#" + tc + " " + answer);
        }
    }
}
