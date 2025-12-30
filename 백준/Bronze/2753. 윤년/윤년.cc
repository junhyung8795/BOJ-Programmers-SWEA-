#include <bits/stdc++.h>
using namespace std;

int main(){
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    int year = 0;

    cin >> year;
    
    // 윤년
    if(year % 4 == 0 && year % 100 != 0){
        cout << 1;
    } else if( year % 400 == 0){
        cout << 1;
    } else{
        cout << 0;
    }
    
    return 0;
}