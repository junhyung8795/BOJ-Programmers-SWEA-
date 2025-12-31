#include <bits/stdc++.h>

using namespace std;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    
    int N;
    int* arr;
    
    cin >> N;
    arr = new int[N];
    for(int i = 0; i < N; i++){
        cin >> arr[i];
    }
    
    int y = 0, m = 0;
    for(int i = 0; i < N; i++){
        
        y += (arr[i] / 30 + 1) * 10; 
        
    }
    
    for(int i = 0; i < N; i++){
        
        m += (arr[i] / 60 + 1) * 15; 
        
    }
    
    
    if(y < m){
        cout << "Y" << " " << y;
    } else if(y > m){
        cout << "M" << " " << m;
    } else{
         cout <<"Y" << " "<< "M" << " " << m;
    }
    
    
    
    return 0;
}