#include <bits/stdc++.h>
using namespace std;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);


    int N;
    cin >> N;

    int arr[N];
    for(int i =0 ; i < N; i++){
        cin >> arr[i];
    }

    sort(arr, arr + N);
    cout << arr[0] <<" " << arr[N - 1];
    return 0;
}