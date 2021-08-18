package dataStructure.search;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {
    //注意:二分查找必须针对的是有序序列,无序序列需要先排成有序序列再进行查找
    /*
    二分查找思路:
    1.首先确定该数组的中间下标
    mid=(left+right)/2
    2.然后让需要查找的数findVal和arr[mid]比较
    2.1 findVal>arr[mid],说明要查找的数在mid的右边,因此需要向右递归查找
    2.2 findVal<arr[mid],说明要查找的数在mid的左边,因此需要向左递归查找
    2.3 findVal==arr[mid],说明找到并返回
    什么时候需要结束递归:
    1.找到就结束递归
    2.递归完整个数组,仍然没有找到findVal,也需要结束递归(当left>right就需要退出)
     */
    public static void main(String[] args) {
        int[] arr = {1, 8, 20, 89, 1000, 1000, 1000, 1234};
        //int resIndex = binarySearch(arr, 0, arr.length - 1, 1000);
        //System.out.println("resIndex=" + resIndex);
        List<Integer> resIndexList = binarySearchPlus(arr, 0, arr.length - 1, 1000);
        System.out.println("resIndexList=" + resIndexList);
    }

    /**
     * 二分查找算法
     *
     * @param arr     数组
     * @param left    左索引
     * @param right   右索引
     * @param findVal 要查找的值
     * @return 如果找到就返回下标, 如果没有找到就返回-1
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        //当left > right时,说明递归整个数组但是没有找到
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal > midVal) {//向右递归
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {//向左递归
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }

    /**
     * 思考:当一个有序序列中有多个相同的数值时,如何将所有的数值都查找到
     * 思路分析:
     * 1.在找到mid索引值时,不要马上返回
     * 2.向mid索引值的左边扫描,将所有等于该数值的下标加入到集合ArrayList中
     * 3.向mid索引值的右边扫描,将所有等于该数值的下标加入到集合ArrayList中
     * 4.最后将ArrayList返回
     */
    public static List<Integer> binarySearchPlus(int[] arr, int left, int right, int findVal) {
        //当left > right时,说明递归整个数组但是没有找到
        if (left > right) {
            return new ArrayList<Integer>();
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal > midVal) {//向右递归
            return binarySearchPlus(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {//向左递归
            return binarySearchPlus(arr, left, mid - 1, findVal);
        } else {
            List<Integer> resIndexList = new ArrayList<Integer>();
            //向mid索引值的左边扫描,将所有等于该数值的下标加入到集合ArrayList中
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal) {
                    break;
                }
                resIndexList.add(temp);
                temp--;
            }
            resIndexList.add(mid);//不要忘记将mid也加入list中
            //向mid索引值的右边扫描,将所有等于该数值的下标加入到集合ArrayList中
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
