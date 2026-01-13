
import java.util.HashMap;
import java.util.Map;

public class Solution1 {
    public int solution(String[] friends, String[] gifts) {
        int maxGifts = 0;

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