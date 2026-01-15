public class Solution2 {

    public int[] solution(int[] sequence, int k) {

        int left = 0;
        int sum = 0;
        int minLen = Integer.MAX_VALUE;

        int start = 0;
        int end = 0;

        for (int right = 0; right < sequence.length; right++) {
            sum += sequence[right];

            while (sum > k) {
                sum -= sequence[left];
                left++;
            }

            if (sum == k) {
                int len = right - left;
                if (len < minLen) {
                    minLen = len;
                    start = left;
                    end = right;
                }
            }
        }

        return new int[]{start, end};
    }

    public static void main(String[] args) {
        Solution2 sol = new Solution2();

        //Ex1
        int[] sequence1 = {1, 2, 3, 4, 5};
        int k1 = 7;
        int[] result1 = sol.solution(sequence1, k1);
        System.out.println("Result: [" + result1[0] + ", " + result1[1] + "]");

        //Ex2
        int[] sequence2 = {1, 1, 1, 2, 3, 4, 5};
        int k2 = 5;
        int[] result2 = sol.solution(sequence2, k2);
        System.out.println("Result: [" + result2[0] + ", " + result2[1] + "]");

        //Ex3
        int[] sequence3 = {2, 2, 2, 2, 2};
        int k3 = 6;
        int[] result3 = sol.solution(sequence3, k3);
        System.out.println("Result: [" + result3[0] + ", " + result3[1] + "]");
    }
}
