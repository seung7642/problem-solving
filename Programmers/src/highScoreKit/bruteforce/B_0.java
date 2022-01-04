package highScoreKit.bruteforce;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class B_0 {

    public static void main(String[] args) {
        String numbers;

        numbers = "17";
        System.out.println(solution(numbers)); // 답: 3

        numbersSet = new HashSet<>();
        numbers = "011";
        System.out.println(solution(numbers)); // 답: 2
    }

    private static Set<Integer> numbersSet = new HashSet<>();
    private static int N;
    private static String numbersCopy;
    private static boolean[] visited;

    public static int solution(String numbers) {
        N = numbers.length();
        visited = new boolean[N];
        numbersCopy = numbers;
        comb(0, "");

        int count = 0;
        Iterator<Integer> it = numbersSet.iterator();
        while (it.hasNext()) {
            int number = it.next();
            if (isPrime(number)) {
                count++;
            }
        }

        return count;
    }

    public static boolean isPrime(int num) {
        if (num == 0 || num == 1) {
            return false;
        }

        int lim = (int) Math.sqrt(num);

        for (int i = 2; i <= lim; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static void comb(int depth, String str) {
        if (depth > N) {
            return;
        }
        if (!"".equals(str)) {
            numbersSet.add(Integer.parseInt(str));
        }
        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            comb(depth + 1, str + numbersCopy.charAt(i));
            visited[i] = false;
        }
    }
}
