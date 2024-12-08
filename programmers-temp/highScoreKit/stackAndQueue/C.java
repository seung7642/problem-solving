package highScoreKit.stackAndQueue;

import java.util.LinkedList;
import java.util.Queue;

public class C {

    public static void main(String[] args) {
        int bridge_length;
        int weight;
        int[] truck_weights;

        bridge_length = 2;
        weight = 10;
        truck_weights = new int[]{7,4,5,6};
        System.out.println(solution(bridge_length, weight, truck_weights)); // 답: 8

        bridge_length = 100;
        weight = 100;
        truck_weights = new int[]{10};
        System.out.println(solution(bridge_length, weight, truck_weights)); // 답: 101

        bridge_length = 100;
        weight = 100;
        truck_weights = new int[]{10,10,10,10,10,10,10,10,10,10};
        System.out.println(solution(bridge_length, weight, truck_weights)); // 답: 110
    }

    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        int len = truck_weights.length, i = 0, weightOnBridge = 0, totalTime = 0;
        Queue<Node> q = new LinkedList<>();
        while (i < len) {
            int curTruckWeight = truck_weights[i];

            // 다리에 트럭 싣기
            if (weightOnBridge + curTruckWeight <= weight) {
                int size = 0;
                while (size++ < q.size()) {
                    Node node = q.poll();
                    q.add(new Node(node.weight, node.time + 1));
                }
                if (!q.isEmpty() && q.peek().time > bridge_length) {
                    weightOnBridge -= q.peek().weight;
                    q.poll();
                }
                q.add(new Node(curTruckWeight, 1));
                weightOnBridge += curTruckWeight;
                i++;
                totalTime++;
            } else {
                Node headTruck = q.poll();
                int timeForArrivalOfHead = bridge_length - headTruck.time + 1;
                weightOnBridge -= headTruck.weight;
                totalTime += timeForArrivalOfHead;

                int size = 0;
                while (size++ < q.size()) {
                    Node node = q.poll();
                    q.add(new Node(node.weight, node.time + timeForArrivalOfHead));
                }

                if (weightOnBridge + curTruckWeight <= weight) {
                    q.add(new Node(curTruckWeight, 1));
                    weightOnBridge += curTruckWeight;
                    i++;
                }
            }
        }

        while (!q.isEmpty()) {
            if (q.size() == 1) {
                totalTime += bridge_length - q.poll().time + 1;
                break;
            }
            q.poll();
        }

        return totalTime;
    }

    private static class Node {
        int weight, time;

        public Node(int weight, int time) {
            this.weight = weight;
            this.time = time;
        }
    }

}
