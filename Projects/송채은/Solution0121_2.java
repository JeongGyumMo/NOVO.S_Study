class Solution0121_2 {
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";

        char[] move = {'d','l','r','u'};
        int[] movex = {1, 0, 0, -1};
        int[] movey = {0, -1, 1, 0};

        // 최단 거리
        int shortest = Math.abs(r - x) + Math.abs(c - y);

        // 도착 가능한지 체크
        // 최단 거리가 k보다 작고, k에서 최단 거리를 뺀 값(남는 걸음)이 짝수여야 한다.
        if (shortest > k || (k - shortest) % 2 == 1) {
            return "impossible";
        }

        for (int step = 0; step < k; step++) {

            // 사전순으로 모든 방향 시도(i=0(d), i=1(l), i=2(r), i=3(u))
            for (int i = 0; i < 4; i++) {
                int nx = x + movex[i];
                int ny = y + movey[i];

                // 격자 밖이면 패스
                if (nx < 1 || nx > n || ny < 1 || ny > m){
                    continue;
                }

                int remain = k - step - 1; // 남은 이동 횟수

                // (nx, ny)에서 (r, c)까지 가기 위해 반드시 필요한 최소 이동 횟수
                int need = Math.abs(r - nx) + Math.abs(c - ny);

                // 해당 방향으로 가도 도착이 가능하다면 경로에 저장
                if (need <= remain && (remain - need) % 2 == 0) {
                    answer += move[i];
                    x = nx;
                    y = ny;
                    break;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution0121_2 sol = new Solution0121_2();

        // Example 1
        int n1 = 3;
        int m1 = 4;
        int x1 = 2;
        int y1 = 3;
        int r1 = 3;
        int c1 = 1;
        int k1 = 5;
        String result1 = sol.solution(n1, m1, x1, y1, r1, c1, k1);
        System.out.println("Example 1 Result: " + result1); //dllrl

        // Example 2
        int n2 = 2;
        int m2 = 2;
        int x2 = 1;
        int y2 = 1;
        int r2 = 2;
        int c2 = 2;
        int k2 = 2;
        String result2 = sol.solution(n2, m2, x2, y2, r2, c2, k2);
        System.out.println("Example 2 Result: " + result2); //dr

        // Example 3
        int n3 = 3;
        int m3 = 3;
        int x3 = 1;
        int y3 = 2;
        int r3 = 3;
        int c3 = 3;
        int k3 = 4;
        String result3 = sol.solution(n3, m3, x3, y3, r3, c3, k3);
        System.out.println("Example 3 Result: " + result3); //impossible
    }
}