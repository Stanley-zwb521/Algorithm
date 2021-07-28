package dataStructure.sort;

import java.util.Arrays;

public class SelectionSort {
    /*
    选择排序思想:
    选择排序也是一种简单的排序方法,它的思想是:第一次从arr[0]-arr[n-1]中选取最小值,与arr[0]交换,
    第二次从arr[1]-arr[n-1]中选取最小值,与arr[1]交换,第三次从arr[2]-arr[n-1]中选取最小值,
    与arr[2]交换,...,第i次从arr[i-1]-arr[n-1]中选取最小值,与arr[i-1]交换,...,第n-1次从
    arr[n-2]-arr[n-1]中选取最小值,与arr[n-2]交换,总共通过n-1次,得到一个按从小到大排序的有序序列。
     */
    /*
    说明:
    1.选择排序一共有数组大小-1轮排序
    2.每一轮排序又是一个循环,循环的规则(代码)
    2.1先假定当前这个数是最小数
    2.2然后和后面的每个数进行比较,如果发现有比当前数更小的数,
    就重新确定最小数并记录下标
    2.3当遍历到数组的最后时,就得到本轮最小数和下标
    2.4交换
     */
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1, -1, 90, 123};
        System.out.println("排序前:");
        System.out.println(Arrays.toString(arr));
        selectSort(arr);
        System.out.println("排序后:");
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 选择排序算法
     *
     * @param arr 待排数组
     */
    public static void selectSort(int[] arr) {
        //算法 先简单->再复杂,将一个复杂的算法,拆成简单的问题逐步解决,先做第一轮再做第二轮...找到规律,进行结合
        //选择排序时间复杂度 O(n^2)
        for (int i = 0; i < arr.length - 1; i++) {
            int min = arr[i];//暂时默认数组下标为i的元素最小
            int minIndex = i;//暂时记录最小元素下标为i
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {//说明假定的最小值并不是最小的
                    min = arr[j];//重置min
                    minIndex = j;//重置minIndex
                }
            }
            //将最小值放在arr[i],即交换
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
            //System.out.println("第"+(i+1)+"轮后:");
            //System.out.println(Arrays.toString(arr));
        }
        /*
        int min = arr[0];//暂时默认数组下标为0的元素最小
        int minIndex = 0;//暂时记录最小元素下标为0
        for (int j = 0 + 1; j < arr.length; j++) {
            if (min > arr[j]) {//说明假定的最小值并不是最小的
                min = arr[j];//重置min
                minIndex = j;//重置minIndex
            }
        }
        //将最小值放在arr[0],即交换
        if (minIndex != 0) {
            arr[minIndex] = arr[0];
            arr[0] = min;
        }
        System.out.println("第1轮后:");
        System.out.println(Arrays.toString(arr));
        */
    }
}
