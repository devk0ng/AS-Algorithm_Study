import java.util.*;

class Solution {

    static class Pos{
        int x,y,cost;
        Pos(int x, int y, int cost){
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }
    static int[] dx = {-1,0,1,0}; static int[] dy = {0,-1,0,1};
    static ArrayList<Pos> people = new ArrayList<>();
    static char[][] board = new char[5][5];

    public int[] solution(String[][] places) {
        int[] answer = {0,0,0,0,0};
        int idx=0;

        for (String[] place : places){
            for (int i=0; i<5; i++){
                String row = place[i];
                for(int j=0; j<5; j++){
                    char ch = row.charAt(j);
                    if(ch=='P'){
                        people.add(new Pos(i,j,0));
                    }
                    board[i][j] = ch;
                }
            }
            answer[idx++] = solve();
            people.clear();
        }

        return answer;
    }

    public static int solve(){

        Queue<Pos> q = new LinkedList<>();

        for(int i=0; i<people.size(); i++){
            Pos now = people.get(i);
            q.offer(now);

            boolean[][] visited = new boolean[5][5];
            while(!q.isEmpty()){
                Pos cur = q.poll();
                int x = cur.x;
                int y = cur.y;
                int cost = cur.cost;

                visited[x][y]=true;
                for(int idx=0; idx<4; idx++){
                    int nx = x + dx[idx];
                    int ny = y + dy[idx];
                    if(check(nx,ny) && !visited[nx][ny] && board[nx][ny]!='X'){
                        if(board[nx][ny]=='P'){
                            return 0;
                        }
                        else if(board[nx][ny]=='O' && cost<1){
                            visited[nx][ny]= true;
                            q.offer(new Pos(nx,ny,cost+1));
                        }
                    }
                }
            }
        }

        return 1;
    }

    public static boolean check(int nx, int ny){
        if(0<=nx && nx<5 && 0<=ny && ny<5)
            return true;
        return false;
    }
}

public class Main {
    public static void main(String[] args) {
        String[][] places = {
                {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}
        };

        System.out.println(new Solution().solution(places));
    }
}