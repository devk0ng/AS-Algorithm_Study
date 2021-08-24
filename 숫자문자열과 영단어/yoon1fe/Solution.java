class Solution {
  public int solution(String s) {
    String[] arry = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    for(int i = 0; i < arry.length; i++) {
      s = s.replaceAll(arry[i], String.valueOf(i));
    }

    return Integer.parseInt(s);
  }

  public static void main(String[] args) {
    Solution solution = new Solution();

    System.out.println(solution.solution("one4seveneight"));
  }
}