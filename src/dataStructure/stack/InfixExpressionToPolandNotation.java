package dataStructure.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InfixExpressionToPolandNotation {
    /*
    中缀表达式转后缀表达式思路:
    1.初始化两个栈:运算符栈s1和存储中间结果的栈s2
    2.从左至右扫描中缀表达式
    3.遇到数字时将其压入s2
    4.遇到运算符时比较其与s1栈顶运算符的优先级
    4.1如果s1为空,或者栈顶运算符为左括号'(',则直接将此运算符入栈
    4.2否则,若优先级比栈顶运算符高,也将其压入s1
    4.3否则,将s1栈顶的运算符弹出并压入到s2中,再次转到4.1与s1中的新的栈顶运算符相比较
    5.遇到括号时
    5.1如果是左括号'(',则直接压入s1
    5.2如果是右括号')',则依次弹出s1栈顶的运算符,并压入s2,遇到左括号为止,此时将这一对括号丢弃
    6.重复步骤2-5直到表达式的最右边
    7.将s1中剩余的运算符一次弹出并压入s2中
    8.依次弹出s2中的元素并输出,结果的逆序输出即为中缀表达式对应的后缀表达式(逆波兰表达式)
     */
    public static void main(String[] args) {
        //完成将中缀表达式转换成后缀表达式的功能
        //1. 1+((2+3)*4)-5 => 1 2 3 + 4 * + 5 -
        //2.因为直接对string进行扫描操作,不方便,因此先将"1+((2+3)*4)-5" => 中缀表达式对应的list
        //  即"1+((2+3)*4)-5" => ArrayList[1, +, (, (, 2, +, 3, ), *, 4, ), -, 5]
        //3.将得到的中缀表达式对应的list => 后缀表达式对应的list
        // 即 ArrayList[1, +, (, (, 2, +, 3, ), *, 4, ), -, 5] => ArrayList[1, 2, 3, +, 4, *, +, 5, -]
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("中缀表达式对应的list=" + infixExpressionList);
        List<String> parseSuffixExpressionList = parseSuffixExpression(infixExpressionList);
        System.out.println("后缀表达式对应的list=" + parseSuffixExpressionList);
        //调用caculate方法使用后缀表达式计算结果
        System.out.println("expression=" + calculate(parseSuffixExpressionList));

//        //先定义一个逆波兰表达式
//        //(3+4)*5-6 => 3 4 + 5 * 6 - =29
//        //为了方便,逆波兰表达式的数字和符号使用空格隔开
//        String suffixExpression = "3 4 + 5 * 6 -";
//        //思路:
//        //1.先将 "3 4 + 5 * 6 -" => 放入ArrayList中
//        //2.将ArrayList 传递进一个方法,遍历ArrayList配合栈完成计算
//        List<String> rpnList = getListString(suffixExpression);
//        System.out.println("rpnList=" + rpnList);
//
//        int res = calculate(rpnList);
//        System.out.println("计算的结果是=" + res);
    }

    //方法: 将得到的中缀表达式对应的list => 后缀表达式对应的list
    // 即 ArrayList[1, +, (, (, 2, +, 3, ), *, 4, ), -, 5] => ArrayList[1, 2, 3, +, 4, *, +, 5, -]
    public static List<String> parseSuffixExpression(List<String> ls) {
        //定义两个栈
        Stack<String> s1 = new Stack<String>();//符号栈
        //说明:因为s2这个栈,在整个转换过程中没有pop操作,而且后面还需要逆序输出
        //因此比较麻烦,这里就不采用Stack<String>直接使用List<String> s2
        List<String> s2 = new ArrayList<String>();//存储中间结果栈

        //遍历ls
        for (String item : ls) {
            //如果是一个数,假如s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                //如果是右括号')',则依次弹出s1栈顶的运算符,并压入s2,遇到左括号为止,此时将这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();//将这个小括号弹出s1,消除小括号
            } else {
                //当item的优先级小于或者等于s1栈顶运算符的优先级,将s1栈顶的运算符弹出并加入到s2中,再次转到4.1与s1中的新的栈顶运算符相比较
                //缺少一个比较优先级高地的方法
                while (s1.size() != 0 && (Operation.getValue(item) <= Operation.getValue(s1.peek()))) {
                    s2.add(s1.pop());
                }
                //还需要将item压入栈
                s1.push(item);
            }
        }
        //将s1中剩余的运算符一次弹出并压入s2中
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;//因为是存放在list中,因此按顺序输出就是对应的后缀表达式对应的list
    }

    //方法: 将中缀表达式转成对应的list
    public static List<String> toInfixExpressionList(String s) {
        List<String> ls = new ArrayList<String>();
        int index = 0;//这是一个指针,用于遍历中缀表达式字符串
        String str;//用于多位数拼接
        char c = ' ';//每遍历到一个字符,就放入到c
        do {
            //如果c是一个非数字,需要加入到ls
            if ((c = s.charAt(index)) < 48 || ((c = s.charAt(index)) > 57)) {
                ls.add("" + c);
                index++;//索引需要向后移动
            } else {//如果是一个数则需要考虑多位数的问题
                str = "";//先将str置成空串
                while (index < s.length() && (c = s.charAt(index)) >= 48 && (c = s.charAt(index)) <= 57) {
                    str += c;//拼接
                    index++;//索引需要向后移动
                }
                ls.add(str);
            }
        } while (index < s.length());
        return ls;
    }

    //将一个逆波兰表达式,依次将数据和运算符放入到ArrayList中
    public static List<String> getListString(String suffixExpression) {
        String[] split = suffixExpression.split(" ");//将suffixExpression进行分割
        List<String> list = new ArrayList<String>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    //完成对逆波兰表达式的运算
    /*
    从左至右扫描,将3和4压入数栈,
    遇到+运算符弹出4和3(4是栈顶元素,3是次顶元素),计算出3+4的数值,得到7,再入栈,
    继续扫描将5入栈,
    接下来是x运算符,因此弹出5和7,计算出7x5=35,将35入栈,
    将6入栈,
    最后是-运算符,计算出35-6的值,为29,由此得出最终结果
     */
    public static int calculate(List<String> ls) {
        //创建一个栈即可
        Stack<String> stack = new Stack<String>();
        //遍历ls
        for (String item : ls) {
            //这里使用正则表达式来取出数
            if (item.matches("\\d+")) { //匹配多位数
                //入栈
                stack.push(item);
            } else {
                //pop出两个数后运算,并将结果入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;//后弹出来的-先弹出来的
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误~");
                }
                //将结果入栈
                stack.push("" + res);//将整数转为字符串(也可用String.valueof())
            }
        }
        //最后留在stack中的数据是运算结果
        return Integer.parseInt(stack.pop());
    }
}

//编写一个类Operation 可以返回一个运算符对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //写一个方法,返回对应的优先级数字
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
        }
        return result;
    }
}
