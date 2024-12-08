import math
import sys
sys.setrecursionlimit(10 ** 6)
input = lambda: sys.stdin.readline().rstrip()

def find_sequence(n, k, stack):
    if len(stack) == N:
        print(' '.join(list(map(str, stack))))
        return
    each_sub_sequence = math.factorial(n) // n
    front_order = math.ceil(k / each_sub_sequence)
    find_sequence(n - 1, k - (each_sub_sequence * (front_order - 1)), stack + [numbers.pop(front_order)])

def find_kth(n, sequence, cnt):
    if len(sequence) == 0:
        print(cnt + 1)
        return
    each_sub_sequence = math.factorial(n) // n
    front_idx = numbers.index(sequence.pop(0))
    numbers.pop(front_idx)
    find_kth(n - 1, sequence, cnt + each_sub_sequence * front_idx)

N = int(input())
input_arr = list(map(int, input().split()))

if input_arr[0] == 1:
    K = input_arr[1]
    numbers = [x for x in range(N + 1)]
    find_sequence(N, K, [])
else:
    numbers = [x for x in range(1, N + 1)]
    find_kth(N, input_arr[1:], 0)
