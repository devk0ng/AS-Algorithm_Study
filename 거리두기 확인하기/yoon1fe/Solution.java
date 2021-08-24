import java.util.*;

class Solution {
  final int LEN = 5;
  final int[] dy = {1, -1, 0, 0};
  final int[] dx = {0, 0, 1, -1};

  static class Dir {
    int y, x;
    Dir(int y, int x) {
      this.y = y; this.x = x;
    }
  }

  public int[] solution(String[][] places) {
    int[] answer = new int[LEN];

    for(int i = 0; i < LEN; i++) {
      answer[i] = makeResult(convertArray(places[i]));
    }

    return answer;
  }

  public int makeResult(char[][] place) {
    List<Dir> people = new ArrayList<>();

    // 리스트에 사람 위치 다 넣기
    for(int i = 0; i < LEN; i++) {
      for(int j = 0; j < LEN; j++) {
        if(place[i][j] == 'P') {
          people.add(new Dir(i, j));
        }
      }
    }

    // 한 사람 당 거리 체크 필요
    for(Dir person : people) {
      if(!isValidDistance(place, person)) return 0;
    }

    return 1;
  }

  public boolean isValidDistance(char[][] place, Dir start) {
    Queue<Dir> q = new LinkedList<>();
    int[][] dist = new int[LEN][LEN];

    q.offer(start);
    dist[start.y][start.x] = 1;

    while(!q.isEmpty()) {
      Dir cur = q.poll();

      for(int i = 0; i < 4; i++) {
        Dir next = new Dir(cur.y + dy[i], cur.x + dx[i]);
        // 맵 벗어나거나 || 이미 방문한 곳이거나 || 파티션의 경우
        if (!isIn(next) || dist[next.y][next.x] != 0 || place[next.y][next.x] == 'X') continue;

        dist[next.y][next.x] = dist[cur.y][cur.x] + 1;
        if (place[next.y][next.x] == 'P' && dist[next.y][next.x] <= 3) return false;

        q.offer(next);
      }
    }

    return true;
  }

  public boolean isIn(Dir c) {
    if(0 <= c.y && c.y < LEN && 0 <= c.x && c.x < LEN) return true;
    else return false;
  }

  public char[][] convertArray(String[] place) {
    char[][] map = new char[LEN][LEN];
    for(int i = 0; i < LEN; i++) {
      for(int j = 0; j < LEN; j++) {
        map[i][j] = place[i].charAt(j);
      }
    }

    return map;
  }

  public static void main(String[] args) {
    Solution s = new Solution();
    System.out.println(Arrays.toString(s.solution(new String[][]{
        {"POOOP",
         "OXXOX",
         "OPXPX",
         "OOXOX",
         "POXXP"},
        {"POOPX",
         "OXPXP",
         "PXXXO",
         "OXXXO",
         "OOOPP"},
        {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
        {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
        {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}}
    )));
  }
}