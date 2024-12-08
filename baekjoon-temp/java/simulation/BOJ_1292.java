package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1292 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int s, e, result;
    private static int[] arr = new int[1001];

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(in.readLine());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        int val = 1, cnt = 0;
        for (int i = 1; i <= 1000; i++) {
            arr[i] = val;
            if (++cnt == val) {
                val += 1;
                cnt = 0;
            }
        }

        result = 0;
        for (int i = s; i <= e; i++) {
            result += arr[i];
        }
        System.out.println(result);
    }
}
