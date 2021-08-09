package dataStructure.sort;

import java.util.Arrays;

public class MergeSort {
    /*
    归并排序是利用归并的思想实现的排序方法,该算法采用经典的分治策略
    (分治法将问题分成一些小的问题然后递归求解,而治的阶段则将分的阶段
    得到的各答案"修补"在一起,即分而治之)
     */
    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        //int[] arr2 = {8, 4, 5, 7, 1, 3, 6, 2, 1, 34, 3435};
        int[] temp = new int[arr.length];//归并排序需要一个额外的空间
        mergeSort(arr, 0, arr.length - 1, temp);
        System.out.println("arr=" + Arrays.toString(arr));
    }

    /**
     * 分解+合并的方法
     *
     * @param arr   待排序数组
     * @param left  初始索引
     * @param right 右边索引
     * @param temp  临时数组
     */
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            //向左递归进行分解
            mergeSort(arr, left, mid, temp);
            //向右递归进行分解
            mergeSort(arr, mid + 1, right, temp);
            //合并操作
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * 合并的方法及流程
     *
     * @param arr   待排序数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  中转数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;//初始化i,左边有序序列的初始索引
        int j = mid + 1;//初始化j,右边有序序列的初始索引
        int t = 0;//指向temp数组的当前索引
        //System.out.println("merge implement");
        //先把左右两边(有序)的数据按照罪责填充到temp数组
        //直到左右两边的有序序列有一边处理完毕位置
        while (i <= mid && j <= right) {//两边没有一方执行完毕
            //如果左边的有序序列的当前元素小于或等于右边的有序序列的当前元素
            //即将左边的当前元素拷贝到temp数组中并后移t和i
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t++;
                i++;
            } else {//否则将右边有序序列的当前元素填充到temp数组,并后移t和j
                temp[t] = arr[j];
                t++;
                j++;
            }
        }
        //把剩余数据的一边有序序列的数值全部填充到temp数组中
        while (i <= mid) {//说明左边的有序序列还有剩余元素,就全部填充到temp
            temp[t] = arr[i];
            t++;
            i++;
        }
        while (j <= right) {//说明右边的有序序列还有剩余元素,就全部填充到temp
            temp[t] = arr[j];
            t++;
            j++;
        }
        //将temp数组的元素拷贝到arr数组中
        //注意并不是每次都拷贝所有
        t = 0;
        int tempLeft = left;
        //第一次合并tempLeft=0; right=1; //第二次合并tempLeft=2; right=3;...
        //最后一次合并tempLeft=0; right=7;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }
    }
}
