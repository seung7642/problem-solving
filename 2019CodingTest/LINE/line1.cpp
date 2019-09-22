#include <iostream>
#include <queue>
using namespace std;

int messageCount;    // 메시지 수
int consumerCount;   // 컨슈머 수
int messageTime[10]; // 메시지별 걸리는 시간
queue<int> q[10];
int exeTime;

int main(void) {
	cin >> messageCount >> consumerCount;

	for (int i = 0; i < messageCount; i++)
		cin >> messageTime[i];


	for (int i = 0; i < messageCount; i++) {
		bool emptyCheck = false;
		for (int idx = 0; idx < consumerCount; idx++) { // 비어있는 큐가 있는지 확인하고, 있다면 거기에 먼저 담는다.
			if (q[idx].empty()) {
				emptyCheck = true;
				for (int processTime = 0; processTime < messageTime[i]; processTime++) q[idx].push(1);
				break;
			}
		}
		if (emptyCheck) continue;

		int minQueueSize = 1000000000;
		int idx = 0; // 가장 작은 크기의 큐의 인덱스 값
		for (int k = 0; k < consumerCount; k++) // 가장 작은 크기를 가지는 큐를 구한다.
			if (minQueueSize > q[k].size()) {
				minQueueSize = q[k].size();
				idx = k;
			}

		for (int processTime = 0; processTime < messageTime[i]; processTime++) q[idx].push(1);
	}

	int maxQueueSize = 0;
	for (int idx = 0; idx < consumerCount; idx++)
		if (maxQueueSize < q[idx].size())
			maxQueueSize = q[idx].size();

	cout << maxQueueSize << "\n";

	return 0;
}