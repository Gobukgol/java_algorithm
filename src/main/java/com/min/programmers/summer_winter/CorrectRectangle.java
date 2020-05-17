package main.java.com.min.programmers.summer_winter;

public class CorrectRectangle {

    static class Solution {
        public long solution(int w, int h) {
            long answer = 1;

            long g = gcd(w, h);

            answer = ((long) w * (long) h) - (((long) w + (long) h) - g);

            return answer;
        }

        public long gcd(int w, int h) {
            long a = Math.max(w, h);
            long b = Math.min(w, h);
            long n;
            while (b != 0) {
                n = a % b;
                a = b;
                b = n;
            }

            return a;
        }
    }
}
