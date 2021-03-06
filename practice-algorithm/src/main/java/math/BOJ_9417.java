package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_9417 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder sb = new StringBuilder();
            int T = Integer.parseInt(br.readLine());

            while (T-- > 0) {
                int[] arr = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();

                int maxGcd = -1;
                for (int i = 0; i < arr.length - 1; i++) {
                    for (int j = i + 1; j < arr.length; j++) {
                        maxGcd = Math.max(maxGcd, getGcd(arr[i], arr[j]));
                    }
                }
                sb.append(maxGcd).append("\n");
            }

            System.out.println(sb.toString());
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    private static int getGcd(int a, int b) {
        return b == 0 ? a : getGcd(b, a % b);
    }
}
