
import java.util.HashMap;
import java.util.Map;

public class Solution1 {
    public Map<String, Integer> ftoi;

    public int solution(String[] friends, String[] gifts) {
        int maxGifts = 0;

        ftoi = new HashMap<>(); //이름 -> 인덱스 번호로 변환
        int size = friends.length;

        for(int i = 0; i < size; i++){
            ftoi.put(friends[i], i);
        }

        //준 사람, 받은 사람의 2차원 배열
        int[][] info = new int[size][size];
        for(String gift: gifts) {
            String[] ab = gift.split(" ");

            int send = ftoi.get(ab[0]);
            int receiver = ftoi.get(ab[1]);

            info[send][receiver]++;
        }

        //각 인덱스가 준 선물, 받은 선물, 선물 지수 계산
        int[][] value = new int[size][3];

        for(int i = 0; i < size; i++){
            int sendSum = 0;
            for(int j = 0; j < size; j++){
                sendSum += info[i][j]; //준 선물 수 계산
            }
            value[i][0] = sendSum;
        }

        for(int i = 0; i < size; i++){
            int receiveSum = 0;
            for(int j = 0; j < size; j++){
                receiveSum += info[j][i];
            }
            value[i][1] = receiveSum;
            value[i][2] = value[i][0] - receiveSum; //선물 지수 = 준 선물 - 받은 선물
        }

        //다음달에 각 인덱스가 받을 선물 저장
        int[] cnt = new int[size];

        for(int i = 0; i < size; i++){
            for(int j = i + 1; j < size; j++){
                if(info[i][j] != info[j][i]){
                    if(info[i][j] > info[j][i]){
                        cnt[i]++; //준 선물 수 : i > j
                    }else{
                        cnt[j]++; //준 선물 수 : i < j
                    }
                }else { // 준 선물 수 : i == j -> 선물 지수 큰 사람이 받음
                    if(value[i][2] != value[j][2]){
                        if(value[i][2] > value[j][2]){
                            cnt[i]++;
                        }else {
                            cnt[j]++;
                        }
                    }
                }
            }
        }

        //가장 많이 받은 선물
        for(int c: cnt){
            maxGifts = Math.max(maxGifts, c);
        }

        return maxGifts;
    }

    public static void main(String[] args) {
        Solution1 sol = new Solution1();

        // Example 1
        String[] friends1 = { "muzi", "ryan", "frodo", "neo" };
        String[] gifts1 = { "muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi",
                "frodo ryan", "neo muzi" };
        System.out.println("Example 1 Result: " + sol.solution(friends1, gifts1)); // Expected: 2

        // Example 2
        String[] friends2 = { "joy", "brad", "alessandro", "conan", "david" };
        String[] gifts2 = { "alessandro brad", "alessandro joy", "alessandro conan", "david alessandro",
                "alessandro david" };
        System.out.println("Example 2 Result: " + sol.solution(friends2, gifts2)); // Expected: 4

        // Example 3
        String[] friends3 = { "a", "b", "c" };
        String[] gifts3 = { "a b", "b a", "c a", "a c", "a c", "c a" };
        System.out.println("Example 3 Result: " + sol.solution(friends3, gifts3)); // Expected: 0
    }
}