package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2522 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        int k = N - 1;
        for (int i = 0; i < 2 * N - 1; i++) {
            for (int j = Math.abs(k); j > 0; j--) {
                System.out.print(" ");
            }

            for (int j = Math.abs(k); j < N; j++) {
                System.out.print("*");
            }
            System.out.println();
            k--;
        }
    }
}
