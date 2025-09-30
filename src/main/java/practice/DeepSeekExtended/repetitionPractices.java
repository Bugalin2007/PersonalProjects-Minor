package practice.DeepSeekExtended;

import java.util.HashMap;
import java.util.Map;

public class repetitionPractices {

    // 记忆化缓存 - 使用HashMap存储已计算的结果
    private static final Map<Integer, Integer> fibonacciCache = new HashMap<>();
    private static final Map<Integer, Integer> factorialCache = new HashMap<>();

    static {
        // 初始化基础情况的缓存值
        fibonacciCache.put(0, 0);
        fibonacciCache.put(1, 1);
        factorialCache.put(0, 1);
        factorialCache.put(1, 1);
    }

    // ========== 原有的迭代和递归版本 ==========

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

    // ========== 记忆化版本 ==========

    /**
     * 记忆化版本的斐波那契数列
     * 时间复杂度: O(n) - 每个值只计算一次
     * 空间复杂度: O(n) - 用于存储缓存
     */
    public static int FibonacciMemoization(int n) {
        // 参数检查
        if (n < 0) {
            throw new IllegalArgumentException("n must be non-negative");
        }

        // 如果结果已经在缓存中，直接返回
        if (fibonacciCache.containsKey(n)) {
            return fibonacciCache.get(n);
        }

        // 否则递归计算并缓存结果
        int result = FibonacciMemoization(n - 1) + FibonacciMemoization(n - 2);
        fibonacciCache.put(n, result);

        return result;
    }

    /**
     * 记忆化版本的阶乘
     * 时间复杂度: O(n) - 每个值只计算一次
     * 空间复杂度: O(n) - 用于存储缓存
     */
    public static int FactorialMemoization(int n) {
        // 参数检查
        if (n < 0) {
            throw new IllegalArgumentException("n must be non-negative");
        }

        // 如果结果已经在缓存中，直接返回
        if (factorialCache.containsKey(n)) {
            return factorialCache.get(n);
        }

        // 否则递归计算并缓存结果
        int result = n * FactorialMemoization(n - 1);
        factorialCache.put(n, result);

        return result;
    }

    /**
     * 使用computeIfAbsent的更简洁的记忆化版本
     */
    public static int FibonacciMemoizationClean(int n) {
        if (n < 0) throw new IllegalArgumentException("n must be non-negative");

        return fibonacciCache.computeIfAbsent(n, k ->
                FibonacciMemoizationClean(k - 1) + FibonacciMemoizationClean(k - 2)
        );
    }

    public static int FactorialMemoizationClean(int n) {
        if (n < 0) throw new IllegalArgumentException("n must be non-negative");

        return factorialCache.computeIfAbsent(n, k ->
                k * FactorialMemoizationClean(k - 1)
        );
    }

    // ========== 工具方法 ==========

    /**
     * 清空缓存，用于重置状态
     */
    public static void clearCache() {
        fibonacciCache.clear();
        factorialCache.clear();

        // 重新初始化基础值
        fibonacciCache.put(0, 0);
        fibonacciCache.put(1, 1);
        factorialCache.put(0, 1);
        factorialCache.put(1, 1);
    }

    /**
     * 获取缓存统计信息
     */
    public static void printCacheStats() {
        System.out.println("Fibonacci Cache size: " + fibonacciCache.size());
        System.out.println("Factorial Cache size: " + factorialCache.size());
    }
}