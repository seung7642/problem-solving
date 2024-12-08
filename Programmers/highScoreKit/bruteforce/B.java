package highScoreKit.bruteforce;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class B {

    public static void main(String[] args) {
        String numbers;

        numbers = "17";
        System.out.println(solution(numbers)); // 답: 3

        numbersSet = new HashSet<>();
        numbers = "011";
        System.out.println(solution(numbers)); // 답: 2
    }

    private static Set<Integer> numbersSet = new HashSet<>();

    public static int solution(String numbers) {
        // 1. 모든 조합의 숫자를 만듭니다.
        recursive("", numbers);

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

    private static void recursive(String comb, String others) {
        // 1. 현재 조합을 set에 추가합니다.
        if (!comb.equals("")) {
            numbersSet.add(Integer.parseInt(comb));
        }

        // 2. 남은 숫자 중 한 개를 더해 새로운 조합을 만듭니다.
        for (int i = 0; i < others.length(); i++) {
            recursive(comb + others.charAt(i), others.substring(0, i) + others.substring(i + 1));
        }
    }
}
