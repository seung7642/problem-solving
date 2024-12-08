package blindRecruitment_2021;

public class A {

    public static void main(String[] args) {
        System.out.println(solution("...!@BaT#*..y.abcdefghijklm"));
        System.out.println(solution("z-+.^."));
        System.out.println(solution("=.="));
        System.out.println(solution("123_.def"));
        System.out.println(solution("abcdefghijklmn.p"));
    }

    public static String solution(String new_id) {
        new_id = step1(new_id);
        new_id = step2(new_id);
        new_id = step3(new_id);
        new_id = step4(new_id);
        new_id = step5(new_id);
        new_id = step6(new_id);
        new_id = step7(new_id);
        return new_id;
    }

    private static String step1(String str) {
        return str.toLowerCase();
    }

    private static String step2(String str) {
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (Character.isLowerCase(c) || Character.isDigit(c)
                    || c == '-' || c == '_' || c == '.') {
                continue;
            }
            sb.deleteCharAt(i--);
        }
        str = sb.toString();
        return str;
    }

    private static String step3(String str) {
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (c != '.') continue;
            int start, end;
            start = end = i;
            for (int j = i + 1; j < sb.length(); j++) {
                char nextChar = sb.charAt(j);
                if (nextChar != '.') break;
                end = j;
            }
            if (end - start > 0) {
                sb.delete(start, end);
            }
        }
        str = sb.toString();
        return str;
    }

    private static String step4(String str) {
        StringBuilder sb = new StringBuilder(str);
        if (sb.charAt(0) == '.') sb.deleteCharAt(0);
        if (sb.length() > 0) {
            if (sb.charAt(sb.length() - 1) == '.') sb.deleteCharAt(sb.length() - 1);
        }
        str = sb.toString();
        return str;
    }

    private static String step5(String str) {
        if ("".equals(str)) str = "aaa";
        return str;
    }

    private static String step6(String str) {
        if (str.length() > 15) {
            str = str.substring(0, 15);
            if (str.charAt(14) == '.') {
                str = str.substring(0, 14);
            }
        }
        return str;
    }

    private static String step7(String str) {
        if (str.length() <= 2) {
            if (str.length() == 2) str += str.charAt(1);
            else str += str.charAt(0) + str.charAt(0);
        }
        return str;
    }
}
