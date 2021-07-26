package dataStructure.recursion;

/*
八皇后问题是一个古老而著名的问题,是回溯算法的典型案例,
该问题是国际西洋棋棋手马克斯.贝瑟尔于1848年提出;
在8x8格的国际象棋上摆放八个皇后,使其不能相互攻击,
即:任意两个皇后都不能处于同一行,同一列或同一斜线上,
问有多少种摆法?
 */
//八皇后问题思路:
//1.第一个皇后先芳第一行第一列
//2.第二个皇后放在第二行第一列然后判断是否ok(即判断是否冲突),如果不ok,继续放在第二列,第三列,依次把所有列都放完并找到合适的列
//3.继续第三个皇后,还是第一列,第二列...直到第八个皇后也能放在一个不冲突的位置,就算找到一个正确的解
//4.当得到一个正确的解时,回退到上一个栈时,就会开始回溯,即将第一个皇后放到第一列的所有解全部得到
//5.然后回头继续第一个皇后放第二列,后面继续循环执行1,2,3,4的步骤
public class EightQueensProblem {
    int max = 8;//定义一共有多少个皇后
    /*定义array,保存皇后放置的位置(理论上来讲需要采用二维数组来表示一个棋盘,但是在算法中直接采用一维数组即可解决),
    arr[row]={0,4,7,5,2,6,1,3},表示在第row+1行val+1列的位置上放置棋子
     */
    int[] array = new int[max];
    static int count = 0;

    public static void main(String[] args) {
        //测试一下八皇后
        EightQueensProblem queen = new EightQueensProblem();
        queen.chess(0);
        System.out.printf("一共有%d种解法", count);
    }

    //编写一个方法,放置第n个皇后
    //特别注意:chess是每一次递归的时候都会产生一个for(int i=0; i<max; i++),只要没有找到正确的解都会产生回溯
    private void chess(int n) {
        if (n == max) {//当n==max时说明所有的8个皇后都已经放置完毕
            print();
            return;
        }
        //将皇后放入棋盘中
        for (int i = 0; i < max; i++) {
            //尝试将皇后依次从改行的第一列放入
            array[n] = i;
            if (judgeConflict(n)) {//判断当前皇后放入后是否有冲突
                chess(n + 1);//没有冲突时放入第n+1个皇后
            }
            //如果冲突就执行array[n]=i,即将第n个皇后放置在本行后移的一个位置
        }
    }

    //写一个方法判断当前放置的第n个皇后和前面放置的n-1个皇后是否有冲突

    /**
     * @param n 表示第n个皇后
     * @return
     */
    private boolean judgeConflict(int n) {
        for (int i = 0; i < n; i++) {
            //说明:
            //1.array[i]==array[n] 表示判断第n个棋子是否和前n-1个棋子在同一列
            //2.Math.abs(n-i)==Math.abs(array[n]=array[i] 表示表示判断第n个棋子是否和前n-1个棋子在同一斜线
            //3.不需要判断棋子是否在同一行,因为n依次递增,不可能出现在同一行的情况
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    //写一个方法,可以将皇后摆放的位置打印出来
    private void print() {
        count++;
        for (int row = 0; row < array.length; row++) {
            System.out.printf(array[row] + " ");
        }
        System.out.println();
    }
}
