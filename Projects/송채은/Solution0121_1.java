class Solution0121_1 {
    public long solution(int[] sequence) {
        long answer = 0;
        int x = 1;

        int[] a1 = new int[sequence.length]; //1로 시작하는 펄스 수열
        int[] b1 = new int[sequence.length]; //-1로 시작하는 펄스 수열

        int[] a2 = new int[sequence.length]; //sequence * a1
        int[] b2 = new int[sequence.length]; //sequence * b1

        //펄스 수열 만들기
        for(int i = 0; i < sequence.length; i++){
            a1[i] = x;
            b1[i] = x * -1;
            x *= -1;
        }

        //sequence * 펄스 수열
        for(int i = 0; i < sequence.length; i++){
            a2[i] = sequence[i] * a1[i];
            b2[i] = sequence[i] * b1[i];
        }

        //a2
        long cur1 = 0;
        long maxA = 0;
        for (int i = 0; i < a2.length; i++) {
            cur1 = Math.max(a2[i], cur1 + a2[i]);
            maxA = Math.max(maxA, cur1);
        }

        //b2
        long cur2 = 0;
        long maxB = 0;
        for (int i = 0; i < b2.length; i++) {
            cur2 = Math.max(b2[i], cur2 + b2[i]);
            maxB = Math.max(maxB, cur2);
        }

        answer = Math.max(maxA, maxB);
        return answer;
    }

    public static void main(String[] args) {
        Solution0121_1 sol = new Solution0121_1();

        // Example
        int[] sequence = {2, 3, -6, 1, 3, -1, 2, 4};
        System.out.println("Example Result: " + sol.solution(sequence));
    }
}