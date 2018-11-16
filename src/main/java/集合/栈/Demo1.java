package 集合.栈;

import java.util.Stack;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2018/11/15
 */
public class Demo1 {

    public static void main(String[] args){
        Stack<String> stringStack = new Stack<>();
        stringStack.push("A");
        stringStack.push("B");
        stringStack.push("C");
        stringStack.push("D");


//        System.out.println(stringStack.pop());
//        System.out.println(stringStack.pop());
//        System.out.println(stringStack.pop());
//        System.out.println(stringStack.pop());
//        System.out.println(stringStack.pop()); 报错误
        System.out.println(stringStack.peek());
        for(int i = 0 ;i<stringStack.size();i++){
            System.out.println(stringStack.pop());
        }

        for (String s : stringStack) {
            System.out.println(s);
        }
    }
}
