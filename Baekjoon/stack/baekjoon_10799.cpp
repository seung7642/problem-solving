#include <iostream>
#include <string>
#include <stack>
using namespace std;

stack<char> st;
string str;
bool flag; // 최근에 스택에 들어온 값이 '('인지 ')'인지 구분해주기 위한 플래그
int ans;

int main() {
    cin >> str;

    for (int i = 0; i < str.length(); i++) {
        if (str[i] == '(') {
            st.push(str[i]);
            flag = false;
        }
        else if (str[i] == ')') {
            st.pop();
            if (flag) ans += 1;
            else ans += st.size();

            flag = true;
        }
    }

    cout << ans << "\n";

    return 0;
}