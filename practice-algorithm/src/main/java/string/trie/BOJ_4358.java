package string.trie;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ_4358 {

    private static class Trie {

        Trie[] child = new Trie[127 - 32]; // 아스키문자(127개) - 아스키코드표에서 제어 문자(32개)
        boolean isFinish = false;
        int cnt = 0; // 해당 종의 갯수

        private void insert(int idx, String str) {
            if (idx >= str.length()) return; // 유효성 검사

            int wordIdx = (int) str.charAt(idx) - 32; // 해당 문자에 대응되는 아스키코드 값으로 변환한다.
            if (child[wordIdx] == null) {
                child[wordIdx] = new Trie();
            }

            if (idx == (str.length() - 1)) {
                child[wordIdx].isFinish = true;
                child[wordIdx].cnt++;
            } else {
                child[wordIdx].insert(idx + 1, str);
            }
        }

        private boolean find(int idx, String str) {
            if (idx >= str.length()) return false; // 유효성 검사

            int wordIdx = (int) str.charAt(idx) - 32; // 해당 문자에 대응되는 아스키코드 값.
            if (child[wordIdx] == null) return false;
            if (idx == (str.length() - 1)) {
                if (child[wordIdx].isFinish) return true;
                return false;
            }
            return child[wordIdx].find(idx + 1, str);
        }

        private int getStringCnt(int idx, String str) {
            if (idx >= str.length()) return -1; // 유효성 검사

            int wordIdx = (int) str.charAt(idx) - 32; // 해당 문자에 대응되는 아스키코드 값.
            if (child[wordIdx] == null) return -1;
            if (idx == (str.length() - 1)) {
                if (child[wordIdx].isFinish) return child[wordIdx].cnt;
                return -1;
            }
            return child[wordIdx].getStringCnt(idx + 1, str);
        }
    }

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            Trie trie = new Trie();
            int totalCnt = 0;
            List<String> list = new ArrayList<>();
            String str;

            while ((str = br.readLine()) != null && str.length() != 0) {
                totalCnt++;
                if (!trie.find(0, str)) {
                    list.add(str);
                }
                trie.insert(0, str);
            }

            StringBuilder sb = new StringBuilder();
            Collections.sort(list);
            for (String s : list) {
                double per = ((double) trie.getStringCnt(0, s) * 100.0) / totalCnt;
                sb.append(s).append(String.format(" %.4f\n", per));
            }

            System.out.println(sb.toString());
        } catch (Exception ex) {
            ex.getMessage();
        }
    }
}
