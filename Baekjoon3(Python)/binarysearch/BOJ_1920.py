from sys import stdin, stdout
N = int(stdin.readline())
arr = sorted(map(int, stdin.readline().split()))
M = int(stdin.readline())
findArr = map(int, stdin.readline().split())

def binary(num, start, end):
    if start > end:
        return 0
    mid = (start + end) // 2
    if num == arr[mid]:
        return 1
    elif num < arr[mid]:
        return binary(num, start, mid - 1)
    else:
        return binary(num, mid + 1, end)

for num in findArr:
    start = 0
    end = len(arr) - 1
    print(binary(num, start, end))
