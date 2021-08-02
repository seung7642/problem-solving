package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1449 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, L;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(arr);

        double tapeRange = (arr[0] - 0.5 + L);
        int cnt = 1;
        for (int i = 1; i < N; i++) {
            if (tapeRange < arr[i]) {
                tapeRange = (arr[i] - 0.5 + L);
                cnt++;
            }
        }

        System.out.println(cnt);
    }
}
