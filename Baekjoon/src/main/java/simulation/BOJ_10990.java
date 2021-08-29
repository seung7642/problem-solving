package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10990 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(in.readLine());
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N - 1; j++)
                System.out.print(" ");
            System.out.print("*");

            if (i == 0) {
                System.out.println();
                continue;
            }

            for (int j = 0; j < 2 * i - 1; j++)
                System.out.print(" ");
            System.out.println("*");
        }
    }
}
