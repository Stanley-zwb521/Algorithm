package dataStructure.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertValueSearch {
    /*
    插值查找算法原理:
    1.插值查找算法类似于二分查找,不同的是插值查找每次从自适应mid处开始查找
    2.将二分查找中的求mid索引的公式进行改进:
    int mid =（left+right)/2 = left+(right-left)/2 改成
    int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
     */
    /*
    插值查找注意事项:
    1.对于数据量较大,关键字(数值)分布比较均匀的查找表来说,采用插值查找速度较快
    2.关键字分布不均匀的情况下,插值查找不一定比二分查找要好
     */
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
        //int[] arr = {1, 8, 20, 89, 1000, 1000, 1000, 1234};
        //System.out.println(Arrays.toString(arr));
        int index = insertValueSearch(arr, 0, arr.length - 1, 100);
        System.out.println("index=" + index);
        //List<Integer> resIndexList = insertValueSearchPlus(arr, 0, arr.length - 1, 1000);
        //System.out.println("resIndexList=" + resIndexList);
    }

    /**
     * 插值查找算法也需要针对的是有序的序列
     *
     * @param arr     数组
     * @param left    左索引
     * @param right   右索引
     * @param findVal 查找值
     * @return 如果查找到数值返回下标, 如果没有查找到就返回-1
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {
        System.out.println("查找次数~~");
        //注意:findVal<arr[0] || findVal>arr[arr.length-1]非常重要
        //如果没有这两个条件限制将可能导致mid溢出的风险
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }
        //求mid中值,采用自适应的方法
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal) {//要查找的数值大于midVal,向右递归
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {//要查找的数值小于midVal,向左递归
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else {//找到查找的数值
            return mid;
        }
    }

    //可处理数列中存在相同数值的情况
    public static List<Integer> insertValueSearchPlus(int[] arr, int left, int right, int findVal) {
        System.out.println("查找次数~~");
        //注意:findVal<arr[0] || findVal>arr[arr.length-1]非常重要
        //如果没有这两个条件限制将可能导致mid溢出的风险
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return new ArrayList<Integer>();
        }
        //求mid中值,采用自适应的方法
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal) {//要查找的数值大于midVal,向右递归
            return insertValueSearchPlus(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {//要查找的数值小于midVal,向左递归
            return insertValueSearchPlus(arr, left, mid - 1, findVal);
        } else {//找到查找的数值
            List<Integer> resIndexList = new ArrayList<Integer>();
            //先向左扫描看看是否有相同的数值
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal) {
                    break;
                }
                resIndexList.add(temp);
                temp--;
            }
            resIndexList.add(mid);//不要忘记将mid也加入list中
            //向右扫描看看是否有相同的数值
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != findVal) {
                    break;
                }
                resIndexList.add(temp);
                temp++;
            }
            return resIndexList;
        }
    }
}
