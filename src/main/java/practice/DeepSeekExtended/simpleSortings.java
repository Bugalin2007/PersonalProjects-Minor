package practice.DeepSeekExtended;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class simpleSortings {
    private static final int ARRAY_SIZE = 100000;
    private static final Random random = new Random();
    private static final ExecutorService executor = Executors.newFixedThreadPool(6);

    public static void main(String[] args) throws Exception {
        int[] originalArray = generateRandomArray(ARRAY_SIZE);

        System.out.println("🚀 Starting all sorting algorithms simultaneously...");
        System.out.println("Array size: " + ARRAY_SIZE + "\n");

        long startTime = System.currentTimeMillis();

        // 使用 CompletableFuture 让先完成的先输出
        CompletableFuture<Void> bubbleFuture = CompletableFuture.runAsync(() ->
                runSortingAlgorithm("Bubble Sort", originalArray.clone()), executor);

        CompletableFuture<Void> selectionFuture = CompletableFuture.runAsync(() ->
                runSortingAlgorithm("Selection Sort", originalArray.clone()), executor);

        CompletableFuture<Void> insertionFuture = CompletableFuture.runAsync(() ->
                runSortingAlgorithm("Insertion Sort", originalArray.clone()), executor);

        CompletableFuture<Void> mergeFuture = CompletableFuture.runAsync(() ->
                runSortingAlgorithm("Merge Sort", originalArray.clone()), executor);

        CompletableFuture<Void> quickFuture = CompletableFuture.runAsync(() ->
                runSortingAlgorithm("Quick Sort", originalArray.clone()), executor);

        CompletableFuture<Void> heapFuture = CompletableFuture.runAsync(() ->
                runSortingAlgorithm("Heap Sort", originalArray.clone()), executor);

        // 等待所有任务完成
        CompletableFuture.allOf(bubbleFuture, selectionFuture, insertionFuture,
                mergeFuture, quickFuture, heapFuture).get(60, TimeUnit.SECONDS);

        long endTime = System.currentTimeMillis();

        System.out.println("\n" + "=".repeat(50));
        System.out.printf("🎉 All algorithms completed in %.3f seconds%n",
                (endTime - startTime) / 1000.0);

        executor.shutdown();
    }

    private static void runSortingAlgorithm(String name, int[] array) {
        long startTime = System.nanoTime();

        try {
            switch (name) {
                case "Bubble Sort":
                    bubbleSort(array);
                    break;
                case "Selection Sort":
                    selectionSort(array);
                    break;
                case "Insertion Sort":
                    insertionSort(array);
                    break;
                case "Merge Sort":
                    mergeSort(array);
                    break;
                case "Quick Sort":
                    quickSort(array, 0, array.length - 1);
                    break;
                case "Heap Sort":
                    heapSort(array);
                    break;
            }

            long endTime = System.nanoTime();
            double duration = (endTime - startTime) / 1_000_000.0; // 转换为毫秒

            boolean sorted = isSorted(array);
            String status = sorted ? "✓" : "✗";

            System.out.printf("%s %-15s completed in %8.3f ms%n",
                    status, name, duration);

        } catch (Exception e) {
            System.out.printf("✗ %-15s failed: %s%n", name, e.getMessage());
        }
    }

    // 生成随机数组
    private static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(size * 10);
        }
        return arr;
    }

    // 验证数组是否已排序
    private static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    // ========== 排序算法实现 ==========

    // 冒泡排序
    private static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // 选择排序
    private static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    // 插入排序
    private static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // 归并排序
    private static void mergeSort(int[] arr) {
        if (arr.length <= 1) return;

        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);

        mergeSort(left);
        mergeSort(right);
        merge(arr, left, right);
    }

    private static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }
        while (i < left.length) arr[k++] = left[i++];
        while (j < right.length) arr[k++] = right[j++];
    }

    // 快速排序
    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    // 堆排序
    private static void heapSort(int[] arr) {
        int n = arr.length;

        // 构建最大堆
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // 逐个提取元素
        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest]) largest = left;
        if (right < n && arr[right] > arr[largest]) largest = right;

        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            heapify(arr, n, largest);
        }
    }
}