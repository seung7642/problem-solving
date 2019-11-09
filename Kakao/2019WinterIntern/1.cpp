#include <bits/stdc++.h>
using namespace std;

int solution(vector<vector<int>> board, vector<int> moves) {
    int answer = 0;
    stack<int> st;

    for (auto pos : moves) {
        for (int i = 0; i < board[0].size(); ++i) {
            int val = board[i][pos - 1];

            if (val != 0) {
                if (!st.empty() && st.top() == val) {
                    st.pop(); answer += 2;
                }
                else st.push(val);
                board[i][pos - 1] = 0;
                break;
            }
        }
    }

    return answer;
}