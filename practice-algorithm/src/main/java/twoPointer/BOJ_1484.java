package twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_1484 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int G;

    public static void main(String[] args) throws IOException {
        G = Integer.parseInt(br.readLine());

        int[] arr = twoPointers();

        if (arr.length == 0) {
            System.out.println("-1");
        } else {
            for (int i = 0; i < arr.length; i++) {
                System.out.println(arr[i]);
            }
        }

        br.close();
    }

    private static int[] twoPointers() {
        List<Integer> list = new ArrayList<>();
        int left = 1, right = 1;

        while (right <= 50001) {
            if (right * right - left * left <= G) {
                if (right * right - left * left == G) list.add(right);
                right++;
            } else {
                left++;
            }
        }
        return list.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
