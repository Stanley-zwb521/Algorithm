package dataStructure.stack;

public class CaculatorExpression {
    public static void main(String[] args) {
        //根据思路完成表达式的运算
        String expression = "7+2*6-4";
        //创建两个栈，数栈和符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义相关变量
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';
        while (true) {
            //依次得到expression中的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断ch是什么,做出相应处理
            if (operStack.isOper(ch)) {
                //判断当前符号栈是否为空
                if (!operStack.isEmpty()) {
                    //如果当前的操作符优先级小于或者等于符号栈中操作符,就需要从数栈中pop两个数出来,
                    //再从符号栈中pop出一个符号,进行运算,算出的结果入数栈,然后将当前操作符入符号栈;
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        //算出的结果入数栈
                        numStack.push(res);
                        //将当前操作符入符号栈
                        operStack.push(ch);
                    } else {
                        operStack.push(ch);
                    }
                } else {
                    //如果发现当前符号栈为空,就直接入符号栈
                    operStack.push(ch);
                }
            } else {
                //如果我们发现是一个数字,就直接入数栈
                numStack.push(ch - 48);//涉及到ASCII,'1'=>1,相差48
            }
            //让index+1,并判断是否扫描到expression尾部
            index++;
            if (index >= expression.length()) {
                break;
            }
        }
        //当表达式扫描完毕后,就顺序的从数栈和符号栈中pop出相应的数和符号,并进行运算
        while (true) {
            //如果符号栈为空,则计算到最后结果,数栈只有一个数字【结果】
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        //将数栈的最后一个数pop出来
        System.out.printf("表达式 %s = %d\n", expression, numStack.pop());
    }
}

/**
 * 使用栈完成表达式计算的思路(7*2*2+1-5+3-4=?):
 * 1.通过一个index值(索引),来遍历我们的表达式
 * 2.如果我们发现是一个数字,就直接入数栈
 * 3.如果发现扫描到的是符号,就分如下情况
 * 3.1如果发现当前符号栈为空,就直接入符号栈
 * 3.2如果符号栈有操作符,就进行判断,
 * 如果当前的操作符优先级小于或者等于符号栈中操作符,就需要从数栈中pop两个数出来,
 * 再从符号栈中pop出一个符号,进行运算,算出的结果入数栈,然后将当前操作符入符号栈;
 * 如果当前的操作符优先级大于栈中操作符,就直接入符号栈
 * 4.当表达式扫描完毕后,就顺序的从数栈和符号栈中pop出相应的数和符号,并进行运算
 * 5.最后在数栈只有一个数字,就是表达式的结果
 */
class ArrayStack2 {
    private int maxSize;//栈的大小
    private int[] stack;//数组模拟栈
    private int top = -1;//栈顶初始化为-1

    //构造器
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 入栈
     *
     * @param value
     */
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满,不能入栈");
            return;
        }
        top++;
        stack[top] = value;
    }

    /**
     * 出栈
     *
     * @return
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("空栈,没有数据");
        }
        int value;
        value = stack[top];
        top--;
        return value;
    }

    /**
     * 显示栈的时候，需要从栈顶开始显示数据
     */
    public void showStack() {
        if (isEmpty()) {
            System.out.println("当前为空栈");
            return;
        }
        for (int cur = top; cur >= 0; cur--) {
            System.out.printf("stack[%d]=%d\n", cur, stack[cur]);
        }
    }

    /**
     * 增加一个方法,可以返回当前栈顶的值,并不是真正的pop
     *
     * @return
     */
    public int peek() {
        return stack[top];
    }

    /**
     * 返回运算符的优先级,优先级是由开发人员决定,优先级用数字来表示
     * 暂时只支持加减乘除符号
     *
     * @param oper
     * @return
     */
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * 判断是不是一个运算符
     *
     * @param value
     * @return
     */
    public boolean isOper(char value) {
        return value == '+' || value == '-' || value == '*' || value == '/';
    }

    public int cal(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}
