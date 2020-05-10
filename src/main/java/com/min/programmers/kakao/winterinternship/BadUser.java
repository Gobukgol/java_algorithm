package main.java.com.min.programmers.kakao.winterinternship;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class BadUser {
    static List<Set<String>> answerSet = new ArrayList<>();

    public static void main(String[] args) {

        Solution solution = new Solution();
        String[] user_id = {"frodo", "crodo", "abcd123"};
        String[] banned_id = {"*rodo"};
        System.out.println(solution.solution(user_id, banned_id));
        ;
    }

    static class Solution {
        public int solution(String[] user_id, String[] banned_id) {
            boolean[] visited = new boolean[user_id.length];
            dfs(user_id, banned_id, visited, 0);

            return answerSet.size();
        }

        public void dfs(String[] user_id, String[] banned_id, boolean[] visited, int n) {
            if (n == banned_id.length) {
                Set<String> cur = new HashSet<>();

                for (int i = 0; i < visited.length; i++) {
                    if (visited[i]) {
                        cur.add(user_id[i]);
                    }
                }

                if (!answerSet.contains(cur)) {
                    answerSet.add(cur);
                }
                return;
            }

            String match = banned_id[n].replace("*", ".");
            Pattern pattern = Pattern.compile(match);
            for (int i = 0; i < user_id.length; i++) {
                if (pattern.matcher(user_id[i]).matches() && !visited[i]) {
                    visited[i] = true;
                    dfs(user_id, banned_id, visited, n + 1);
                    visited[i] = false;
                }
            }
        }
    }
}
