package cn.mark.stack;

/**
 * 采用栈的特点来实现简单计算器（中缀表达式）
 * 中缀表达式的求值是我们人最熟悉的，但是对计算机来说却不好操作(前面我们讲的案例就能看的这个问题)，
 * 因此，在计算结果时，往往会将中缀表达式转成其它表达式来操作(一般转成后缀表达式.)
 *
 * @author Mark
 * @create 2020/2/24
 */
public class CalculateDemo {

    public static void main(String[] args) {
        String expression = "7*2*2-5+1-5+3-4";
        //创建两个栈，数栈和操作符栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        int num1 = 0;
        int num2 = 0;
        int oper = ' ';
        int result = 0;
        String numStr = "";//用于拼接多位数
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            //如果是操作符
            if (operStack.isOperator(ch)) {
                //如果操作符栈为空，则直接入栈
                if (operStack.isEmpty()) {
                    operStack.push(ch);
                } else {
                    //操作符栈不为空，需要进一步判断
                    //如果当前操作符的优先级小于等于操作符栈顶的优先级，则从数栈中弹出两个数，从操作符栈中弹出一个操作符，进行运算后入数栈，当前操作符入操作符栈
                    if (operStack.getPriority(ch) <= operStack.getPriority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        result = numStack.calculate(num1, num2, oper);
                        numStack.push(result);
                        operStack.push(ch);
                    } else {
                        //如果当前操作符的优先级大于操作符栈顶的优先级，则直接入栈
                        operStack.push(ch);
                    }
                }
            } else {
                //数字需要考虑多位的情况
                numStr += ch;
                //如果遍历到最后一位则直接入栈
                if (i == expression.length() - 1) {
                    numStack.push(Integer.parseInt(numStr));
                } else {
                    //如果当前数的后一位是操作符时，则数字拼接结束，进行入栈
                    if (operStack.isOperator(expression.charAt(i + 1))) {
                        numStack.push(Integer.parseInt(numStr));
                        numStr = "";
                    }
                }
            }
        }

        //遍历完后，依次从数栈中弹出2个数，从操作符弹出一个操作符，进行运算后在入数栈
        while (!operStack.isEmpty()) {
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            result = numStack.calculate(num1, num2, oper);
            numStack.push(result);
        }
        System.out.printf("%s = %d", expression, numStack.pop());
    }
}

//采用数组来模拟栈
class ArrayStack2 {
    private int maxSize;//栈的大小
    private int[] stack;//数组模拟栈，数据存储在数组中
    private int top = -1;//栈顶，开始为-1

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new int[maxSize];
    }

    /**
     * 判断是否为操作符
     *
     * @param ch
     * @return
     */
    public boolean isOperator(char ch) {
        return '+' == ch || '-' == ch || '*' == ch || '/' == ch;
    }

    /**
     * 获取操作符的优先级
     *
     * @param operator
     * @return
     */
    public int getPriority(int operator) {
        if ('*' == operator || '/' == operator) {
            return 1;
        } else if ('+' == operator || '-' == operator) {
            return 0;
        } else {
            return -1;//暂不考虑其他操作符
        }
    }

    /**
     * 计算结果
     *
     * @param num1
     * @param num2
     * @param oper
     * @return
     */
    public int calculate(int num1, int num2, int oper) {
        int result = 0;
        switch (oper) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num2 - num1;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;
            default:
                break;
        }
        return result;
    }

    //判断栈是否为空
    public boolean isEmpty() {
        return top == -1;
    }

    //判断栈是否满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //查看栈顶的数据
    public int peek() {
        return stack[top];
    }

    //入栈
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满，无法添加~");
            return;
        }
        this.stack[++top] = value;
    }

    //出栈，从栈顶弹出数据
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空，无法出栈");
        }
        return this.stack[top--];
    }

    //遍历栈，从栈顶开始遍历
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，无法遍历，请添加数据");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d\n", i, this.stack[i]);
        }
    }
}

