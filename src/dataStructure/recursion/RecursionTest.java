package dataStructure.recursion;

public class RecursionTest {
    public static void main(String[] args) {
        //递归调用机制
        print(4);
        int res = factorial(3);
        System.out.println("factorial=" + res);
    }

    /**
     * 打印问题
     * print(4); -> 会先在java virtual machine中的栈区生成main栈,
     * main栈中包含print(4);
     * 由于print(4)方法的存在会自动在JVM中栈区内再生成一个n==4的栈
     * n==4的栈中包含print(3);
     * 由于print(3)方法的存在会自动在JVM中栈区内再生成一个n==3的栈
     * n==3的栈中包含print(2);
     * 由于print(2)方法的存在会自动在JVM中栈区内再生成一个n==2的栈
     * n==2的栈中包含System.out.print代码块
     * 依次从JVM中的栈区栈顶的n==2栈区释放
     * 因此输出n=2 n=3 n=4
     *
     * @param n
     */
    public static void print(int n) {
        if (n > 2) {
            print(n - 1);
        }//else{ //如果存在else则输出结果只存在n=2
        System.out.println("n=" + n);
        //}
    }

    /**
     * 阶乘问题
     *
     * @param n
     * @return
     */
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return factorial(n - 1) * n;
        }
    }
}
