package string.time;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;

public class BOJ_3029 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String current = br.readLine();
        String target = br.readLine();

        int[] cur = Arrays.stream(current.split(":")).mapToInt(Integer::parseInt).toArray();
        int[] tar = Arrays.stream(target.split(":")).mapToInt(Integer::parseInt).toArray();

        LocalDateTime later, earlier;
        if (current.compareTo(target) >= 0) {
            later = LocalDateTime.of(2000, 1, 2, tar[0], tar[1], tar[2]);
            earlier = LocalDateTime.of(2000, 1, 1, cur[0], cur[1], cur[2]);
        } else {
            later = LocalDateTime.of(2000, 1, 1, tar[0], tar[1], tar[2]);
            earlier = LocalDateTime.of(2000, 1, 1, cur[0], cur[1], cur[2]);
        }

        long res = Math.abs(Duration.between(later, earlier).getSeconds());
        System.out.printf("%02d:%02d:%02d\n", res / 3600, (res % 3600) / 60, res % 60);
    }
}
