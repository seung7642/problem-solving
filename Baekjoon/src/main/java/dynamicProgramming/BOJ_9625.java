package dynamicProgramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_9625 {

    private static int K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        int[] aArr = new int[45];
        int[] bArr = new int[45];
        aArr[0] = 0;
        aArr[1] = 1;
        bArr[0] = 1;
        bArr[1] = 1;
        for (int i = 2; i < 45; i++) {
            aArr[i] = aArr[i - 1] + aArr[i - 2];
            bArr[i] = bArr[i - 1] + bArr[i - 2];
        }

        System.out.println(aArr[K - 1] + " " + bArr[K - 1]);
    }
}
