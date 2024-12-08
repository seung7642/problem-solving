package simulation;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16235 {

    private static int N, M, K;
    private static int[][] A;
    private static Land[][] map;
    private static int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
    private static int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] info = br.readLine().split(" ");

        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);
        K = Integer.parseInt(info[2]);
        A = new int[N][N];
        map = new Land[N][N];

        StringTokenizer st;
        // 각 칸에 추가되는 양분의 양을 담은 배열 입력받음
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 땅 지도 초기화
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new Land();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            map[x - 1][y - 1].trees.add(value);
        }

        int treeCnt = 0;
        while (K-- > 0) {
            treeCnt = oneYearFlow(); // 1년이 지날 때 마다 몇 그루의 나무가 살아있는지 구함
            // 살아있는 나무가 없으면 반복문 종료
            if (treeCnt == 0) { break; }
        }

        bw.write(treeCnt + " \n");
        bw.flush();
        bw.close();
    }

    private static int oneYearFlow() {
        int[][] breeding = springAndSummer();
        fall(breeding);
        winter();
        return countAliveTree();
    }

    private static int[][] springAndSummer() {
        int[][] breeding = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Queue<Integer> newTree = new PriorityQueue<>();
                Land land = map[i][j];
                int breedingCnt = 0;

                // 봄 - 나무 존재하고, 나이만큼 양분 먹을 수 있으면
                while (!land.trees.isEmpty() && land.food >= land.trees.peek()) {
                    int age = land.trees.poll();
                    land.food -= age; // 나이만큼 양분 먹음
                    newTree.add(age + 1); // 나이 증가

                    // 가을 - 증가한 나이가 5의 배수이면 번식할 수 있음
                    if ((age + 1) % 5 == 0) {
                        breedingCnt += 1;
                    }
                }

                // 큐가 비어있지 않음 = 양분 못먹은 나무 존재 = 죽어야 함
                // 여름 - 죽은 나무 양분으로 전환
                int summerFood = 0;
                while (!land.trees.isEmpty()) {
                    summerFood += land.trees.poll() / 2;
                }

                land.trees = newTree; // 새로운 나무 정보 저장
                land.food += summerFood; // 죽은 나무 양분 저장
                breeding[i][j] = breedingCnt; // 번식 가능한 나무 개수 저장
            }
        }

        return breeding;
    }

    private static void fall(int[][] breeding) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (breeding[i][j] == 0)
                    continue;

                // 번식 가능한 나무가 있으면 주위 8칸으로 나무 번식
                for (int k = 0; k < 8; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    int newTreeCnt = breeding[i][j];

                    // 주위의 칸이 범위 밖이면 넘김
                    if (!isIn(nx, ny)) continue;

                    // 번식 가능한 나무의 개수대로 나이가 1인 나무 심음
                    while (newTreeCnt-- > 0) {
                        map[nx][ny].trees.add(1);
                    }
                }
            }
        }
    }

    private static void winter() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j].food += A[i][j];
            }
        }
    }

    private static int countAliveTree() {
        int alive = 0;
        // 큐의 사이즈 = 살아남은 나무의 수
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                alive += map[i][j].trees.size();
            }
        }
        return alive;
    }

    private static boolean isIn(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    private static class Land {
        int food;
        Queue<Integer> trees;

        public Land() {
            this.food = 5;
            trees = new PriorityQueue<>();
        }
    }
}