package blindRecruitment_2021;

public class A_0 {

    public static void main(String[] args) {
        System.out.println(solution("...!@BaT#*..y.abcdefghijklm"));
        System.out.println(solution("z-+.^."));
        System.out.println(solution("=.="));
        System.out.println(solution("123_.def"));
        System.out.println(solution("abcdefghijklmn.p"));
    }

    public static String solution(String new_id) {
        String id = new_id.toLowerCase(); // 소문자로 변환
        id = id.replaceAll("[^-_.a-z0-9]", ""); // -_. 영문 숫자만 남긴다.
        id = id.replaceAll("[.]{2,}", ".");     // .2개 이상 .으로 치환한다.
        id = id.replaceAll("^[.]|[.]$", "");    // 처음과 끝 . 제거한다.

        if ("".equals(id)) {
            id += "a";
        }

        if (id.length() > 15) {
            id = id.substring(0, 15);
            id = id.replaceAll("^[.]|[.]$", "");
        }

        if (id.length() <= 2) {
            while (id.length() < 3) {
                id += id.charAt(id.length() - 1);
            }
        }
        return id;
    }
}
