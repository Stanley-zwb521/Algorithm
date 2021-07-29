package dataStructure.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class InsertionSort {
    /*
    插入排序思想:
    插入排序的基本思想:把n个待排序的元素看成为一个有序表和一个无序表,开始时有序表中只包含一个元素,
    无序表中包含有n-1个元素,排序过程中每次从无序表中取出第一个元素,把它的排序码依次与有序表元素的排序码进行比较,
    将它插入到有序表中的适当位置,使之成为新的有序表。
     */
    public static void main(String[] args) {
        //int[] arr={101,34,119,1,-1,89};
        //测试一下插入排序的时间
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 800000);
        }
        System.out.println("排序前:");
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strdate1 = simpleDateFormat.format(date1);
        System.out.println(strdate1);
        insertSort(arr);
        System.out.println("排序后:");
        Date date2 = new Date();
        String strdate2 = simpleDateFormat.format(date2);
        System.out.println(strdate2);
    }

    public static void insertSort(int[] arr) {
        //使用for循环来将代码简化
        for (int i = 1; i < arr.length; i++) {
            //定义待插入的数
            int insertVal = arr[i];
            int insertIndex = i - 1;//即arr[1]的前面这个数的下标

            //给insertVal找到插入的位置
            //说明:
            //1.insertIndex>=0 保证在给insertVal找插入位置不越界
            //2.arr[insertIndex]>insertVal 待插入的数存在逆序,没有找到正确的插入位置
            //3.需要将insertIndex向后移
            while (insertIndex >= 0 && arr[insertIndex] > insertVal) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //当退出while循环时,说明待插入的正确位置已经找到,为insertIndex+1
            //其次可以在这里进行一个优化,提升算法速度
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }
            //System.out.println("第"+i+"轮插入后");
            //System.out.println(Arrays.toString(arr));
        }

        /*
        //第一轮: {101,34,119,1} => {34,101,119,1}
        //定义待插入的数
        int insertVal=arr[1];
        int insertIndex=1 - 1;//即arr[1]的前面这个数的下标

        //给insertVal找到插入的位置
        //说明:
        //1.insertIndex>=0 保证在给insertVal找插入位置不越界
        //2.arr[insertIndex]>insertVal 待插入的数存在逆序,没有找到正确的插入位置
        //3.需要将insertIndex向后移
        while(insertIndex>=0&&arr[insertIndex]>insertVal){
            arr[insertIndex+1]=arr[insertIndex];
            insertIndex--;
        }
        //当退出while循环时,说明待插入的正确位置已经找到,为insertIndex+1
        arr[insertIndex+1]=insertVal;
        System.out.println("第一轮插入后");
        System.out.println(Arrays.toString(arr));

        //第二轮
        //定义待插入的数
        insertVal=arr[2];
        insertIndex=2 - 1;//即arr[2]的前面这个数的下标

        while(insertIndex>=0&&arr[insertIndex]>insertVal){
            arr[insertIndex+1]=arr[insertIndex];
            insertIndex--;
        }
        //当退出while循环时,说明待插入的正确位置已经找到,为insertIndex+1
        arr[insertIndex+1]=insertVal;
        System.out.println("第二轮插入后");
        System.out.println(Arrays.toString(arr));

        //第三轮
        //定义待插入的数
        insertVal=arr[3];
        insertIndex=3 - 1;//即arr[3]的前面这个数的下标

        while(insertIndex>=0&&arr[insertIndex]>insertVal){
            arr[insertIndex+1]=arr[insertIndex];
            insertIndex--;
        }
        //当退出while循环时,说明待插入的正确位置已经找到,为insertIndex+1
        arr[insertIndex+1]=insertVal;
        System.out.println("第三轮插入后");
        System.out.println(Arrays.toString(arr));
        */
    }
}
