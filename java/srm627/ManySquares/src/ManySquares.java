import java.util.*;

public class ManySquares {
    public int howManySquares(int[] sticks) {
        Arrays.sort(sticks);
        int current = 1;
        int ans = 0;


       for (int i = 1; i < sticks.length; i++ ){
            if (sticks[i] == sticks[i - 1]) {
                current++;
            } else {
                ans += current / 4;
                current = 1;
            }
        }

        ans += current/4;
        return ans;
    }
}
