#include <bits/stdc++.h>
using namespace std;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);


    int N;
    cin >> N;

    int arr[N];
    int m = INT_MIN;
    int n = INT_MAX;
    
    for(int i =0 ; i < N; i++){
        cin >> arr[i];
        m = max(m, arr[i]);
        n = min(n, arr[i]);
    }

    
    cout << n <<" " <<m;
    return 0;
}