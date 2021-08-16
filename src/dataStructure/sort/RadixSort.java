package dataStructure.sort;

import java.util.Arrays;

public class RadixSort {
    /*
    基数排序属于"分配式排序",又称桶子法,顾名思义它是通过键值的各个位的值,
    将要排序的元素分配至某些"桶"中,达到排序的作用。
    基数排序法属于稳定性的排序,基数排序法的是效率高的稳定性排序法。
    基数排序是桶排序的扩展。
    基数排序是1887年赫尔曼.何乐礼发明的。它是这样实现的:将整数按位数切割成
    不同的数字,然后按每个位数分别比较。
     */
    /*
    基数排序基本思想:
    将所有待比较数值统一为同样的位数长度,位数较短的数前面补零,然后从最低位开始,
    依次进行一次排序。这样从最低位排序一直到最高位排序完成以后,数列就变成一个有序序列。
     */
    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214};
        radixSort(arr);
    }

    //基数排序算法
    public static void radixSort(int[] arr) {
        //根据下面的推导过程,我们可以得到最终的基数排序
        //1.得到数组中最大的数的位数
        int max = arr[0];//假设第一个数就是最大的数
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //得到最大的数是几位数
        int maxLength = (max + "").length();
        //定义一个二维数组 总共有10个桶,每一个桶就是一个一维数组
        //说明:
        //1.二维数组中包含有10个一维数组
        //2.为了防止在放入数据的时候数据溢出,所以设置每一个一维数组(桶)的大小为arr.length
        //3.很显然,基数排序是一个典型的空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];
        //为了计数每一个桶中实际存放了多少个数据,定义一个一维数组记录各个桶的每次放入的数据个数
        //bucketElementCounts[0] 记录的就是bucket[0]桶中放入的数据个数
        int[] bucketElementCounts = new int[10];
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //(针对每一个元素的对应位进行排序),第一次是个位,第二次是十位,第三次是百位
            for (int j = 0; j < arr.length; j++) {
                //取出每一个数据的对应位的值
                int digitalOfElement = arr[j] / n % 10;
                //放入到对应桶当中
                bucket[digitalOfElement][bucketElementCounts[digitalOfElement]] = arr[j];
                bucketElementCounts[digitalOfElement]++;
            }
            //接下来需要将桶中数据放入原数组
            int index = 0;//数组索引下标
            //遍历每一个桶
            for (int k = 0; k < bucket.length; k++) {
                if (bucketElementCounts[k] != 0) {//说明此时该桶中有数据
                    //循环该桶即第k个桶,将数据放入原数组中
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        arr[index] = bucket[k][l];
                        index++;
                    }
                }
                //每一轮处理完毕后需要将bucketElementCounts[k]=0 !!!
                bucketElementCounts[k] = 0;
            }
            System.out.println("第" + (i + 1) + "轮排序处理" + Arrays.toString(arr));
        }
        /*
        //第一轮
        //定义一个二维数组 总共有10个桶,每一个桶就是一个一维数组
        //说明:
        //1.二维数组中包含有10个一维数组
        //2.为了防止在放入数据的时候数据溢出,所以设置每一个一维数组(桶)的大小为arr.length
        //3.很显然,基数排序是一个典型的空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];
        //为了计数每一个桶中实际存放了多少个数据,定义一个一维数组记录各个桶的每次放入的数据个数
        //bucketElementCounts[0] 记录的就是bucket[0]桶中放入的数据个数
        int[] bucketElementCounts = new int[10];
        //第一轮将每一个数据的个位放入对应桶中
        for (int j = 0; j < arr.length; j++) {
            //取出每一个数据的个位
            int digitalOfElement = arr[j] % 10;
            //放入到对应桶当中
            bucket[digitalOfElement][bucketElementCounts[digitalOfElement]] = arr[j];
            bucketElementCounts[digitalOfElement]++;
        }
        //接下来需要将桶中数据放入原数组
        int index = 0;//数组索引下标
        //遍历每一个桶
        for (int k = 0; k < bucket.length; k++) {
            if (bucketElementCounts[k] != 0) {//说明此时该桶中有数据
                //循环该桶即第k个桶,将数据放入原数组中
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    arr[index] = bucket[k][l];
                    index++;
                }
            }
            //第一轮处理完毕后需要将bucketElementCounts[k]=0 !!!
            bucketElementCounts[k]=0;
        }
        System.out.println("第一轮,对个位的排序处理" + Arrays.toString(arr));

        //======================================================
        //第二轮将每一个数据的十位放入对应桶中
        for (int j = 0; j < arr.length; j++) {
            //取出每一个数据的十位
            int digitalOfElement = arr[j] / 10 % 10;
            //放入到对应桶当中
            bucket[digitalOfElement][bucketElementCounts[digitalOfElement]] = arr[j];
            bucketElementCounts[digitalOfElement]++;
        }
        //接下来需要将桶中数据放入原数组
        index = 0;//数组索引下标
        //遍历每一个桶
        for (int k = 0; k < bucket.length; k++) {
            if (bucketElementCounts[k] != 0) {//说明此时该桶中有数据
                //循环该桶即第k个桶,将数据放入原数组中
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    arr[index] = bucket[k][l];
                    index++;
                }
            }
            //第二轮处理完毕后需要将bucketElementCounts[k]=0 !!!
            bucketElementCounts[k]=0;
        }
        System.out.println("第二轮,对十位的排序处理" + Arrays.toString(arr));

        //======================================================
        //第三轮将每一个数据的百位放入对应桶中
        for (int j = 0; j < arr.length; j++) {
            //取出每一个数据的百位
            int digitalOfElement = arr[j] / 100 % 10;
            //放入到对应桶当中
            bucket[digitalOfElement][bucketElementCounts[digitalOfElement]] = arr[j];
            bucketElementCounts[digitalOfElement]++;
        }
        //接下来需要将桶中数据放入原数组
        index = 0;//数组索引下标
        //遍历每一个桶
        for (int k = 0; k < bucket.length; k++) {
            if (bucketElementCounts[k] != 0) {//说明此时该桶中有数据
                //循环该桶即第k个桶,将数据放入原数组中
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    arr[index] = bucket[k][l];
                    index++;
                }
            }
            bucketElementCounts[k]=0;
        }
        System.out.println("第三轮,对百位的排序处理" + Arrays.toString(arr));
        */
    }
}
