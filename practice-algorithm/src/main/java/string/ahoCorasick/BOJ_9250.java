package string.ahoCorasick;

import java.io.*;
import java.util.*;

// 아호-코라식(Aho-Corasick) 알고리즘 입문용 문제
public class BOJ_9250 {
    
    private static int size = 26;
    private static Trie trie = new Trie();
    private static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            // 1. 입력받은 문자열을 trie에 세팅한다.
            int N = Integer.parseInt(br.readLine());
            while (N-- > 0) {
                trie.insert(br.readLine());
            }

            // 2. 실패 함수 실행 (KMP 전처리, BFS를 이용해 구현)
            failure();

            // 3. 문자열-트라이 간의 매칭 (KMP 알고리즘 실행)
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            while (N-- > 0) {
                sb.append(kmp(br.readLine(), trie.root) ? "YES" : "NO").append("\n");
            }

            System.out.print(sb.toString());
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    // 실패 함수 (KMP로 비교를 하다가 실패했을 때의 이동해야할 지점을 정의한다.)
    // 일반적인 KMP 구현에서 getPi() 메서드에 해당한다.
    private static void failure() {
        Queue<TrieNode> q = new LinkedList<>();
        trie.root.fail = trie.root; // root 회귀
        q.add(trie.root);

        // BFS 탐색
        while (!q.isEmpty()) {
            TrieNode currentNode = q.poll();

            for (int idx = 0; idx < size; idx++) {
                TrieNode nextNode = currentNode.child[idx];

                if (nextNode == null) continue;

                if (currentNode.isRoot) { // root 자식 노드의 fail은 그들의 부모인 root 노드가 된다.
                    nextNode.fail = trie.root;
                } else {
                    TrieNode failure = currentNode.fail;

                    while (!failure.isRoot && failure.child[idx] == null) {
                        failure = failure.fail;
                    }
                    if (failure.child[idx] != null) {
                        failure = failure.child[idx];
                    }
                    nextNode.fail = failure;
                }

                if (nextNode.fail.isEnd) {
                    nextNode.isEnd = true;
                }

                q.add(nextNode);
            }
        }
    }

    // 원래의 KMP는 문자열-문자열 간의 매칭 비교이지만,
    // 위 개념을 확장해서 문자열-트라이 간의 매칭 비교를 한다.
    private static boolean kmp(String input, TrieNode root) {
        TrieNode currentNode = root; // 그냥 n 가져다 쓰면 trie.root 값 변함(call by ref.)

        for (int i = 0; i < input.length(); i++) {
            int idx = input.charAt(i) - 'a';

            while (!currentNode.isRoot && currentNode.child[idx] == null) {
                currentNode = currentNode.fail;
            }
            if (currentNode.child[idx] != null) {
                currentNode = currentNode.child[idx];
            }
            if (currentNode.isEnd) {
                return true;
            }
        }
        return false;
    }

    private static class TrieNode {

        private boolean isEnd, isRoot;
        private TrieNode[] child;
        private TrieNode fail;

        public TrieNode(boolean isRoot) {
            child = new TrieNode[size];
            this.isRoot = isRoot;
        }

        private TrieNode setChild(char data) {
            int idx = data - 'a';

            if (child[idx] == null) {
                child[idx] = new TrieNode(false);
            }
            return child[idx];
        }
    }

    private static class Trie {

        private TrieNode root = new TrieNode(true); // true = root

        private void insert(String key) {
            TrieNode currentNode = root;

            for (int i = 0; i < key.length(); i++) {
                currentNode = currentNode.setChild(key.charAt(i));
            }
            currentNode.isEnd = true;
        }
    }
}
