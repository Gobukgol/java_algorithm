package main.java.com.min.programmers.kakao.winterinternship;

import java.util.Stack;

public class CrainDoll {

    static class Solution {
        public int solution(int[][] board, int[] moves) {
            int answer = 0;

            Stack<Integer> stack = new Stack<>();

            for (int i = 0; i < moves.length; i++) {
                int move = moves[i];

                for (int j = 0; j < board.length; j++) {
                    if (board[j][move - 1] != 0) {
                        if (!stack.isEmpty() && stack.peek() == board[j][move - 1]) {
                            stack.pop();
                            answer += 2;
                        } else {
                            stack.push(board[j][move - 1]);
                        }
                        board[j][move - 1] = 0;
                        break;
                    }
                }

            }

            return answer;
        }
    }
}
