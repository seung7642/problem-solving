package string.trie;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_14725 {

    private static class Trie {

        String word; // 정석적인 트라이 자료구조는 각 노드가 문자 1개를 저장하지만, 해당 문제에서는 각 노드가 문자열을 저장한다.
        List<Trie> child = new ArrayList<>();

        public Trie() {
        }

        public Trie(String word) {
            this.word = word;
        }

        // 새로운 문자열을 트라이에 추가한다.
        // k: 먹이 정보 갯수 (문자열 갯수)
        public void add(int idx, String[] arr) {
            boolean isNotExist = true; // 추가하려는 문자열이 이미 있는지 체크한다.

            // 추가하려는 문자열이 트라이에 이미 있는지 확인한다.
            // 있다면 자식 노드에서 남은 문자열에 대해 다시 add() 실행한다.
            for (Trie item : child) {
                if (item.word.equals(arr[idx])) {
                    item.add(idx + 1, arr);
                    isNotExist = false;
                    break;
                }
            }

            // 추가하려는 문자열이 트라이에 없다면 자식 노드에 새로 추가해준 후, 다시 add()를 실행한다.
            if (isNotExist) {
                child.add(new Trie(arr[idx]));
                if (idx < arr.length - 1) {
                    this.add(idx, arr);
                }
            }
        }

        public void print(int depth) {
            // 같은 레벨의 노드에 대해선 문자열이 사전 순으로 앞서는 것부터 출력한다.
            this.child.sort((t1, t2) -> t1.word.compareTo(t2.word));

            for (Trie item : child) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < depth; i++) {
                    sb.append("--");
                }
                System.out.println(sb.toString() + item.word);
                item.print(depth + 1);
            }
        }
    }

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            Trie trie = new Trie();
            int n = Integer.parseInt(br.readLine());

            for (int i = 0; i < n; i++) {
                String[] list = br.readLine().split(" ");
                trie.add(1, list);
            }

            trie.print(0);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }
}
