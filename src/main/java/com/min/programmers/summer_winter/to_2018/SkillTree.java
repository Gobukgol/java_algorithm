package main.java.com.min.programmers.summer_winter.to_2018;

import java.util.ArrayList;
import java.util.List;

public class SkillTree {

    static class Solution {
        public int solution(String skill, String[] skill_trees) {
            int answer = 0;

            List<Character> skills = new ArrayList<>();
            for (int i = 0; i < skill.length(); i++) {
                skills.add(skill.charAt(i));
            }
            for (int i = 0; i < skill_trees.length; i++) {
                int treeIdx = 0;
                boolean check = false;
                char[] treeArr = skill_trees[i].toCharArray();
                for (int j = 0; j < treeArr.length; j++) {
                    if (skills.contains(treeArr[j])) {
                        if (skills.get(treeIdx) == treeArr[j]) {
                            treeIdx++;
                        } else {
                            check = true;
                            break;
                        }
                    }
                }
                if (!check) {
                    answer++;
                }
            }

            return answer;
        }
    }
}
