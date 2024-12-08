package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2576 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] arr = new int[7];

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 7; i++) { arr[i] = Integer.parseInt(br.readLine()); }

        Arrays.sort(arr);

        int sum = 0, minOdd = -1;
        for (int i = 6; i >= 0; i--) {
            if (arr[i] % 2 != 0) {
                sum += arr[i];
                minOdd = arr[i];
            }
        }

        if (sum == 0) { System.out.println(-1); }
        else { System.out.println(sum + "\n" + minOdd); }
    }
}
