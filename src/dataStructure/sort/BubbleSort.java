package dataStructure.sort;

import java.util.Arrays;

public class BubbleSort {
    /*
    冒泡排序的基本思想是:通过对待排序序列从前向后(下标较小的元素开始),依次比较相邻元素的值,若发现逆序则交换,
    使值较大的元素逐渐从前移向后部,就像水底下的气泡一样逐渐上浮。
     */
    //冒泡排序规则:
    //1.一共进行数组的大小-1次大的循环
    //2.每一趟排序的次数在逐渐的减少
    //3.如果我们发现在某趟排序中没有发生一次交换,可以提前结束冒泡排序
    public static void main(String[] args) {
        int array[] = {3, 9, -1, 10, -2};
        System.out.println("排序前:");
        System.out.println(Arrays.toString(array));
        //int array[]={1,2,3,4,5,6};
        //冒泡排序的时间复杂度O(n^2)
        bubbleSort(array);
        System.out.println("排序后:");
        System.out.println(Arrays.toString(array));
//        int temp = 0;//临时变量
//        boolean flag=false;//设置一个标识变量,用于冒泡排序优化,表示是否进行过交换
//        for(int i=0;i<array.length-1;i++) {
//            for (int j = 0; j < array.length -i - 1; j++) {
//                //如果前面的数比后面的数大,则交换
//                if (array[j] > array[j + 1]) {
//                    flag=true;//说明进行过交换
//                    temp = array[j];
//                    array[j] = array[j + 1];
//                    array[j + 1] = temp;
//                }
//            }
//            System.out.println("第"+(i+1)+"趟排序后的数组:");
//            System.out.println(Arrays.toString(array));
//            if(!flag){//在一趟排序种,一次交换也没产生
//                break;
//            }else{
//                flag=false;//重置flag!!!,用于下一次判断
//            }
//        }
    }

    //将前面的冒泡排序算法封装成一个方法
    public static void bubbleSort(int[] array) {
        //冒泡排序的时间复杂度O(n^2)
        int temp = 0;//临时变量
        boolean flag = false;//设置一个标识变量,用于冒泡排序优化,表示是否进行过交换
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                //如果前面的数比后面的数大,则交换
                if (array[j] > array[j + 1]) {
                    flag = true;//说明进行过交换
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
            //System.out.println("第"+(i+1)+"趟排序后的数组:");
            //System.out.println(Arrays.toString(array));
            if (!flag) {//在一趟排序种,一次交换也没产生
                break;
            } else {
                flag = false;//重置flag!!!,用于下一次判断
            }
        }
    }
}
