import java.util.*;

class Solution {

  public String solution(int n, int k, String[] cmd) {
    StringBuilder sb = new StringBuilder();
    Stack<Integer> stack = new Stack<>();     // 행 번호 저장
    int tableSize = n;

    for (String s : cmd) {
      char op = s.charAt(0);

      if (op == 'C') {
        stack.push(k);
        tableSize--;
        if (k == tableSize) {
          k--;
        }
      } else if (op == 'Z') {
        int undoRow = stack.pop();
        tableSize++;

        if (k >= undoRow) {
          k++;
        }
      } else if (op == 'D') {
        k = (k + Integer.parseInt(s.split(" ")[1])) % tableSize;
      } else {
        k = (k - Integer.parseInt(s.split(" ")[1])) % tableSize;
      }
    }

    for(int i = 0; i < tableSize; i++) {
      sb.append("O");
    }

    while(!stack.isEmpty()) {
      sb.insert(stack.pop(), "X");
    }

    return sb.toString();
  }

  public static void main(String[] args) {
    Solution s = new Solution();
    System.out.println(s.solution(8, 2, new String[]{"D 2","C","U 3","C","D 4","C","U 2","Z","Z", "U 1","C"}));
  }
}