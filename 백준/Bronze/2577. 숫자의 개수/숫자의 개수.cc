#include <bits/stdc++.h>
using namespace std;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);

    int a,b,c;
    cin >> a >> b >> c;

    // 0부터 9까지의 배열 선언
    int* arr = new int[10];

    int result = a * b * c;
    string strResult = to_string(result);
   
    
    for(char a: strResult){
        int index = a - '0';
        arr[index] += 1;
    }

    for(int i = 0; i < 10; i++){
        cout << arr[i]<< "\n";;
    }

    return 0;
}