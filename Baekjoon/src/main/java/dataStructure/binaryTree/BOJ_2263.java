package dataStructure.binaryTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// inorder로 순회해서 나온 노드의 값과 postorder로 순회해서 나온 노드의 값을 가지고,
// 이진 트리를 구성할 수 있는가?
public class BOJ_2263 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, idx;
    private static int[] inOrder, postOrder, preOrder;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        inOrder = new int[N];
        postOrder = new int[N];
        preOrder = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            inOrder[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            postOrder[i] = Integer.parseInt(st.nextToken());
        }

        getPreOrder(0, N - 1, 0, N - 1);

        for (int e : preOrder) {
            sb.append(e).append(" ");
        }
        System.out.println(sb);
    }

    // 파라미터로 inOrder, postOrder의 시작과 끝 인덱스를 넘긴다.
    public static void getPreOrder(int inStart, int inEnd, int postStart, int postEnd) {
        if (inStart > inEnd || postStart > postEnd) return;

        preOrder[idx++] = postOrder[postEnd]; // postOrder의 마지막은 루트 노드이다.
        int pos = inStart;
        for (int i = inStart; i <= inEnd; i++) {
            if (inOrder[i] == postOrder[postEnd]) {
                pos = i;
                break;
            }
        }

        // 왼쪽 자식 트리를 가지고 다시 똑같은 과정을 반복한다.
        getPreOrder(inStart, pos - 1, postStart, postStart + pos - inStart - 1);

        // 오른쪽 자식 트리를 가지고 다시 똑같은 과정을 반복한다.
        getPreOrder(pos + 1, inEnd, postStart + pos - inStart, postEnd - 1);
    }
}
