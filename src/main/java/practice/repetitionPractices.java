package practice;
/*
Program # :  1.3
Techniques : Recursion and Iteration
Goal : with both Recursion and Iteration, implement 2 classical loop problem: Fibonacci and Factorial.
 */
public class repetitionPractices {

    private static int FibonacciIteration(int n){
        int[] fibonacci = new int[]{0,1};
        int temp;
        for (int i = 2; i <= n; i++) {
            temp = fibonacci[0] + fibonacci[1];
            fibonacci[0] = fibonacci[1];
            fibonacci[1] = temp;
        }
        return fibonacci[1];
    }

    private static int FibonacciRecursion(int n) {
        if (n <= 1) return n;
        return FibonacciRecursion(n-1) + FibonacciRecursion(n-2);
    }

    private static int FactorialIteration(int n){
        int result = 1;
        for(int i=2;i<=n;i++){
            result *= i;
        }
        return result;
    }

    public static int FactorialRecursion(int n) {
        if (n <= 1) return 1;
        return n * FactorialRecursion(n - 1);
    }
}
