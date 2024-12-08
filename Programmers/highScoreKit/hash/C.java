package highScoreKit.hash;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class C {

    public static void main(String[] args) {
        String[][] clothes;

        clothes = new String[][]{{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}};
        System.out.println(solution(clothes)); // 답: 5

        clothes = new String[][]{{"crowmask", "face"}, {"bluesunglasses", "face"}, {"smoky_makeup", "face"}};
        System.out.println(solution(clothes)); // 답: 3
    }

    public static int solution(String[][] clothes) {
        Map<String, Integer> map = new HashMap<>();
        for (String[] clothe : clothes) {
            map.put(clothe[1], map.getOrDefault(clothe[1], 0) + 1);
        }

        int answer = 1;
        Iterator<Integer> iterator = map.values().iterator();
        while (iterator.hasNext()) {
            answer *= iterator.next() + 1;
        }

        return answer - 1;
    }
}
