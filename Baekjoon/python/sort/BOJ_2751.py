from sys import stdin

def merge_sort(arr):
    if len(arr) <= 1:
        return arr
    mid = len(arr) // 2
    left = merge_sort(arr[:mid])
    right = merge_sort(arr[mid:])

    left_idx, right_idx, k = 0, 0, 0

    while left_idx < len(left) and right_idx < len(right):
        if left[left_idx] < right[right_idx]:
            arr[k] = left[left_idx]
            left_idx += 1
        else:
            arr[k] = right[right_idx]
            right_idx += 1
        k += 1

    if left_idx == len(left):
        while right_idx < len(right):
            arr[k] = right[right_idx]
            right_idx += 1
            k += 1
    elif right_idx == len(right):
        while left_idx < len(left):
            arr[k] = left[left_idx]
            left_idx += 1
            k += 1
    return arr

N = int(stdin.readline())
arr = []
for _ in range(N):
    arr.append(int(stdin.readline()))

arr = merge_sort(arr)

for val in arr:
    print(val)
