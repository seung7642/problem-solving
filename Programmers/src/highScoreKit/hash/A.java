package highScoreKit.hash;

import java.util.*;

public class A {

    public static void main(String[] args) {
        String[] participant;
        String[] completion;

        participant = new String[]{"leo", "kiki", "eden"};
        completion = new String[]{"eden", "kiki"};
        System.out.println(solution(participant, completion)); // 답: "leo"

        participant = new String[]{"marina", "josipa", "nikola", "vinko", "filipa"};
        completion = new String[]{"josipa", "filipa", "marina", "nikola"};
        System.out.println(solution(participant, completion)); // 답: "vinko"

        participant = new String[]{"mislav", "stanko", "mislav", "ana"};
        completion = new String[]{"stanko", "ana", "mislav"};
        System.out.println(solution(participant, completion)); // 답: "mislav"
    }

    public static String solution(String[] participant, String[] completion) {
        String answer = "";
        Map<String, Integer> map = new HashMap<>();
        for (String s : participant) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        for (String s : completion) {
            if (map.get(s) == 1) {
                map.remove(s);
            } else {
                map.put(s, map.get(s) - 1);
            }
        }
        for (String s : map.keySet()) {
            answer = s;
        }

        return answer;
    }
}
