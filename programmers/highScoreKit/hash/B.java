package highScoreKit.hash;

public class B {

    public static void main(String[] args) {
        String[] phone_book;

        phone_book = new String[]{"119", "97674223", "1195524421"};
        System.out.println(solution(phone_book)); // 답: false

        phone_book = new String[]{"123","456","789"};
        System.out.println(solution(phone_book)); // 답: true

        phone_book = new String[]{"12","123","1235","567","88"};
        System.out.println(solution(phone_book)); // 답: false
    }

    public static boolean solution(String[] phone_book) {
        Trie trie = new Trie();
        for (String s : phone_book) {
            if (trie.contains(trie.root, s, 0)) {
                return false;
            }
            trie.insert(trie.root, s, 0);
        }
        return true;
    }

    private static class Trie {

        Node root = new Node();

        public void insert(Node head, String str, int idx) {
            int charIdx = Character.digit(str.charAt(idx), 10);
            if (head.child[charIdx] == null) {
                head.child[charIdx] = new Node();
            }
            if (idx == str.length() - 1) {
                head.child[charIdx].finish = true;
            } else {
                this.insert(head.child[charIdx], str, idx + 1);
            }
        }

        // str이 이미 포함되어 있는지 확인합니다.
        public boolean contains(Node head, String str, int idx) {
            int charIdx = Character.digit(str.charAt(idx), 10);
            if (head.child[charIdx] == null) {
                return false;
            }
            if (idx == str.length() - 1) {
                return true;
            } else if (head.child[charIdx].finish) {
                return true;
            }
            return contains(head.child[charIdx], str, idx + 1);
        }

        private static class Node {
            Node[] child = new Node[26];
            boolean finish = false;
        }

    }
}
