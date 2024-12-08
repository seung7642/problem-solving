package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1049 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] pack = new int[M];
        int[] unit = new int[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            pack[i] = Integer.parseInt(st.nextToken());
            unit[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(unit);
        Arrays.sort(pack);

        int answer = Math.min((N / 6 + 1) * pack[0],
                Math.min((N / 6) * pack[0] + (N % 6) * unit[0], N * unit[0]));
        System.out.println(answer);
    }
}
