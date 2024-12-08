package highScoreKit.greedy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class A {

    public static void main(String[] args) {
        int n;
        int[] lost;
        int[] reserve;

        n = 5;
        lost = new int[]{2, 4};
        reserve = new int[]{1, 3, 5};
        System.out.println(solution(n, lost, reserve)); // 답: 5

        n = 5;
        lost = new int[]{2, 4};
        reserve = new int[]{3};
        System.out.println(solution(n, lost, reserve)); // 답: 4

        n = 3;
        lost = new int[]{3};
        reserve = new int[]{1};
        System.out.println(solution(n, lost, reserve)); // 답: 2
    }

    public static int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;

        Arrays.sort(lost);
        Set<Integer> set = new HashSet<>();
        boolean[] check = new boolean[31];
        for (int n1 : lost) {
            for (int n2 : reserve) {
                if (n1 == n2) {
                    check[n2] = false;
                    set.add(n1);
                } else if (!set.contains(n2)) {
                    check[n2] = true;
                }
            }
        }

        for (int num : lost) {
            if (set.contains(num)) continue;
            if (num - 1 > 0) {
                if (check[num - 1]) {
                    check[num - 1] = false;
                    set.add(num - 1);
                    continue;
                }
            }
            if (num + 1 <= 30) {
                if (check[num + 1]) {
                    check[num + 1] = false;
                    set.add(num + 1);
                }
            }
        }

        answer = n - (lost.length - set.size());
        return answer;
    }
}
