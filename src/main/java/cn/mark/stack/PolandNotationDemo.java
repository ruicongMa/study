package cn.mark.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 后缀表达式的实现(逆波兰表达式)
 * 中缀表达式转为后缀表达式
 *
 * @author Mark
 * @create 2020/2/24
 */
public class PolandNotationDemo {

    public static void main(String[] args) {

        //(30+4)*5-6  => 30 4 + 5 * 6 - => 164
        // String suffixExpress = "30 4 + 5 * 6 -";
        // System.out.println("逆波兰表达式计算结果为：" + calculate(suffixExpressionToList(suffixExpress)));

        String originExpress = "1+((2+3)*4)-5";
        List<String> middleExpress = originExpressToMiddleExpress(originExpress);
        System.out.println("中缀表达式结果为：" + middleExpress);//[1,+,(,(,2,+,3,),*,4,),-,5]
        List<String> suffixExpress = middleExpressToSuffixExpress(middleExpress);
        System.out.println("后缀表达式结果为：" + suffixExpress);//[1,2,3,+,4,*,+,5,–]
        System.out.printf("%s = %d", originExpress, calculate(suffixExpress));
    }

    /**
     * 将中缀表达式转为后缀表达式
     * 思路：
     * 1.定义两个栈，s1存储运算符，s2存储中间结果
     * 2.从左向右扫描中缀表达式
     * 3.遇到操作数，则直接入栈s2
     * 4.遇到运算符
     * 4.1.如果s1为空，或栈顶运算符为左括号"("，则直接将此运算符入栈
     * 4.2.否则，若优先级比栈顶运算符高，也将运算符入栈
     * 4.3.否则，将s1栈顶的运算符弹出并压入s2，再次转到4.1与s1中新的栈顶运算符比较
     * 5.遇到括号时：
     * 5.1.如果是左括号"("，则直接入栈s1
     * 5.2.如果是右括号")",则依次弹出s1栈顶的运算符，并入栈s2，直到遇到左括号为止，此时将这一对括号丢弃
     * 6.重复步骤2到5，直到表达式的最右边
     * 7.将s1中剩余的运算符依次弹出压入s2
     * 8.依次弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
     *
     * @param middleExpressList 中缀表达式List
     * @return 后缀表达式List
     */
    public static List<String> middleExpressToSuffixExpress(List<String> middleExpressList) {
        //定义两个栈
        Stack<String> s1 = new Stack<>();//存储运算符
        // Stack<String> s2 = new Stack<>();//存储中间结果，因为s2一直入栈，并未出栈过，可以将其定义为List，这样到第8步后就不需要将结果进行逆序了，就是对应的后缀表达式
        List<String> s2 = new ArrayList<>();
        for (String item : middleExpressList) {
            //如果是数字直接入栈s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if ("(".equals(item)) {
                s1.push(item);
            } else if (")".equals(item)) {
                //则依次弹出s1栈顶的运算符，并入栈s2，直到遇到左括号为止，此时将这一对括号丢弃
                while (!s1.isEmpty() && !s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();//弹出右括号，即消除一对括号
            } else {
                //比较操作符优先级
                // 若优先级小于等于栈顶运算符优先级,将s1栈顶的运算符弹出并压入s2，再次转到4.1与s1中新的栈顶运算符比较
                while (!s1.isEmpty() && Operation.getPriority(item) <= Operation.getPriority(s1.peek())) {
                    s2.add(s1.pop());
                }
                // 若优先级比栈顶运算符高，也将运算符入栈
                s1.push(item);
            }
        }
        //将s1剩余的运算符依次弹出入栈s2
        while (!s1.isEmpty()) {
            s2.add(s1.pop());
        }
        return s2;
    }

    /**
     * 将运算表达式字符串转为中缀表达式
     *
     * @param originExpress
     * @return
     */
    public static List<String> originExpressToMiddleExpress(String originExpress) {
        List<String> list = new ArrayList<>();
        String numStr = "";//用于多位数的拼接
        for (int i = 0; i < originExpress.length(); i++) {
            char ch = originExpress.charAt(i);
            if (ch < 48 || ch > 57) {
                list.add(String.valueOf(ch));
            } else {//数字
                numStr += ch;
                if (i == originExpress.length() - 1) {
                    list.add(numStr);
                } else {
                    //如果当前数的下一位为非数字则添加
                    if (originExpress.charAt(i + 1) < 48 || originExpress.charAt(i + 1) > 57) {
                        list.add(numStr);
                        numStr = "";
                    }
                }
            }
        }
        return list;
    }

    /**
     * 完成对逆波兰表达式的计算
     * 思路：
     * 1.遍历逆波兰表达式集合，如果当前项为数字，则直接入栈
     * 2.如果当前项为运算符，则弹出两个数，计算后入栈
     * 3.遍历完毕后，栈顶即为最后运算的结果
     *
     * @param suffixExpressList 逆波兰表达式List即后缀表达式List
     * @return
     */
    public static int calculate(List<String> suffixExpressList) {
        Stack<String> stack = new Stack<>();
        for (String item : suffixExpressList) {
            //如果是数字则直接入栈
            if (item.matches("\\d+")) {//匹配多位
                stack.push(item);
            } else {
                //如果是运算符则，pop出两个数，运算后入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int result = 0;
                if ("+".equals(item)) {
                    result = num1 + num2;
                } else if ("-".equals(item)) {
                    result = num1 - num2;
                } else if ("*".equals(item)) {
                    result = num1 * num2;
                } else if ("/".equals(item)) {
                    result = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误~");
                }
                stack.push(String.valueOf(result));
            }
        }
        return Integer.parseInt(stack.pop());
    }

    /**
     * 将一个逆波兰表达式，依次将数据和运算符放入到List中
     *
     * @param suffixExpression 逆波兰表达式
     * @return
     */
    public static List<String> suffixExpressionToList(String suffixExpression) {
        List<String> list = new ArrayList<>();
        for (String item : suffixExpression.split(" ")) {
            list.add(item);
        }
        return list;
    }

}

//编写一个类 Operation 可以返回一个运算符 对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //写一个方法，返回对应的优先级数字
    public static int getPriority(String operation) {
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
                System.out.println("不存在该运算符" + operation);
                break;
        }
        return result;
    }

}
