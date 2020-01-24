package com.min.programmers.kakao.blind_2020;

//정확성 70%
public class WallInspectation {
    private static int N;
    private static int answer = -1;

    public static void main(String[] args) {
        N = 4;
        int[] weak = {1, 2, 3};
        int[] dist = {2, 1};
        solution(N, weak, dist);
        System.out.println(answer);
    }

    public static int solution(int n, int[] weak, int[] dist) {
        int[] reuslt = new int[dist.length];
        boolean[] visited = new boolean[dist.length];

        distPermutaion(n, dist, reuslt, visited, 0, weak);

        return answer;
    }

    public static void distPermutaion(int n, int[] dist, int[] result, boolean[] visited, int depth, int[] weak) {
        if (depth == dist.length) {
            //weak 계산하기
            for (int i = 0; i < weak.length; i++) {
                int[] sorted = weakPointSort(weak, i, n);
                int idx = 0;
                for (int j = 0; j < result.length; j++) {
                    int currPosition = sorted[idx] + result[j];

                    while (idx < sorted.length && currPosition > sorted[idx]) {
                        idx++;
                    }
                    if (idx == sorted.length) {
                        if (answer == -1) {
                            answer = j + 1;
                        } else {
                            answer = Math.min(answer, j + 1);
                        }
                        break;
                    }
                }
            }
            return;
        }

        for (int i = 0; i < dist.length; i++) {
            if (!visited[i]) {
                result[depth] = dist[i];
                visited[i] = true;
                distPermutaion(n, dist, result, visited, depth + 1, weak);
                visited[i] = false;
            }
        }
    }

    public static int[] weakPointSort(int[] weak, int startIdx, int n) {
        int[] sorted = new int[weak.length];

        int k = 0;
        while (k < sorted.length) {
            for (int i = startIdx; i < weak.length; i++, k++) {
                sorted[k] = weak[i];
            }
            for (int i = 0; i < startIdx; i++, k++) {
                sorted[k] = weak[i] + n;
            }
        }

        return sorted;
    }
}
