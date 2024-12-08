package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_13305 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(br.readLine()); // 도시의 수

            long[] dist = Arrays.stream(br.readLine().split(" "))
                    .mapToLong(Long::parseLong)
                    .toArray();

            long[] cost = Arrays.stream(br.readLine().split(" "))
                    .mapToLong(Long::parseLong)
                    .toArray();

            long sum = 0;
            long minCost = cost[0];

            for (int i = 0; i < n - 1; i++) {
                if (cost[i] < minCost) {
                    minCost = cost[i];
                }

                sum += (minCost * dist[i]);
            }

            System.out.println(sum);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }
}
