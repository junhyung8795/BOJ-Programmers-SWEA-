import sys

input = sys.stdin.readline
a = int(input())

for i in range(a):
    for j in range(a + i):
        if j >= a - 1 - i and j <= a - 1 + i:
            print("*", end="")
        elif j > a - 1 + i:
            break
        else: 
            print(" ", end="")
    if i != a - 1:
        print()