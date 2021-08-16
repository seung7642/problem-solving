package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10992 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(in.readLine());
        for (int y = 0; y < N - 1; y++) {
            for (int x = 0; x < N - 1 - y; x++) {
                System.out.print(" ");
            }
            System.out.print("*");

            for (int x = 0; x < y * 2 - 1; x++) {
                System.out.print(" ");
                if (x == y * 2 - 2) System.out.print("*");
            }
            System.out.println();
        }

        for (int x = 0; x < 2 * N - 1; x++) {
            System.out.print("*");
        }
    }
}
