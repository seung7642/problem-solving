package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_10973 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        if (prevPermutation()) {
            for (int e : nums) { System.out.print(e + " "); }
        } else {
            System.out.println(-1);
        }
    }

    private static boolean prevPermutation() {
        int i = nums.length - 1;
        while (i > 0 && nums[i - 1] < nums[i]) { i--; } // 끝을 기준으로 내림차순이 꺾이는 지점을 찾는다.
        if (i == 0) { return false; }

        int j = nums.length - 1;
        while (j >= 0 && nums[i - 1] < nums[j]) { j--; }

        swap(i - 1, j);
        j = nums.length - 1;
        while (i < j) { swap(i++, j--); }
        return true;
    }

    private static void swap(int idx1, int idx2) {
        int tmp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = tmp;
    }
}
