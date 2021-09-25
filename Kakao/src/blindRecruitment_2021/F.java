package blindRecruitment_2021;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 카드 짝 맞추기
public class F {

    public static void main(String[] args) {
        int[][] board = {{1,0,0,3}, {2,0,0,0}, {0,0,0,2}, {3,0,1,0}};
        int r = 1;
        int c = 0;
        int result = solution(board, r, c);
        System.out.println(result);
    }

    private static List<String> orders;
    private static int cardCnt;
    private static int[] card;
    private static boolean[] check;
    private static boolean[][] visited;
    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {-1, 1, 0, 0};

    public static int solution(int[][] board, int r, int c) {
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != 0) {
                    cardCnt++;
                }
            }
        }
        cardCnt /= 2;
        card = new int[cardCnt];
        for (int i = 0; i < cardCnt; i++) {
            card[i] = i + 1;
        }

        // 카드 조합 순서 경우의 수 구하기
        orders = new ArrayList<>();
        check = new boolean[cardCnt + 1];
        permutation(0, "");

        for (String comb : orders) {
            String[] order = comb.split("");
            int totalMove = 0;
            Node node = new Node(c, r, 0);
            int[][] tmpBoard = new int[4][4];
            for (int i = 0; i < 4; i++) {
                System.arraycopy(board[i], 0, tmpBoard[i], 0, 4);
            }
            for (String targetCard : order) {
                int cardNum = Integer.parseInt(targetCard);

                // 첫 번째 카드 찾기
                totalMove += cardSearch(node, cardNum, tmpBoard);
                tmpBoard[node.y][node.x] = 0;
                totalMove += 1; // enter 클릭

                // 두 번째 카드 찾기
                totalMove += cardSearch(node, cardNum, tmpBoard);
                tmpBoard[node.y][node.x] = 0;
                totalMove += 1; // enter 클릭
            }
            answer = Math.min(answer, totalMove);
        }
        return answer;
    }

    // 1. 카드 찾기 순서 경우의 수 모두 구하기
    private static void permutation(int depth, String str) {
        if (depth == cardCnt) {
            orders.add(str);
            return;
        }
        for (int i = 0; i < cardCnt; i++) {
            if (check[card[i]]) continue;
            check[card[i]] = true;
            permutation(depth + 1, str + card[i]);
            check[card[i]] = false;
        }
    }

    // 2. 카드 찾으러 이동하기
    private static int cardSearch(Node pos, int targetCard, int[][] tmpBoard) {
        Queue<Node> q = new LinkedList<>();
        visited = new boolean[4][4];
        visited[pos.y][pos.x] = true;
        q.add(new Node(pos.x, pos.y, 0));
        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (tmpBoard[cur.y][cur.x] == targetCard) {
                pos.x = cur.x;
                pos.y = cur.y;
                return cur.move;
            }
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (!valid(nx, ny) || visited[ny][nx]) continue;
                visited[ny][nx] = true;
                q.add(new Node(nx, ny, cur.move + 1));
            }
            for (int i = 0; i < 4; i++) {
                Node result = checkRoute(cur.x, cur.y, i, tmpBoard);
                int nx = result.x;
                int ny = result.y;
                if (nx == pos.x && ny == pos.y) continue;
                if (visited[ny][nx]) continue;
                visited[ny][nx] = true;
                q.add(new Node(nx, ny, cur.move + 1));
            }
        }
        return 0;
    }

    private static Node checkRoute(int x, int y, int direction, int[][] tmpBoard) {
        while (true) {
            x += dx[direction];
            y += dy[direction];
            if (!valid(x, y)) break;
            if (tmpBoard[y][x] != 0) {
                return new Node(x, y, 0);
            }
        }
        return new Node(x - dx[direction], y - dy[direction], 0);
    }

    private static boolean valid(int x, int y) {
        return 0 <= x && x < 4 && 0 <= y && y < 4;
    }

    private static class Node {
        int x, y, move;

        public Node(int x, int y, int move) {
            this.x = x;
            this.y = y;
            this.move = move;
        }
    }
}
