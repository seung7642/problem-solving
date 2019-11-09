#include <bits/stdc++.h>
using namespace std;

int f(int n) {
    if (n == 1) return 0;

    int ret = 0;
    while (n) ret++, n /= 10;
    return ret;
}

int solution(string s) {
    int answer = s.size(), n = s.size();
    unordered_set<string> S; // hashset

    for (int i = 1; i * 2 <= n; ++i) {
        S.clear();
        int j, len = 0, cnt = 1;
        string prv = s.substr(0, i);
        S.insert(prv);

        for (j = i; j < n; j += i) {
            if (S.find(s.substr(j, i)) != S.end()) ++cnt;
            else {
                len += f(cnt) + i;
                S.erase(prv);
                S.insert(prv = s.substr(j, i));
                cnt = 1;
            }
        }

        len += cnt > 1 ? f(cnt) + i : n - j + i;
        answer = min(answer, len);
    }
    return answer;
}