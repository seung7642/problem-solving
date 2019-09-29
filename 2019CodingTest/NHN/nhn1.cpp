#include <iostream>
#include <map>
#include <string>
using namespace std;

int N; // 보유중인 카드의 갯수 <= 1000
string card;
map<string, int> m;
int chk[1001]; // i개의 갯수를 가지는 카드가 몇 개인지 저장한다.

int main() {
    cin >> N;

    for (int i = 0; i < N; i++) {
        cin >> card;
        m[card]++;
    }

    // 총 3가지의 경우가 있다.
    //     1. 애초에 모든 value값이 같은 경우
    //     2. 어떤 하나의 value값에 +1을 했을 때 모든 value값이 같아지는 경우
    //     3. 같아질 수 없는 경우

    bool isEqual = true;
    auto standardIter = m.begin(); // 기준값을 잡는다.
    for (auto iter = m.begin(); iter != m.end(); iter++) {
        if (standardIter->second != iter->second) {
            isEqual = false;
            break;
        }
    }

    if (isEqual) { // 1. 애초에 모든 value값이 같은 경우
        cout << "Y\n";

        int sum = 0;
        for (auto iter = m.begin(); iter != m.end(); iter++)
            sum += iter->second;

        cout << sum << "\n";
        cout << m.size() << "\n";
    }
    else {
        for (auto i = m.begin(); i != m.end(); i++) {
            isEqual = true;
            i->second++;

            for (auto j = m.begin(); j != m.end(); j++) {
                if (i->second != j->second) {
                    isEqual = false;
                    break;
                }
            }

            if (isEqual) { // 2. 어떤 하나의 value값에 +1을 했을 때 모든 value값이 같아지는 경우
                cout << "Y\n";
                cout << i->second * m.size() << "\n";
                cout << m.size() << "\n";
                break;
            }

            i->second--;
        }

        if (!isEqual) {
            cout << "N\n";

            int sum = 0;
            for (auto iter = m.begin(); iter != m.end(); iter++)
                sum += iter->second;

            cout << sum << "\n";
            cout << m.size() << "\n";
        }
    }

    return 0;
}