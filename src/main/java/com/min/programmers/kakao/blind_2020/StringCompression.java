package com.min.programmers.kakao.blind_2020;

public class StringCompression {

    //정확성 66점
    public static void main(String[] args) {
        System.out.println(solution("aabbaaaaaaaaaaaaaa"));
    }

    public static int solution(String s) {
        int answer = s.length();

        for (int i = 1; i <= s.length() / 2; i++) {
            answer = Math.min(answer, compression(s, i));
        }

        return answer;
    }

    public static int compression(String s, int count) {
        int length = 0;
        int currentIdx = count;
        int patternCnt = 1;
        String pattern = s.substring(0, count);
        String next = s.substring(currentIdx);
        while (currentIdx < s.length()) {
            if (next.startsWith(pattern)) {
                patternCnt++;
            } else {
                if (patternCnt > 1) {
                    length += String.valueOf(patternCnt).length();
                }
                length += pattern.length();
                if (currentIdx + count >= s.length()) {
                    length += s.substring(currentIdx).length();
                    break;
                }
                pattern = s.substring(currentIdx, currentIdx + count);
                patternCnt = 1;
            }
            currentIdx += count;
            next = s.substring(currentIdx);
        }
        if (patternCnt > 1) {
            length += String.valueOf(patternCnt).length();
            length += pattern.length();
        }
        return length;
    }
}
