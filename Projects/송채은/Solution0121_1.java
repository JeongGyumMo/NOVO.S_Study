import java.util.Arrays;

class Solution {
    int[] dc = {10, 20, 30, 40};
    int[] discount;
    int maxJoin = 0;
    int maxMoney = 0;

    public int[] solution(int[][] users, int[] emoticons) {
        discount = new int[emoticons.length];

        //할인 방법의 모든 경우의 수
        dfs(0, users, emoticons);

        return new int[]{maxJoin, maxMoney};
    }

    void dfs(int depth, int[][] users, int[] emoticons) {
        if (depth == emoticons.length) {
            simulate(users, emoticons);
            return;
        }

        for (int i = 0; i < dc.length; i++) {
            discount[depth] = dc[i];
            dfs(depth + 1, users, emoticons);
        }
    }

    void simulate(int[][] users, int[] emoticons) {
        int join = 0;
        int money = 0;

        for (int[] user : users) {
            int needRate = user[0];
            int limit = user[1];
            int sum = 0;

            for (int i = 0; i < emoticons.length; i++) {
                if (discount[i] >= needRate) {
                    sum += emoticons[i] * (100 - discount[i]) / 100;
                }
            }

            if (sum >= limit) {
                join++;
            } else {
                money += sum;
            }
        }

        if (join > maxJoin || (join == maxJoin && money > maxMoney)) {
            maxJoin = join;
            maxMoney = money;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1
        int[][] users1 = {{40, 10000}, {25, 10000}};
        int[] emoticons1 = {7000, 9000};
        System.out.println("Example 1 Result: " + Arrays.toString(sol.solution(users1, emoticons1)));

        // Example 2
        int[][] users2 = {{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200}, {32, 6900}};
        int[] emoticons2 = {1300, 1500, 1600, 4900};
        System.out.println("Example 2 Result: " + Arrays.toString(sol.solution(users2, emoticons2)));
    }
}