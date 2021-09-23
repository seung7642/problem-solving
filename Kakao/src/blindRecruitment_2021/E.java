package blindRecruitment_2021;

import java.util.Arrays;

// 광고 삽입
public class E {

    public static void main(String[] args) {
        String play_time = "02:03:55";
        String adv_time = "00:14:15";
        String[] logs = {"01:20:15-01:45:14"
                , "00:40:31-01:00:00"
                , "00:25:50-00:48:29"
                , "01:30:59-01:53:29"
                , "01:37:44-02:02:30"};
        System.out.println(solution(play_time, adv_time, logs));
    }

    public static String solution(String play_time, String adv_time, String[] logs) {
        int playTimeSecond = timeToSecond(play_time);
        int advTimeSecond = timeToSecond(adv_time);
        long[] prefixSum = new long[playTimeSecond + 1];
        for (String log : logs) {
            String[] str = log.split("-");
            prefixSum[timeToSecond(str[0])]++;
            prefixSum[timeToSecond(str[1])]--;
        }
        for (int i = 1; i <= playTimeSecond; i++) prefixSum[i] += prefixSum[i - 1]; // 현재 동영상을 시청하고 있는 사람의 수를 구간에 저장한다.
        for (int i = 1; i <= playTimeSecond; i++) prefixSum[i] += prefixSum[i - 1]; // 현재 시간까지 동영상을 시청한 총 재생시간을 누적한다.

        long maxTimeSecond = prefixSum[advTimeSecond - 1];
        long startTimeSecond = 0L;
        for (int i = 1; i + advTimeSecond - 1 <= playTimeSecond; i++) {
            long arangeSum = prefixSum[i + advTimeSecond - 1] - prefixSum[i - 1];
            if (maxTimeSecond < arangeSum) {
                maxTimeSecond = arangeSum;
                startTimeSecond = i;
            }
        }
        long hour = startTimeSecond / (60 * 60);
        long minute = (startTimeSecond / 60) % 60;
        long second = startTimeSecond % 60;
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    private static int timeToSecond(String time) {
        int[] t = Arrays.stream(time.split(":")).mapToInt(Integer::parseInt).toArray();
        return t[0] * 60 * 60 + t[1] * 60 + t[2];
    }
}
