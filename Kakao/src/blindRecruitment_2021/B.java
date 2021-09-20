package blindRecruitment_2021;

import java.util.*;

public class B {

    public static void main(String[] args) {
        String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] course = {2, 3, 4};
        List<String> list = solution(orders, course);
        for (String item : list) {
            System.out.print(item + " ");
        }
    }

    private static int N, max;
    private static StringBuilder sb;
    private static Map<String, Integer> map;

    public static List<String> solution(String[] orders, int[] course) {
        List<String> answer = new ArrayList<>();

        for (int i = 0; i < orders.length; i++) {
            char[] chars = orders[i].toCharArray();
            Arrays.sort(chars);
            orders[i] = String.valueOf(chars);
        }

        for (int i = 0; i < course.length; i++) {
            N = course[i];
            map = new HashMap<>();
            max = 0;
            for (String order : orders) {
                sb = new StringBuilder();
                if (order.length() >= N) {
                    combination(0, 0, order);
                }
            }

            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (max >= 2 && entry.getValue() == max) {
                    answer.add(entry.getKey());
                }
            }
        }

        Collections.sort(answer);
        return answer;
    }

    private static void combination(int depth, int idx, String str) {
        if (depth == N) {
            map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + 1);
            max = Math.max(max, map.get(sb.toString()));
            return;
        }

        for (int i = idx; i < str.length(); i++) {
            sb.append(str.charAt(i));
            combination(depth + 1, i + 1, str);
            sb.deleteCharAt(depth);
        }
    }
}
