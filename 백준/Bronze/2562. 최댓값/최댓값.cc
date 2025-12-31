#include <bits/stdc++.h>

using namespace std;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);

    int* arr = new int[9];

    for(int i = 0; i < 9; i++){
        cin  >> arr[i];
    }

    int a = arr[0];
    int index = 0;
    for(int i = 0; i < 9; i++){
        if(a < arr[i]){
            index = i;
            a = arr[i];
        }
    }
    
    cout << a << "\n";
    cout << index + 1;
    return 0;
}