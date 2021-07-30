package dataStructure.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ShellSort {
    /*
    希尔排序是希尔(Donald Shell)于1959年提出的一种排序算法。希尔排序也是一种插入排序,
    它是简单插入排序经过改进之后的一个更高效的版本,也称最小增量排序。
     */
    /*
    希尔排序算法思想:
    希尔排序是把记录按下标的一定增量分组,对每组使用直接插入排序算法排序;
    随着增量逐渐减少,每组包含的关键组越来越多,当增量减至1时,整个文件恰好被分为一组,算法便终止。
     */
    public static void main(String[] args) {
        //int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        int[] arr = new int[8];
        for (int i = 0; i < 8; i++) {
            arr[i] = (int) (Math.random() * 800000);
        }
        System.out.println("排序前:");
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strdate1 = simpleDateFormat.format(date1);
        System.out.println(strdate1);
        //shellSort(arr);
        shellSort2(arr);
        System.out.println("排序后:");
        Date date2 = new Date();
        String strdate2 = simpleDateFormat.format(date2);
        System.out.println(strdate2);
        System.out.println(Arrays.toString(arr));
    }

    //对交换式的希尔排序进行优化,移位法
    public static void shellSort2(int[] arr) {
        //增量gap,并逐步的缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从第gap个元素,逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        //移动
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    //当退出while循环,就给temp找到插入的位置
                    arr[j] = temp;
                }
            }
        }
    }

    //使用逐步推到的方式来编写希尔排序
    //希尔排序时,对有序序列在插入时采用交换法,效率并不是很高
    public static void shellSort(int[] arr) {
        int temp = 0;
        int count = 0;
        //根据下面的逐步分析,使用循环处理
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                //遍历各组中所有的元素(共gap组,每组10/gap个元素),步长gap
                for (int j = i - gap; j >= 0; j -= gap) {
                    //如果当前元素大于加上步长后的那个元素,说明需要交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            //System.out.println("希尔排序第" + (++count) + "轮后=" + Arrays.toString(arr));
        }

        /*
        int temp = 0;
        //希尔排序的第一轮排序
        //因为第一轮排序是将10个数据分成5组
        for (int i = 5; i < arr.length; i++) {
            //遍历各组中所有的元素(共5组,每组2个元素),步长5
            for (int j = i - 5; j >= 0; j -= 5) {
                //如果当前元素大于加上步长后的那个元素,说明需要交换
                if (arr[j] > arr[j + 5]) {
                    temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }
        System.out.println("希尔排序1轮后=" + Arrays.toString(arr));

        //希尔排序的第二轮排序
        //因为第二轮排序是将10个数据分成5/2=2组
        for (int i = 2; i < arr.length; i++) {
            //遍历各组中所有的元素(共2组,每组5个元素),步长2
            for (int j = i - 2; j >= 0; j -= 2) {
                //如果当前元素大于加上步长后的那个元素,说明需要交换
                if (arr[j] > arr[j + 2]) {
                    temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
            }
        }
        System.out.println("希尔排序2轮后=" + Arrays.toString(arr));

        //希尔排序的第三轮排序
        //因为第三轮排序是将10个数据分成2/2=1组
        for (int i = 1; i < arr.length; i++) {
            //遍历各组中所有的元素(共1组,每组10个元素),步长1
            for (int j = i - 1; j >= 0; j -= 1) {
                //如果当前元素大于加上步长后的那个元素,说明需要交换
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("希尔排序3轮后=" + Arrays.toString(arr));
        */
    }
}
