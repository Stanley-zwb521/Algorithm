package dataStructure.tree;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {4, 6, 8, 5, 9};
        heapSort(arr);
    }

    public static void heapSort(int[] arr) {
        int temp = 0;
        System.out.println("堆排序测试");
        //adjustHeap(arr, 1, arr.length);
        //System.out.println("数组=" + Arrays.toString(arr));//49856
        //adjustHeap(arr, 0, arr.length);
        //System.out.println("数组=" + Arrays.toString(arr));//96854
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        //System.out.println("数组=" + Arrays.toString(arr));
        for (int j = arr.length - 1; j >= 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }
        System.out.println("排序结果为:");
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 将堆调整成大顶堆
     *
     * @param arr    待排序数组
     * @param i      非叶子节点在数组中的索引
     * @param length 表示对多少个元素进行调整,length是在之间的减少
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {//说明此时右子节点大于左子节点
                k++;
            }
            if (arr[i] < arr[k]) {
                arr[i] = arr[k];//将较大的数值赋给父节点
                i = k;//将i指向k继续进行比较
            } else {
                break;
            }
            //for 循环过后已经将i为父节点的树的最大值当到最顶(局部)
            arr[i] = temp;
        }
    }
}
