package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1240 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int T, N, M;
    private static String[] rates = {"3211", "2221", "2122", "1411", "1132"
            , "1231", "1114", "1312", "1213", "3112"}; // 각 비율을 저장한다. ex) "3211" --> 3:2:1:1 (가장 우측이 1로 시작.)
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            String subStr = "";
            boolean isFind = false;
            for (int i = 0; i < N; i++) {
                String str = br.readLine();
                for (int j = M - 1; j >= 0 && !isFind; j--) {
                    if (str.charAt(j) == '1') {
                        subStr = str.substring(j - 55, j + 1);
                        isFind = true;
                    }
                }
            }

            String[] codes = getCodeArray(1);
            int[] resultArr = new int[10];
            for (int i = 0, idx = 0; i < subStr.length(); i += 7) {
                String compareStr = subStr.substring(i, i + 7);
                for (int j = 0; j < codes.length; j++) {
                    if (compareStr.equals(codes[j])) {
                        resultArr[idx++] = j;
                    }
                }
            }

            int checkVal = (resultArr[0] + resultArr[2] + resultArr[4] + resultArr[6]) * 3
                    + resultArr[1] + resultArr[3] + resultArr[5] + resultArr[7];

            int ans = 0;
            if (checkVal % 10 == 0) { // 최종 값이 10의 배수라면 정상적인 암호코드.
                for (int num : resultArr) {
                    ans += num;
                }
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }

        System.out.println(sb);
    }

    private static String[] getCodeArray(int mul) {
        String[] codes = new String[10];
        for (int i = 0; i < 10; i++) {
            String rate = rates[i];
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 4; j++) {
                // i가 홀수면 1, 짝수면 0
                int num = Character.digit(rate.charAt(j), 10);
                for (int k = 0; k < num * mul; k++) {
                    sb.append(j % 2 == 0 ? 0 : 1);
                }
            }

            codes[i] = sb.toString();
        }

        return codes;
    }
}
