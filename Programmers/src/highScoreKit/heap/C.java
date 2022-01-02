package highScoreKit.heap;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class C {

    public static void main(String[] args) {
        String[] operations;
        operations = new String[]{"I 16","D 1"};
        System.out.println(Arrays.toString(solution(operations))); // 답: [0,0]

        operations = new String[]{"I 7","I 5","I -5","D -1"};
        System.out.println(Arrays.toString(solution(operations))); // 답: [7,5]
    }

    public static int[] solution(String[] operations) {
        int[] answer = {};

        Queue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        Queue<Integer> minHeap = new PriorityQueue<>();

        for (String operation : operations) {
            String[] sArr = operation.split(" ");
            String cmd = sArr[0];
            int data = Integer.parseInt(sArr[1]);

            if ("I".equals(cmd)) {
                maxHeap.add(data);
                minHeap.add(data);
            } else if ("D".equals(cmd)) {
                if (data == 1) {
                    Integer max = maxHeap.poll();
                    minHeap.remove(max);
                } else {
                    Integer min = minHeap.poll();
                    maxHeap.remove(min);
                }
            }
        }

        if (maxHeap.isEmpty()) {
            answer = new int[]{0, 0};
        } else {
            answer = new int[]{maxHeap.peek(), minHeap.peek()};
        }
        return answer;
    }
}
