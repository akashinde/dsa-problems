/* 
 * 70. Climbing Stairs
 * You are climbing a staircase. It takes n steps to reach the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * 
 * Constraints: 1 <= n <= 45
*/

package leetcode.easy;
import java.math.BigInteger;

class MySolution {
    private BigInteger calculateFactorial(BigInteger num) {
        BigInteger n1 = new BigInteger("1");
        if (num.compareTo(n1) < 0) {
            return n1;
        }
        return num.multiply(calculateFactorial(num.subtract(n1)));
    }

    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }

        int distinct = 1; // for all n, all 1s will be distinct (edge condition)
        int min2 = 1;
        int max2;
        if (n%2 != 0) {
            // its not even
            max2 = (n-1)/2; // max number of 2 we can have for odd num
        } else {
            max2 = (n/2) - 1; // max range for 2s excluding last num
            distinct += 1; // we know for all even n, 2s distinct will be 1 (edge condition)
        }

        int numOf1s;
        int numOf2s;
        int range;
        long variations;
        for (int i=min2; i<=max2; i++) {
            numOf2s = i;
            numOf1s = n - (numOf2s*2);
            range = numOf1s + numOf2s;
            BigInteger denom = new BigInteger("1");
            for (int j=range; j>numOf1s; j--) {
                denom = denom.multiply(BigInteger.valueOf(j));
            }
            variations = denom.divide(calculateFactorial(BigInteger.valueOf(numOf2s))).longValue();
            distinct += variations;
        }
        return distinct;
    }
}

class BestSolution {
    public int climbStairs(int n) {
        if (n==0 || n==1) {
            return 1;
        }
        int one = 1; 
        int two = 1;
        for (int i=2; i<=n; i++) {
            int temp = one;
            one = one+two;
            two = temp;
        }
        return one;
    }
}

class Main {
    public static void main(String[] args) {
        MySolution mySol = new MySolution();
        BestSolution bestSol = new BestSolution();

        int ans1 = mySol.climbStairs(42);
        int ans2 = bestSol.climbStairs(42);
        System.out.println();
        System.out.println("Answer matched: "+ (ans1 == ans2));
        System.out.println(ans2);
    }
}

/*
 * Learning Outcome:
 * In my approach, I have tried to use a permutation mathematical way to solve the problem.
 * I am first getting the edge conditions, like for we can climb with all 1s and if n=even 
 * then n/2 steps or n=odd then (n-1)/2 + 1s step.
 * And then between I am getting subsets for which we will have 2s incrementing (so that 
 * I can cover all the 2s possible)
 * Finally, I am performing the permutation on those subsets, so that we get possible 
 * variations for each subset and finally add them.
 * 
 * In best solution approach, there is a pattern which was discovered by using decision 
 * tree method, where seen that we get redundant sub-trees, hence the approach uses
 * bottom-up way that is we start from n and traverse reversely.
 * While doing this a fibonacci pattern can be seen.
 */