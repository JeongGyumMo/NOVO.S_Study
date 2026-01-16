class Solution3 {
    public int solution(int[] players, int m, int k) {
        int answer = 0; //증설 횟수
        int server = 0; //살아있는 서버 수
        int [] alive = new int[players.length + k]; //만료 기록

        for(int i = 0; i < players.length; i++){
            //만료된 서버 제거
            server -= alive[i];

            //필요한 서버 수
            int need = players[i] / m;

            //부족하면 서버 추가
            if(server < need){
                int add = need - server;
                answer += add;
                server += add;

                //k시간 뒤 만료
                alive[i + k] += add;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution3 sol = new Solution3();

        //Ex1
        int[] players1 = {0, 2, 3, 3, 1, 2, 0, 0, 0, 0, 4, 2, 0, 6, 0, 4, 2, 13, 3, 5, 10, 0, 1, 5};
        int m1 = 3;
        int k1 = 5;
        int result1 = sol.solution(players1, m1, k1);
        System.out.println("Result: " + result1);

        //Ex2
        int[] players2 = {0, 0, 0, 10, 0, 12, 0, 15, 0, 1, 0, 1, 0, 0, 0, 5, 0, 0, 11, 0, 8, 0, 0, 0};
        int m2 = 5;
        int k2 = 1;
        int result2 = sol.solution(players2, m2, k2);
        System.out.println("Result: " + result2);

        //Ex3
        int[] players3 = {0, 0, 0, 0, 0, 2, 0, 0, 0, 1, 0, 5, 0, 2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1};
        int m3 = 1;
        int k3 = 1;
        int result3 = sol.solution(players3, m3, k3);
        System.out.println("Result: " + result3);
    }
}