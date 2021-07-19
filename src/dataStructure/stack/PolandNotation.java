package dataStructure.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        //先定义一个逆波兰表达式
        //(3+4)*5-6 => 3 4 + 5 * 6 - =29
        //为了方便,逆波兰表达式的数字和符号使用空格隔开
        String suffixExpression = "3 4 + 5 * 6 -";
        //思路:
        //1.先将 "3 4 + 5 * 6 -" => 放入ArrayList中
        //2.将ArrayList 传递进一个方法,遍历ArrayList配合栈完成计算
        List<String> rpnList = getListString(suffixExpression);
        System.out.println("rpnList=" + rpnList);

        int res = calculate(rpnList);
        System.out.println("计算的结果是=" + res);
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
