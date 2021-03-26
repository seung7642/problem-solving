package search.binarySearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ_12738 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            List<Integer> list = new ArrayList<>(); // 증가 수열을 저장할 리스트
            list.add(Integer.MIN_VALUE);

            for (int i = 0; i < N; i++) {
                int num = arr[i];

                // 확인하는 숫자가 증가수열의 마지막보다 큰 경우, 수열에 추가해준다.
                if (num > list.get(list.size() - 1)) {
                    list.add(num);
                } else {
                    int left = 1;
                    int right = list.size() - 1;

                    // 숫자의 적당한 위치를 찾아서 증가수열의 값을 갱신해준다.
                    while (left < right) {
                        int mid = (left + right) >> 1;

                        if (num <= list.get(mid)) right = mid;
                        else left = mid + 1;
                    }
                    list.set(right, num);
                }
            }

            System.out.println(list.size() - 1);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }
}
