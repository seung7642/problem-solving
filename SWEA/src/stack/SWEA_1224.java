package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class SWEA_1224 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int T;
    private static Stack<Integer> operand;   // 피연산자
    private static Stack<Character> operator;  // 연산자

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (T++ < 10) {
            operand = new Stack<>();
            operator = new Stack<>();
            br.readLine();
            String str = br.readLine();
            for (char c : str.toCharArray()) {
                if (Character.isDigit(c)) {
                    operand.push(Character.digit(c, 10));
                } else if (c == '(') {
                    operator.push(c);
                } else if (c == ')') {
                    while (!operator.isEmpty()) {
                        char op = operator.pop();
                        if (op == '(') break;
                        int a = operand.pop();
                        int b = operand.pop();
                        operand.push(calculate(a, b, op));
                    }
                } else {
                    while (!operator.isEmpty() && getPriority(operator.peek()) >= getPriority(c)) {
                        int a = operand.pop();
                        int b = operand.pop();
                        operand.push(calculate(a, b, operator.pop()));
                    }
                    operator.push(c);
                }
            }

            while (!operator.isEmpty()) {
                int a = operand.pop();
                int b = operand.pop();
                operand.push(calculate(a, b, operator.pop()));
            }

            sb.append("#").append(T).append(" ").append(operand.pop()).append("\n");
        }
        System.out.println(sb);
    }

    private static int getPriority(char c) {
        if (c == '(') return 0;
        else if (c == '+') return 1;
        else return 2;
    }

    private static int calculate(int a, int b, char operator) {
        if (operator == '*') return a * b;
        else return a + b;
    }
}
