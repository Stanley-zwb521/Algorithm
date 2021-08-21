package dataStructure.search;

import java.util.Arrays;

public class FibonacciSearch {
    /*
    基本介绍:
    黄金分割点是指把一条线段分割成两部分,使其中一部分与全长之比等于另一部分与这一部分之比。
    取其前三位数字的近似值是0.618。由于按比例设计的造型十分美丽,因此称为黄金分割也称中外比。
    这是一个神奇的数字,会带来意想不到的效果。
    斐波那契数列{1,1,2,3,5,8,13,21,34,55}发现斐波那契数列的两个相邻数的比例
    无限接近黄金分割值0.618。
     */
    /*
    斐波那契查找原理:
    斐波那契查找原理与前两种查找算法相似,仅仅改变了中间节点(mid)的位置,
    mid不再是中间或插值得到,而是位于黄金分割点附近,即mid=left+F(k-1)-1
    (F代表斐波那契数列),如下图所示。
    {                F[k]-1                  }
    -----------------------[-]----------------
    {left         F(k-1)-1}mid{F(k-2)-1 right}
    对F(k-1)-1的理解:
    1.由斐波那契数列F[k]=F[k-1]+F[k-2]的性质,可以得到(F[k]-1)=(F[k-1]-1)+(F[k-2]-1)+1。
    该式说明只要顺序表的长度为F[k]-1,则可以将该表分成长度为F[k-1]-1和F[k-2]-1的两部分,即如上图所示。
    从而中间位置为mid=left+F[k-1]-1。
    2.类似的,每一个子段也可以用相同的方式分割。
    3.但顺序表的长度n不一定刚好等于F[k]-1,所以需要将原来的顺序表长度n增加至F[k]-1。这里的K值只要
    能使得F[k]-1恰好大于或等于n即可,由以下代码,得到顺序表的长度增加后,新增的位置(从n+1到F[k]-1
    位置),都赋为n位置的值即可。
    while(n>fib(k)-1)
        k++;
     */
    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        System.out.println("index=" + fibonacciSearch(arr, 1));
    }

    //因为mid=left+F[k-1]-1,需要使用到斐波那契数列,因此我们需要先获取到一个斐波那契数列
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    /**
     * 斐波那契查找算法
     * 使用非递归的方式编写斐波那契算法
     *
     * @param arr 数组
     * @param key 需要查找的关键码(值)
     * @return 返回对应下表, 没有返回-1
     */
    public static int fibonacciSearch(int[] arr, int key) {
        int left = 0;
        int right = arr.length - 1;
        int k = 0;//表示斐波那契分割数值的下标
        int mid = 0;//存放mid值
        int f[] = fib();//获取到斐波那契数列
        //获取到斐波那契分割数值的下标
        while (right > f[k] - 1 - 1) {
            k++;
        }
        //因为f[k]数值可能大于数组的长度,因此我们需要使用Arrays类,构造一个新的数组,并指向temp[]
        //不足的部分会用0填充
        int[] temp = Arrays.copyOf(arr, f[k] - 1);
        //实际上需要使用arr数组最后一位的数值填充temp
        for (int i = right + 1; i < temp.length; i++) {
            temp[i] = arr[right];
        }
        //使用while循环循环处理,找到我们的数key
        while (left <= right) {//只要这个条件满足，就可以找
            mid = left + f[k - 1] - 1;
            if (key < temp[mid]) {//应该向数组的左边查找
                right = mid - 1;
                //为什么是k--呢???
                //说明:
                //1.全部元素=前面的元素+后边的元素
                //2.f[k] = f[k-1] + f[k-2]
                //因为前面有f[k-1]个元素,所以可以继续拆分f[k-1]=f[k-2]+f[k-3]
                //即在f[k-1]前面继续查找 k--
                k--;
            } else if (key > temp[mid]) {//向数组的右边查找
                left = mid + 1;
                //为什么是k-=2呢???
                //说明:
                //1.全部元素=前面的元素+后边的元素
                //2.f[k] = f[k-1] + f[k-2]
                //因为后面右f[k-2]个元素,所以可以继续拆分f[k-2]=f[k-3]+f[k-4]
                //即在f[k-2]里面继续查找 k-=2
                k -= 2;
            } else {//找到
                //需要确定,返回的是哪个下标
                if (mid <= right) {
                    return mid;
                } else {
                    return right;
                }
            }
        }
        return -1;
    }
}
