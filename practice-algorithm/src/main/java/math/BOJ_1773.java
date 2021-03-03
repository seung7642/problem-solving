package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1773 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            int[] arr = new int[N];

            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(br.readLine());
            }

            int count = solve(N, C, arr);
            System.out.println(count);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    private static int solve(int N, int C, int[] arr) {
        int count = 0;
        for (int i = 1; i <= C; i++) {
            for (int item : arr) {
                if (i % item == 0) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }
}
