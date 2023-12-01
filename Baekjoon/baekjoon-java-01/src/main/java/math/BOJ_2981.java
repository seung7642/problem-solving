package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// 최대공약수 (유클리드 호제법)
public class BOJ_2981 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(br.readLine());

            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(br.readLine());
            }
            Arrays.sort(arr);

            int gcdVal = arr[1] - arr[0];

            for (int i = 2; i < n; i++) {
                int dist = arr[i] - arr[i - 1];
                gcdVal = gcd(gcdVal, dist);
            }

            divide(gcdVal);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    private static int gcd(int a, int b) {
        return b > 0 ? gcd(b, a % b) : a;
    }

    private static void divide(int num) {
        List<Integer> list = new LinkedList<>();
        list.add(num);

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                if (i == num / i) {
                    list.add(i);
                } else {
                    list.add(i);
                    list.add(num / i);
                }
            }
        }

        Collections.sort(list);

        StringBuilder sb = new StringBuilder();
        for (int item : list) {
            sb.append(item).append(" ");
        }

        System.out.println(sb.toString());
    }
}
