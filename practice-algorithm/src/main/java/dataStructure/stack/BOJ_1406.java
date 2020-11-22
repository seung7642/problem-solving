package dataStructure.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_1406 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int M;
    private static Stack<Character> leftStr, rightStr;

    public static void main(String[] args) throws IOException {
        String str = br.readLine();
        M = Integer.parseInt(br.readLine());

        leftStr = new Stack<>();
        rightStr = new Stack<>();
        for (int i = 0; i < str.length(); i++)
            leftStr.push(str.charAt(i));

        while (M-- > 0) {
            String cmd = br.readLine();
            executeCommand(cmd);
        }

        print();
        br.close();
    }

    private static void executeCommand(String cmd) {
        if (cmd.charAt(0) == 'L') { // 커서를 왼쪽으로 한 칸 옮긴다.
            if (!leftStr.isEmpty())
                rightStr.push( leftStr.pop() );
        } else if (cmd.charAt(0) == 'D') { // 커서를 오른쪽으로 한 칸 옮긴다.
            if (!rightStr.isEmpty())
                leftStr.push( rightStr.pop() );
        } else if (cmd.charAt(0) == 'B') { // 커서 왼쪽에 위치한 문자를 삭제한다.
            if (leftStr.size() != 0)
                leftStr.pop();
        } else if (cmd.charAt(0) == 'P') {
            leftStr.push( cmd.charAt(2) );
        }
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        while (!leftStr.isEmpty())
            rightStr.push( leftStr.pop() );

        while (!rightStr.isEmpty())
            sb.append(rightStr.pop());

        System.out.println(sb);
    }
}
