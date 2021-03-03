package dynamicProgramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class BOJ_14003 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder sb = new StringBuilder();
            int N = Integer.parseInt(br.readLine());
            int[] arr = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int[] indexArr = new int[N];
            List<Integer> list = new ArrayList<>();
            list.add(Integer.MIN_VALUE);

            binarySearch(N, arr, indexArr, list);
            sb.append(list.size() - 1).append("\n");

            Stack<Integer> stack = new Stack<>();
            backTrack(N, arr, indexArr, list, stack);

            while (!stack.isEmpty()) {
                sb.append(stack.pop()).append(" ");
            }

            System.out.println(sb.toString());
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    private static void binarySearch(int N, int[] arr, int[] indexArr, List<Integer> list) {
        for (int i = 0; i < N; i++) {
            int num = arr[i];
            if (num > list.get(list.size() - 1)) {
                list.add(num);
                indexArr[i] = list.size() - 1;
            } else {
                int left = 1;
                int right = list.size() - 1;

                while (left < right) {
                    int mid = (left + right) >> 1;
                    if (num <= list.get(mid)) {
                        right = mid;
                    } else {
                        left = mid + 1;
                    }
                }

                list.set(right, num);
                indexArr[i] = right; // right 위치가 곧 해당 인덱스가 만들 수 있는 수열의 최장 길이다.
            }
        }
    }

    private static void backTrack(int N, int[] arr, int[] indexArr, List<Integer> list, Stack<Integer> stack) {
        int idx = list.size() - 1;
        for (int i = N - 1; i >= 0; i--) {
            if (indexArr[i] == idx) {
                idx--;
                stack.push(arr[i]);
            }
        }
    }
}
