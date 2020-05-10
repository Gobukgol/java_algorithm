package main.java.com.min.programmers.kakao.winterinternship;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Hotel {

    public static void main(String[] args) {
        long k = 10;
        long[] room_number = {1, 3, 4, 1, 3, 1};

        Solution solution = new Solution();

        long[] answer = solution.solution(k, room_number);

        System.out.println(Arrays.toString(answer));
    }

    static class Solution {
        Map<Long, Long> rooms = new HashMap<>();

        public long[] solution(long k, long[] room_number) {
            long[] answer = new long[room_number.length];

            for (int i = 0; i < room_number.length; i++) {
                answer[i] = findAvailable(room_number[i]);
            }

            return answer;
        }

        public long findAvailable(long roomNum) {

            if (rooms.get(roomNum) == null) {
                rooms.put(roomNum, roomNum + 1);
                return roomNum;
            }

            long next = findAvailable(rooms.get(roomNum));
            rooms.replace(roomNum, next);

            return next;
        }
    }
}
