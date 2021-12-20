package highScoreKit.stackAndQueue;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class B {

    public static void main(String[] args) {
        int[] priorities;
        int location;

        priorities = new int[]{2, 1, 3, 2};
        location = 2;
        System.out.println(solution(priorities, location)); // 답: 1

        priorities = new int[]{1, 1, 9, 1, 1, 1};
        location = 0;
        System.out.println(solution(priorities, location)); // 답: 5
    }

    public static int solution(int[] priorities, int location) {
        Queue<Node> q = new LinkedList<>();
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int max = 0;
        for (int i = 0; i < priorities.length; i++) {
            int priority = priorities[i];
            if (max < priority) {
                max = priority;
            }
            q.add(new Node(i, priority));
            pq.add(priority);
        }

        int cnt = 0;
        while (!pq.isEmpty()) {
            Integer maxPriority = pq.poll();

            while (true) {
                Node node = q.poll();
                if (node.priority == maxPriority) {
                    cnt++;
                    if (node.idx == location) {
                        return cnt;
                    }
                    break;
                } else {
                    q.add(node);
                }
            }
        }

        return cnt;
    }

    private static class Node {
        int idx, priority;

        public Node(int idx, int priority) {
            this.idx = idx;
            this.priority = priority;
        }
    }

}
