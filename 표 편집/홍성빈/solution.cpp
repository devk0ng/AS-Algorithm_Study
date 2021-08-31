#include <bits/stdc++.h>

using namespace std;


stack<int> erased_list;
set<int>::iterator cur_pos;

void cmd_D(set<int> &list, int dis)
{
    for(int i=0 ; i<dis ; i++)
        cur_pos++;
}

void cmd_U(set<int> &list, int dis)
{
    for(int i=0 ; i<dis ; i++)
        cur_pos--;
}

void cmd_C(set<int> &list)
{
    erased_list.push(*cur_pos);
    cur_pos = list.erase(cur_pos);
    
    if(cur_pos == list.end())
        cur_pos--;
        
}

void cmd_Z(set<int> &list)
{
    int cur_data = *cur_pos;
    
    int recover_data = erased_list.top();
    erased_list.pop();
    
    list.insert(recover_data);
    
    cur_pos = list.find(cur_data);

}

string solution(int n, int k, vector<string> cmd) {
    string answer = "";
    
    set<int> list;
    vector<int> check(n, 0);
    
    for(int i=0 ; i<n ; i++)
        list.insert(i);
    
    cur_pos = list.begin();
    
    for(int i=0 ; i<k ; i++)
        cur_pos++;
  
    for(int i=0 ; i<cmd.size() ; i++)
    {
        if(cmd[i][0] == 'D')
        {
            cmd_D(list, stoi(cmd[i].substr(2)));
        }
        else if(cmd[i][0] == 'U')
        {
            cmd_U(list, stoi(cmd[i].substr(2)));
        }
        else if(cmd[i][0] == 'C')
        {
            cmd_C(list);
        }
        else if(cmd[i][0] == 'Z')
        {
            cmd_Z(list);
        }
    }
    
    
    for(auto iter = list.begin() ; iter != list.end() ; iter++)
    {
        check[*iter] = 1;
    }
    
    for(int i=0 ; i<check.size() ; i++) {
        if(check[i] == 1) {
            answer += "O";
        } else {
            answer += "X";
        }
    }
    
    return answer;
}
