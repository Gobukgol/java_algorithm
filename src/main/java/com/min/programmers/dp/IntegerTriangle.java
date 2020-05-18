package main.java.com.min.programmers.dp;

public class IntegerTriangle {
    public static void main(String[] args) {

    }

    static class Solution {
        public int solution(int[][] triangle) {

            //왼쪽 초기화
            for (int i = 1; i < triangle.length; i++) {
                triangle[i][0] += triangle[i - 1][0];
            }

            //오른쪽 초기화
            for (int i = 1; i < triangle.length; i++) {
                triangle[i][i] += triangle[i - 1][i - 1];
            }

            //가운데 계산

            for (int i = 1; i < triangle.length; i++) {
                for (int j = 1; j < i; j++) {
                    triangle[i][j] += Math.max(triangle[i - 1][j - 1], triangle[i - 1][j]);
                }
            }

            //최댓값 계산
            int lastDepth = triangle.length - 1;
            int answer = Integer.MIN_VALUE;
            for (int i = 0; i < triangle[lastDepth].length; i++) {
                answer = Math.max(answer, triangle[lastDepth][i]);
            }

            return answer;
        }
    }
}
