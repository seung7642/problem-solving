package winterCoding_2020;

public class A {

    public static void main(String[] args) {

        int n = 6;
        int[][] delivery = {{1,3,1},{3,5,0},{5,4,0},{2,5,0}};
        String str = solution(n, delivery); // result: "O?O?X?"
        System.out.println(str);
    }

    private static String solution(int n, int[][] delivery) {
        int[] item = new int[n + 1]; // 1: 있다, 0: 모른다, -1: 없다

        for (int k = 0; k < 2; k++) {
            for (int i = 0; i < delivery.length; i++) {
                int item1 = delivery[i][0];
                int item2 = delivery[i][1];
                boolean isDelivery = (delivery[i][2] != 0);

                if (isDelivery) { // 배송이 되었다면, 두 상품 모두 재고가 있다는 의미.
                    item[item1] = item[item2] = 1;
                } else {
                    if (item[item1] == 1 || item[item2] == 1) { // 두 상품 중 하나는 재고가 있는 경우
                        if (item[item1] == 1) item[item2] = -1;
                        else item[item1] = -1;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if (item[i] == 1) sb.append("O");
            else if (item[i] == 0) sb.append("?");
            else if (item[i] == -1) sb.append("X");
        }

        return String.valueOf(sb);
    }
}
