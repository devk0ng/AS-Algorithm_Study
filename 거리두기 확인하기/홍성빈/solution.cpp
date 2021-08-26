#include <bits/stdc++.h>

using namespace std;

int dx[4] = {1,0,-1,0};
int dy[4] = {0,1,0,-1};

bool is_corona_safe(vector<string> place) 
{
    char board[5][5];
    vector<vector<int> > visited(5, vector<int>(5,0));
    
    for(int i=0 ; i<5 ; i++)
        for(int j=0 ; j<place[i].size() ; j++)
            board[i][j] = place[i][j];
     
    for(int i=0 ; i<5 ; i++)
    {
        for(int j=0 ; j<5 ; j++)
        {
            if(board[i][j] == 'P' && visited[i][j] == 0)
            {
                queue<pair<int,int> > q;
                
                q.push(make_pair(i,j));
                visited[i][j] = 1;
                
                while(!q.empty())
                {
                    int x = q.front().first;
                    int y = q.front().second;
                    
                    q.pop();
                    
                    for(int k=0 ; k<4 ; k++)
                    {
                        if(x+dx[k]<0 || x+dx[k]>=5 || y+dy[k]<0 || y+dy[k]>=5)
                            continue;
                        
                        if(board[x+dx[k]][y+dy[k]] == 'X')
                            continue;
                        
                        if(visited[x+dx[k]][y+dy[k]] > 0)
                            continue;
                        
                        if(board[x+dx[k]][y+dy[k]] == 'P')
                        {
                            if(visited[x][y]<=2)
                                return false;
                            else
                                continue;
                        }
                        
                        visited[x+dx[k]][y+dy[k]] = visited[x][y]+1;
                        q.push(make_pair(x+dx[k], y+dy[k]));
                    }
                    
                    
                }
                
            }
        }
    }
    
    return true;
}

vector<int> solution(vector<vector<string>> places) {
    vector<int> answer;
    
    for(int i=0 ; i<places.size() ; i++)
    {
        if(is_corona_safe(places[i]))
            answer.push_back(1);
        else
            answer.push_back(0);
    }
    return answer;
}
