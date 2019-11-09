#include <bits/stdc++.h>
using namespace std;

vector<int> solution(string s) {
    vector<int> answer;
    unordered_map<int, int> m;
    queue<int> q;

    for (int i = 2; i < s.length(); ++i) {
        if (isdigit(s[i])) q.push(s[i]);
        else {
            if (!q.empty()) {
                int size = q.size();
                string tmp;
                for (int pos = 0; pos < size; ++pos) {
                    tmp += q.front(); q.pop();
                }
                m[stoi(tmp)]++;
            }
        }
    }

    int idx = m.size();
    for (int i = 0; i < m.size(); ++i) {
        for (auto [key, value] : m) {
            if (value == idx) {
                answer.push_back(key); --idx;
            }
        }
    }

    return answer;
}