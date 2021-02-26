package dataStructure.queue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_18258 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            Deque<Integer> queue = new LinkedList<>();
            StringBuilder sb = new StringBuilder();
            int N = Integer.parseInt(br.readLine());

            for (int i = 0; i < N; i++) {
                String[] cmd = br.readLine().split(" ");

                if (cmd[0].equals("push")) {
                    queue.offer(Integer.parseInt(cmd[1]));
                } else if (cmd[0].equals("pop")) {
                    Integer item = queue.poll();
                    sb.append(item == null ? -1 : item).append('\n');
                } else if (cmd[0].equals("size")) {
                    sb.append(queue.size()).append('\n');
                } else if (cmd[0].equals("empty")) {
                    sb.append(queue.isEmpty() ? 1 : 0).append('\n');
                } else if (cmd[0].equals("front")) {
                    Integer item = queue.peek();
                    sb.append(item == null ? -1 : item).append('\n');
                } else if (cmd[0].equals("back")) {
                    Integer item = queue.peekLast();
                    sb.append(item == null ? -1 : item).append('\n');
                }
            }

            System.out.println(sb);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }
}
