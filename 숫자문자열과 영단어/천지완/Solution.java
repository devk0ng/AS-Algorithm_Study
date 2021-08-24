import java.util.*;

class Solution {

    static HashMap<String,Integer> map;

    public int solution(String s) {
        StringBuilder answer = new StringBuilder();
        StringBuilder str = new StringBuilder();

        init();

        for (int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            if('a'<= ch && ch<='z'){
                str.append(ch);
                if(map.get(str.toString())!=null){
                    answer.append(map.get(str.toString()));
                    str.setLength(0);
                }
            }
            else if('0'<= ch && ch<='9'){
                answer.append(ch);
            }
        }
        return Integer.parseInt(answer.toString());
    }

    public static void init(){
        map = new HashMap<>();
        map.put("zero",0);
        map.put("one",1);
        map.put("two",2);
        map.put("three",3);
        map.put("four",4);
        map.put("five",5);
        map.put("six",6);
        map.put("seven",7);
        map.put("eight",8);
        map.put("nine",9);
    }
}

public class Main {
    public static void main(String[] args) {
        String s = "one4seveneight";

        System.out.println(new Solution().solution(s));
    }
}