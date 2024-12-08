package winterCoding_2020;

// 60점/100점
public class B {

    public static void main(String[] args) {

        System.out.println('b' - 'a');
        System.out.println(-2 % 26);

        String encryptedText = "qyyigoptvfb";
        String key = "abcdefghijk";
        int rotation = 3;
        String result = solution(encryptedText, key, rotation); // result: "hellopython"
        System.out.println(result);
    }

    private static String solution(String encrypted_text, String key, int rotation) {
        StringBuilder sb = new StringBuilder();
        int len = encrypted_text.length();
        int absRotation = Math.abs(rotation) % len;

        int cur;
        if (rotation < 0) cur = len - absRotation + 1;
        else cur = absRotation;

        for (int i = 0; i < len; i++) {
            sb.append(encrypted_text.charAt(cur % len));
            cur++;
        }

        for (int i = 0; i < len; i++) {
            int charNum = sb.charAt(i);
            int num = key.charAt(i) - 'a' + 1;
            int convertNum = charNum - num;
            if (convertNum < 97) convertNum += 26;
            sb.setCharAt(i, (char) convertNum);
        }

        return String.valueOf(sb);
    }
}
