package programmingCompetition.codeforces.round710;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A {

    private static long row, col, x;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            row = Long.parseLong(st.nextToken());
            col = Long.parseLong(st.nextToken());
            x = Long.parseLong(st.nextToken());

            long colCnt;
            if (x % row == 0) {
                colCnt = (x / row);
            } else {
                colCnt = (x / row) + 1;
            }

            long rowCnt = x - (row * (colCnt - 1));
            long result = (col * rowCnt) - (col - colCnt);
            sb.append(result).append("\n");
        }

        System.out.println(sb.toString());
    }
}
