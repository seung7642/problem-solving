package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_1259 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int T, N;
    private static List<Node> nodes;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(in.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(in.readLine());
            nodes = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < N; i++) {
                int front = Integer.parseInt(st.nextToken());
                int back = Integer.parseInt(st.nextToken());
                nodes.add(new Node(front, back));
            }
            List<Node> list = new ArrayList<>();
            list.add(nodes.remove(0));
            while (!nodes.isEmpty()) {
                for (int i = 0; i < nodes.size(); i++) {
                    Node node = nodes.get(i);
                    if (list.get(0).front == node.back) {
                        list.add(0, nodes.remove(i));
                    } else if (node.front == list.get(list.size() - 1).back) {
                        list.add(nodes.remove(i));
                    }
                }
            }

            sb.append("#").append(tc).append(" ");
            for (Node node : list) {
                sb.append(node.front).append(" ");
                sb.append(node.back).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }


    private static class Node {
        int front, back;

        public Node(int front, int back) {
            this.front = front;
            this.back = back;
        }
    }
}
