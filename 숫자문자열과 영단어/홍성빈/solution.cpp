#include <bits/stdc++.h>

using namespace std;

int solution(string s) {
    map<string, string> convert_map;
    
    convert_map["zero"]="0";convert_map["one"]="1";convert_map["two"]="2";
    convert_map["three"]="3";convert_map["four"]="4";convert_map["five"]="5";
    convert_map["six"]="6";convert_map["seven"]="7";convert_map["eight"]="8";
    convert_map["nine"]="9";
    
    string result = "";
    string temp = "";
    
    for(int i=0 ; i<s.size() ; i++)
    {
        if(convert_map.find(temp) != convert_map.end())
        {
            result += convert_map[temp];
            temp = "";
        }
        
        if(s[i] >= '0' && s[i] <= '9')
        {
            result += s[i]; 
        }
        else
        {
            temp += s[i];
        }
    }
    
    if(convert_map.find(temp) != convert_map.end())
    {
        result += convert_map[temp];
    }
    
    return stoi(result);
}
