#include <bits/stdc++.h>
using namespace std;

int solution(vector<int> stones, int k) {
    int answer = 1000000000, idx;

    for (int i = 0; i <= stones.size() - k; ++i) {
        int tmp = 0;

        for (int j = i; j < i + k; ++j)
            if (tmp < stones[j]) tmp = stones[j];

        if (answer > tmp) answer = tmp;
    }
    return answer;
}