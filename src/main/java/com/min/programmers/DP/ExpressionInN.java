package main.java.com.min.programmers.DP;

public class ExpressionInN {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(1, 1));
    }

    static class Solution {
        static int answer = Integer.MAX_VALUE;

        public int solution(int N, int number) {
            dfs(0, number, 0, N);
            if (answer == Integer.MAX_VALUE) {
                return -1;
            }
            return answer;
        }

        public void dfs(int cur, int number, int length, int N) {
            if (length > 8) {
                return;
            }
            if (cur == number) {
                answer = Math.min(answer, length);
                return;
            }

            int attachedN = 0;
            for (int i = 0; i < 8; i++) {
                attachedN = attachedN * 10 + N;
                for (int j = 1; j <= 4; j++) {
                    dfs(calc(cur, attachedN, j), number, length + i + 1, N);
                }
            }
        }

        public int calc(int opr1, int opr2, int opt) {
            int result = 0;
            switch (opt) {
                case 1:
                    result = opr1 + opr2;
                    break;
                case 2:
                    result = opr1 - opr2;
                    break;
                case 3:
                    result = opr1 * opr2;
                    break;
                case 4:
                    result = opr1 / opr2;
                    break;
            }
            return result;
        }
    }

}
