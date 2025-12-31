#include <bits/stdc++.h>
using namespace std;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);

    vector<int> arr(41);
    vector<int> nums(10);
    for(int i = 0; i < 10; i++){
        cin >> nums[i];
        arr[nums[i] % 42] += 1;
        
    }

    int answer = 0;
    for(int i = 0; i < 42; i++){
        if(arr[i] != 0 ){
            answer++;
        }
    }

    cout << answer;
    


    return 0;
}