package dataStructure.tree;

import java.util.Arrays;

public class HeapSort {
    /*
    堆排序基本介绍:
    1.堆排序是利用堆这种数据结构而设计的一种排序算法,堆排序是一种选择排序,
    它的最坏,最好,平均时间复杂度均为O(nlogn),它是不稳定排序
    2.堆是具有以下性质的完全二叉树:每个节点的值都大于或等于其左右孩子节点的值,
    称为大顶堆,注意:没有要求节点的左孩子的值和右孩子的值的大小关系
    3.每个节点的值都小于或等于其左右孩子节点的值称为小顶堆
     */
    /*
    堆排序基本思路:
    1.将无序序列构成一个堆,根据升序降序需求选择大顶或小顶堆
    2.将堆顶元素与末尾元素交换,将最大元素"沉"到数组末端
    3.重新调成结构,使其满足堆定义,然后继续交换堆顶元素与当前末尾元素,
    反复执行调整+交换步骤直到整个序列有序
     */
    public static void main(String[] args) {
        //要求将数组按升序排列
        int[] arr = {4, 6, 8, 5, 9};
        heapSort(arr);
    }

    public static void heapSort(int[] arr) {
        int temp = 0;
        System.out.println("堆排序测试");
//        adjustHeap(arr, 1, arr.length);
//        System.out.println("第一轮=" + Arrays.toString(arr));//49856
//        adjustHeap(arr, 0, arr.length);
//        System.out.println("第二轮=" + Arrays.toString(arr));//96854 真正的大顶堆
        //1.将无序序列构成一个堆,根据升序降序需求选择大顶或小顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
//        System.out.println("数组=" + Arrays.toString(arr));
        /*
         * 2.将堆顶元素与末尾元素交换,将最大元素"沉"到数组末端
         * 3.重新调成结构,使其满足堆定义,然后继续交换堆顶元素与当前末尾元素,
         * 反复执行调整+交换步骤直到整个序列有序
         */
        for (int j = arr.length - 1; j > 0; j--) {
            //交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }
        System.out.println("排序结果为:");
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 将一个数组(二叉树)调整成一个大顶堆
     * 功能:完成以i为指向的非叶子节点的树调整成大顶堆
     * 举例:int[] arr = {4, 6, 8, 5, 9}; => i = 1 => adjustHeap =>得到{4, 9, 8, 5, 6}
     * 如果再次调用adjustHeap传入的i = 0 =>得到{9, 6, 8, 5, 4}
     *
     * @param arr    待调整的数组
     * @param i      非叶子节点在数组中的索引
     * @param length 表示对多少个元素进行调整,length是在逐渐的减少
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];//先取出当前元素的值,保存在临时变量
        //开始调整
        //说明:
        //k = i * 2 + 1; k是i节点的左子节点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {//说明左子节点的值小于右子节点的值
                k++;//k指向右子节点
            }
            if (arr[k] > temp) {//如果子节点大于父节点
                arr[i] = arr[k];//将较大的数值赋给父节点
                i = k;//i指向k,继续循环比较
            } else {
                break;
            }
        }
        //for循环过后已经将i为父节点的树的最大值放到最顶(局部)
        arr[i] = temp;//将temp值放到调整后的位置
    }
}
