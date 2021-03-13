package string.trie;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_5670 {

    private static int cnt = 0;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                int N = Integer.parseInt(line);
                Trie trie = new Trie();
                String[] str = new String[N];

                for (int i = 0; i < N; i++) {
                    str[i] = br.readLine();
                    trie.insert(str[i]);
                }

                for (int i = 0; i < 26; i++) {
                    if (trie.root.child[i] != null) {
                        trie.check(trie.root.child[i], 1);
                    }
                }

                double avg = (double) cnt / N;
                sb.append(String.format("%.2f\n", avg));
                cnt = 0;
            }

            System.out.println(sb.toString());
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    private static class Trie {

        TrieNode root = new TrieNode();

        private void insert(String key) {
            int length = key.length();
            TrieNode currentNode = root;

            for (int i = 0; i < length; i++) {
                int next = key.charAt(i) - 'a';
                if (currentNode.child[next] == null) {
                    currentNode.child[next] = new TrieNode();
                    currentNode.nChild++; // 자식의 갯수
                }
                currentNode = currentNode.child[next];
            }
            currentNode.isFinish = true;
        }

        private void check(TrieNode node, int ret) {
            if (node.isFinish) cnt += ret;

            if (node.nChild >= 2) ret++; // 자식이 2개 이상이라면 자동 입력이 불가능하다.

            // 자식이 하나여도 해당 문자가 문자열의 끝이라면 자동 입력을 하면 안된다.
            // 해당 문자열을 입력하고자 하는 목적일 수가 있기 때문이다.
            if (node.isFinish && node.nChild == 1) ret++;

            for (int i = 0; i < 26; i++) {
                if (node.child[i] != null) {
                    check(node.child[i], ret);
                }
            }
        }
    }

    private static class TrieNode {

        TrieNode[] child = new TrieNode[26];
        boolean isFinish = false;
        int nChild = 0;
    }
}
