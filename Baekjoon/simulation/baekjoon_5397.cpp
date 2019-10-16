#include <iostream>
#include <stack>
using namespace std;

int T;

int main() {
    cin >> T;

    while (T--) {
        stack<char> s1, s2;
        string str, password;
        cin >> str;

        for (int i = 0; i < str.length(); i++) {
            if (str[i] == '<') {
                if (!s1.empty()) {
                    s2.push(s1.top());
                    s1.pop();
                }
            }
            else if (str[i] == '>') {
                if (!s2.empty()) {
                    s1.push(s2.top());
                    s2.pop();
                }
            }
            else if (str[i] == '-') {
                if (!s1.empty())
                    s1.pop();
            }
            else {
                s1.push(str[i]);
            }
        }

        while (s1.size()) {
            s2.push(s1.top());
            s1.pop();
        }

        while (s2.size()) {
            password += s2.top();
            s2.pop();
        }

        cout << password << "\n";
    }

    return 0;
}
