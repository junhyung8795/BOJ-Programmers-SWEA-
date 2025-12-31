#include <bits/stdc++.h>
using namespace std;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    
    int N,M;
    cin >> N >> M;
    int arr[N];
    
    for(int i = 0;  i < N; i++){
        cin >> arr[i];   
    }
    
    for(int i = 0; i < N; i++){
        if(arr[i] < M){
            cout << arr[i] << " ";
        }
    }
    
    
    return 0;
}