#include <vector>
#include <stack>
#include <string>
using namespace std;

stack<string> temporaryLocker; // 임시 보관함
stack<string> permanentLocker; // 영구 보관함

vector<string> solution(vector<string> record) {
    vector<string> ans;

    for (int i = 0; i < record.size(); i++) {
        string command;

        for (int j = 0; j < record[i].size(); j++)
            command += record[i][j];

        // 명령어에 따른 처리
        if (command[0] == 'R') {
            string emailAddress;
            for (int j = 8; j < command.length(); j++) {
                emailAddress += command[j];
            }
            temporaryLocker.push(emailAddress);
        }
        else if (command == "DELETE") {
            if (!temporaryLocker.empty())
                temporaryLocker.pop();
        }
        else if (command == "SAVE") {
            // 임시 보관함 -> 영구 보관함으로 이동
            stack<string> tmp;
            int size = temporaryLocker.size();
            for (int j = 0; j < size; j++) {
                tmp.push(temporaryLocker.top());
                temporaryLocker.pop();
            }

            for (int j = 0; j < size; j++) {
                permanentLocker.push(tmp.top());
                tmp.pop();
            }
        }
    }

    vector<string> tmp;
    int permanentSize = permanentLocker.size();
    for (int i = 0; i < permanentSize; i++) {
        tmp.push_back(permanentLocker.top());
        permanentLocker.pop();
    }

    for (int i = tmp.size() - 1; i >= 0; i--) {
        ans.push_back(tmp[i]);
    }

    return ans;
}