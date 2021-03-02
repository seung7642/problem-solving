package search.binarySearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ_12015 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            List<Integer> list = new ArrayList<>();
            list.add(0);

            for (int i = 0; i < N; i++) {
                int val = arr[i];

                if (val > list.get(list.size() - 1)) {
                    list.add(val);
                } else {
                    int left = 0;
                    int right = list.size() - 1;

                    while (left < right) {
                        int mid = (left + right) / 2;
                        if (list.get(mid) >= val) {
                            right = mid;
                        } else {
                            left = mid + 1;
                        }
                    }

                    list.set(right, val);
                }
            }

            System.out.println(list.size() - 1);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }
}
