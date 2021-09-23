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
        int playTime = timeToSecond(play_time);
        int advTime = timeToSecond(adv_time);
        long[] playCnt = new long[playTime + 1];
        for (String log : logs) {
            String[] split = log.split("-");
            playCnt[timeToSecond(split[0])]++;
            playCnt[timeToSecond(split[1])]--;
        }
        for (int i = 1; i <= playTime; i++) playCnt[i] += playCnt[i - 1]; // 현재 동영상을 시청하고 있는 사람의 수를 구간에 저장한다.
        for (int i = 1; i <= playTime; i++) playCnt[i] += playCnt[i - 1]; // 현재 시간까지 동영상을 시청한 총 재생시간을 누적한다.

        long maxTime = playCnt[advTime - 1];
        long maxStartTime = 0;
        for (int i = 0; i + advTime <= playTime; i++) {
            long tmp = playCnt[i + advTime] - playCnt[i];
            if (tmp > maxTime) {
                maxTime = tmp;
                maxStartTime = i + 1;
            }
        }
        long hour = maxStartTime / (60 * 60);
        long minute = (maxStartTime / 60) % 60;
        long second = maxStartTime % 60;
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    private static int timeToSecond(String time) {
        int[] parse = Arrays.stream(time.split(":")).mapToInt(Integer::parseInt).toArray();
        return parse[0] * 60 * 60 + parse[1] * 60 + parse[2];
    }
}
