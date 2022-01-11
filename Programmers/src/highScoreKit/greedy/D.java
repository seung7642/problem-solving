package highScoreKit.greedy;

import java.util.Arrays;

public class D {

    public static void main(String[] args) {
        int[] people;
        int limit;

        people = new int[]{70, 50, 80, 50};
        limit = 100;
        System.out.println(solution(people, limit)); // 답: 3

        people = new int[]{70, 80, 50};
        limit = 100;
        System.out.println(solution(people, limit)); // 답: 3
    }

    public static int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        int left = 0;
        for (int right = people.length - 1; left <= right; right--) {
            if (people[left] + people[right] <= limit) {
                left++;
            }
            answer++;
        }
        return answer;
    }
}
