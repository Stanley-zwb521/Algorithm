package dataStructure.recursion;

public class RecursionTest {
    public static void main(String[] args) {
        //递归调用机制
        //递归调用规则:
        //1.当程序执行到一个方法时,就会开辟一个独立的空间(栈)
        //2.每一个空间的数据(局部变量)是独立的(除非传入的参数是引用类型变量例如:数组,就会共享该引用类型的数据),不会互相影响
        //3.递归必须向退出递归的条件逼近,否则就是无限递归(死递归),出现StackOverFlowError
        //4.当一个方法执行完毕,或者遇到return语句就会返回,遵守谁调用,就将结果返回给谁,同时当方法执行完毕或者返回时,该方法也就执行完毕
        print(4);
        int res = factorial(3);
        System.out.println("factorial=" + res);
    }

    /**
     * 打印问题
     * print(4); -> 会先在java virtual machine中的栈区生成main栈
     * main栈中包含print(4);
     * 由于print(4)方法的存在会自动在JVM中栈区内再生成一个n=4的栈
     * n=4的栈中包含if(n>2)print(3); Sys...
     * 由于print(3)方法的存在会自动在JVM中栈区内再生成一个n=3的栈
     * n=3的栈中包含if(n>2)print(2); Sys...
     * 由于print(2)方法的存在会自动在JVM中栈区内再生成一个n=2的栈
     * n=2的栈中包含System.out.print代码块
     * 依次从JVM中的栈区栈顶的n=2栈区释放,直到执行到main栈退出程序
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
