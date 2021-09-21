package blindRecruitment_2021;

import java.util.*;

// 순위 검색 - 조합, 이분탐색
public class C {

    public static void main(String[] args) {
        String[] info = {"java backend junior pizza 150"
                ,"python frontend senior chicken 210"
                ,"python frontend senior chicken 150"
                ,"cpp backend senior pizza 260"
                ,"java backend junior chicken 80"
                ,"python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100"
                ,"python and frontend and senior and chicken 200"
                ,"cpp and - and senior and pizza 250"
                ,"- and backend and senior and - 150"
                ,"- and - and - and chicken 100"
                ,"- and - and - and - 150"};
        List<Integer> list = solution(info, query);
        for (int item : list) {
            System.out.print(item + " ");
        }
    }

    private static final Map<String, List<Integer>> infos = new HashMap<>();

    public static List<Integer> solution(String[] info, String[] query) {
        List<Integer> answer = new ArrayList<>();
        for (String str : info) {
            dfs(0, "", str.split(" "));
        }
        for (Map.Entry<String, List<Integer>> entry : infos.entrySet()) {
            Collections.sort(entry.getValue());
        }
        for (String str : query) {
            str = str.replaceAll(" and ", "");
            int score = 0;
            for (int j = str.length() - 1; j >= 0; j--) {
                if (str.charAt(j) == ' ') {
                    score = Integer.parseInt(str.substring(j + 1));
                    str = str.substring(0, j);
                    break;
                }
            }
            answer.add(binarySearch(str, score));
        }
        return answer;
    }

    private static void dfs(int depth, String pos, String[] info) {
        if (depth == 4) {
            if (!infos.containsKey(pos)) {
                infos.put(pos, new ArrayList<>());
            }
            infos.get(pos).add(Integer.parseInt(info[4]));
            return;
        }
        dfs(depth + 1, pos + "-", info);
        dfs(depth + 1, pos + info[depth], info);
    }

    private static int binarySearch(String str, int score) {
        if (!infos.containsKey(str)) return 0;
        List<Integer> scoreList = infos.get(str);
        int left = 0, right = scoreList.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (scoreList.get(mid) < score) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return scoreList.size() - left;
    }
}
