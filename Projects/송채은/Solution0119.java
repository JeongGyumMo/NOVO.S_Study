import java.util.*;

class Solution0119 {
    public long solution(int[] weights) {
        long answer = 0;

        // 몸무게 개수 저장
        Map<Integer, Long> map = new HashMap<>();
        for (int i = 0; i < weights.length; i++) {
            if (map.containsKey(weights[i])) {
                map.put(weights[i], map.get(weights[i]) + 1);
            } else {
                map.put(weights[i], 1L);
            }
        }

        List<Integer> keys = new ArrayList<>(map.keySet());

        // 같은 무게
        for (int i = 0; i < keys.size(); i++) {
            long cnt = map.get(keys.get(i));
            if (cnt >= 2) {
                answer += cnt * (cnt - 1) / 2;
            }
        }

        // 다른 무게
        for (int i = 0; i < keys.size(); i++) {
            int w = keys.get(i);

            // 2 : 3
            if (w * 3 % 2 == 0) {
                int target = w * 3 / 2;
                if (w < target && map.containsKey(target)) {
                    answer += map.get(w) * map.get(target);
                }
            }

            // 2 : 4
            int target24 = w * 2;
            if (w < target24 && map.containsKey(target24)) {
                answer += map.get(w) * map.get(target24);
            }

            // 3 : 4
            if (w * 4 % 3 == 0) {
                int target = w * 4 / 3;
                if (w < target && map.containsKey(target)) {
                    answer += map.get(w) * map.get(target);
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution0119 sol = new Solution0119();

        int[] weights = {100, 180, 360, 100, 270};
        System.out.println("Example Result: " + sol.solution(weights));
    }
}
