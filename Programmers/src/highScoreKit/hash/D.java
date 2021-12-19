package highScoreKit.hash;

import java.util.*;

public class D {

    public static void main(String[] args) {
        String[] genres;
        int[] plays;

        genres = new String[]{"classic", "pop", "classic", "classic", "pop"};
        plays = new int[]{500, 600, 150, 800, 2500};
        System.out.println(solution(genres, plays)); // 답: [4, 1, 3, 0]
    }

    public static List<Integer> solution(String[] genres, int[] plays) {
        List<Integer> answer = new ArrayList<>();

        // 장르별 총 재생횟수
        // 장르별 고유번호, 해당 노래의 재생횟수
        Map<String, Node> map = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            int play = plays[i];
            Node node;
            if (map.containsKey(genre)) {
                node = map.get(genre);
                node.totalPlayCnt += play;
            } else {
                node = new Node(genre, play);
            }
            node.musicList.add(new Music(i, play));
            map.put(genre, node);
        }

        Queue<Node> pq = new PriorityQueue<>((a, b) -> b.totalPlayCnt - a.totalPlayCnt);
        pq.addAll(map.values());
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            node.musicList.sort((a, b) -> b.playCnt - a.playCnt);
            int cnt = 0;
            for (Music music : node.musicList) {
                answer.add(music.idx);
                if (++cnt == 2) {
                    break;
                }
            }
        }

        return answer;
    }

    private static class Node {
        String genre;
        int totalPlayCnt;
        List<Music> musicList = new ArrayList<>();

        public Node(String genre, int totalPlayCnt) {
            this.genre = genre;
            this.totalPlayCnt = totalPlayCnt;
        }
    }

    private static class Music {
        int idx;
        int playCnt;

        public Music(int idx, int playCnt) {
            this.idx = idx;
            this.playCnt = playCnt;
        }
    }

}
