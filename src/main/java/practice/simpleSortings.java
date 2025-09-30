package practice;

import java.util.Arrays;
/*
Program # :  1.4
Techniques : Arrays
Goal : be familiar with Array methods, and sorting algo.
 */
public class simpleSortings {
    public static void main(String[] args) {
        int[] list = new int[]{1,9,5,3,7,11,52,4,5412,138};
        list = sort(list);
        System.out.println(Arrays.toString(list));
    }

    private static int[] sort(int[] list) {
        for (int i = 0; i < list.length - 1; i++) {
            for (int j = i + 1; j < list.length; j++) {
                if (list[i] > list[j]) {
                    int temp = list[i];
                    list[i] = list[j];
                    list[j] = temp;
                }
            }
        }
        return list;
    }
}
