package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * Greedy 는 간단하고 빠르다, 또한 위험하다.
 * 완전 탐색은 느리다, 하지만 반드시 답을 찾는다.
 * 		=> 시간을 개선하기위한 가지치기를 잘하면 (Bracktracking) 빠른 결과를 낼 수 있다.
 */

// 최대상금 - 백트래킹
public class SWEA_1244 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int result;
    static Set<String> set;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws NumberFormatException, IOException {
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String str = st.nextToken();
            int[] nums = new int[str.length()];
            for (int i = 0; i < str.length(); i++) {
                nums[i] = Character.digit(str.charAt(i), 10);
            }

            int N = Integer.parseInt(st.nextToken()); // 교환횟수
            
            set = new HashSet<>();	// 같은상태의 중복을 제거
            result = 0;
            find(nums, N);
            ans.append("#").append(tc).append(" ").append(result).append("\n");
        }

        System.out.println(ans);
    }

    public static void find(int[] nums, int N) {
        // nums 배열을 10진수 숫자로 만들기
        int val = 0;
        for (int i = 0; i < nums.length; i++) {
            val = val * 10 + nums[i];
        }

        if (set.contains("" + val + N)) return; // 검토 했던 작업이므로 리턴
        set.add("" + val + N); // 현재 상태 저장

        if (N == 0) { // base case(종료파트) --> 교환회수가 0이면 종료
            if (result < val) result = val;
            return;
        }

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                swap(nums, i, j);
                find(nums, N - 1);
                swap(nums, i, j);
            }
        }
    }

    private static void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
}