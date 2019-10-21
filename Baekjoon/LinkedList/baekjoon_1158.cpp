#include <iostream>
#include <list>
using namespace std;

int N, K;
list<int> lt;

int main() {
    cin >> N >> K;

    for (int i = 1; i <= N; i++) {
        lt.push_back(i);
    }

    list<int>::iterator iter = lt.begin();
    for (int i = 0; i < K - 1; i++) iter++;

    cout << "<";
    while (true) {
        cout << *iter;
        iter = lt.erase(iter);

        if (lt.empty()) {
            cout << ">";
            break;
        }
        else cout << ", ";

        if (iter == lt.end())
            iter = lt.begin();

        for (int i = 0; i < K - 1; i++) {
            iter++;
            if (iter == lt.end()) iter = lt.begin();
        }
    }

    return 0;
}