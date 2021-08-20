package dataStructure.search;

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
    public static void main(String[] args){

    }
}
