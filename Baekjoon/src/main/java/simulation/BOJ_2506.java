package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2506 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int N, result;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(in.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(in.readLine());
        int prev = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (arr[i] == 1) {
                result += ++prev;
            } else {
                prev = 0;
            }
        }

        System.out.println(result);
    }
}
