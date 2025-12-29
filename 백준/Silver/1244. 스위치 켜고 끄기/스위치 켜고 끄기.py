import sys

input = sys.stdin.readline
N = int(input())
switches = list(map(int, input().split(" ")))

M = int(input())
orders = list()
for i in range(M):
    order = list(map(int, input().split(" ")))
    orders.append(order)


for order in orders:
    gender = order[0]
    pos = order[1]

    if gender == 1:
        for i in range(N):
            if (i + 1) % pos == 0:
                switches[i] ^= 1
    else:
        for i in range(N):
            if pos - 1 + i < N and pos - 1 - i >= 0:
                if switches[pos - 1 + i] != switches[pos - 1 - i]:
                    break
                elif pos - 1 + i == pos - 1 - i:
                    switches[pos - 1 + i] ^= 1
                else: 
                    switches[pos - 1 + i] ^= 1
                    switches[pos - 1 - i] ^= 1
for i in range(N):
    print(switches[i], end = " ")
    if (i + 1) % 20 == 0:
        print()