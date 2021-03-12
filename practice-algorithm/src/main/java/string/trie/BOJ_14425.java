package string.trie;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_14425 {

    private static class Trie {

        boolean isExist = false;
        boolean isFinish = false; // 해당 노드가 문자열의 끝인지 체크한다. (te, tea 두 개가 저장될때 te라는 문자열이 있는지 확인한다고 해보자.)
        Trie[] child = new Trie[26]; // 알파벳의 갯수는 26 (소문자만)

        private void insert(int idx, String str) {
            if (idx >= str.length()) return; // 유효성 검사

            int wordIdx = (str.charAt(idx) - 'a');
            if (child[wordIdx] == null) { // 문자가 자식 노드에 없다면 추가해준다.
                child[wordIdx] = new Trie();
            }

            if (idx == (str.length() - 1)) {
                child[wordIdx].isFinish = true;
            } else {
                child[wordIdx].insert(idx + 1, str);
            }
        }

        private boolean find(int idx, String str) {
            if (idx >= str.length()) return false; // 유효성 검사

            int wordIdx = (str.charAt(idx) - 'a');
            if (idx == (str.length() - 1)) {
                if (child[wordIdx] != null && child[wordIdx].isFinish) return true;
                else return false;
            }

            if (child[wordIdx] == null) {
                return false;
            }

            return child[wordIdx].find(idx + 1, str);
        }
    }

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            Trie trie = new Trie();
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            for (int i = 0; i < N; i++) {
                String word = br.readLine();
                trie.insert(0, word);
            }

            int cnt = 0;
            for (int i = 0; i < M; i++) {
                String word = br.readLine();
                if (trie.find(0, word)) {
                    cnt++;
                }
            }

            System.out.println(cnt);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }
}
