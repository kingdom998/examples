package com.kingdom.test;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

/**
 * 在链表（LinkedList）的开头和结尾添加元素
 */
class TLinkedList {
    public static void main(String[] args) {
        LinkedList<String> lList = new LinkedList<>();
        lList.add("1");
        lList.add("2");
        lList.add("3");
        lList.add("4");
        lList.add("5");
        lList.add("2");
        System.out.println(lList);
        lList.addFirst("0");
        System.out.println(lList);
        lList.addLast("6");
        System.out.println(lList);
        lList.subList(2, 4).clear();
        System.out.println(lList);
        lList.remove(0);
        System.out.println(lList);
        System.out.println("元素 2 第一次出现的位置：" + lList.indexOf("2"));
        System.out.println("元素 2 最后一次出现的位置："+ lList.lastIndexOf("2"));
    }
}

/**
 * 获取向量元素的索引值
 */
class TVector {
    public static void main(String[] args) {
        Vector v = new Vector();
        v.add("X");
        v.add("M");
        v.add("D");
        v.add("A");
        v.add("O");
        Collections.sort(v);
        System.out.println(v);
        int index = Collections.binarySearch(v, "D");
        System.out.println("元素索引值为 : " + index);

        Object obj = Collections.max(v);
        System.out.println("最大元素是："+obj);

        Collections.swap(v, 0, 3);
        System.out.println("旋转后");
        System.out.println(v);
    }
}

/**
 * 栈的实现
 */
class TStack {
    private int maxSize;
    private long[] stackArray;
    private int top;
    public TStack(int s) {
        maxSize = s;
        stackArray = new long[maxSize];
        top = -1;
    }
    public void push(long j) {
        stackArray[++top] = j;
    }
    public long pop() {
        return stackArray[top--];
    }
    public long peek() {
        return stackArray[top];
    }
    public boolean isEmpty() {
        return (top == -1);
    }
    public boolean isFull() {
        return (top == maxSize - 1);
    }
    public static void main(String[] args) {
        TStack theStack = new TStack(10);
        theStack.push(10);
        theStack.push(20);
        theStack.push(30);
        theStack.push(40);
        theStack.push(50);
        while (!theStack.isEmpty()) {
            long value = theStack.pop();
            System.out.print(value);
            System.out.print(" ");
        }
        System.out.println("");
    }
}

/**
 * 压栈出栈的方法实现字符串反转
 */
class StringReverserThroughStack {
    private String input;
    private String output;
    public StringReverserThroughStack(String in) {
        input = in;
    }

    /**
     * 字符串反转
     * @return 反转后的字符串
     */
    public String doRev() {
        // 进栈
        int stackSize = input.length();
        Stack theStack = new Stack(stackSize);
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            theStack.push(ch);
        }
        // 出栈
        output = "";
        while (!theStack.isEmpty()) {
            char ch = theStack.pop();
            output = output + ch;
        }
        return output;
    }
    class Stack {
        private int top;
        private int maxSize;
        private char[] stackArray;
        public Stack(int max) {
            top = -1;
            maxSize = max;
            stackArray = new char[maxSize];
        }
        public void push(char j) {
            stackArray[++top] = j;
        }
        public char pop() {
            return stackArray[top--];
        }
        public char peek() {
            return stackArray[top];
        }
        public boolean isEmpty() {
            return (top == -1);
        }
    }

    public static void main(String[] args)
            throws IOException {
        String input = "www.w3cschool.cc";
        String output;
        StringReverserThroughStack theReverser =
                new StringReverserThroughStack(input);
        output = theReverser.doRev();
        System.out.println("反转前： " + input);
        System.out.println("反转后： " + output);
    }
}

/**
 * 队列（Queue）用法
 */
class TQueue {
    public static void main(String[] args) {
        //add()和remove()方法在失败的时候会抛出异常(不推荐)
        Queue<String> queue = new LinkedList<>();
        //添加元素
        System.out.println("所有元素：");
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");
        queue.offer("d");
        queue.offer("e");
        for(String q : queue){
            System.out.print(q + " ");
        }

        System.out.println();
        //返回第一个元素，并在队列中删除
        System.out.println("返回第一个元素，并在队列中删除：poll="+queue.poll());
        System.out.println("剩余元素：");
        for(String q : queue){
            System.out.print(q + " ");
        }

        System.out.println("\n返回第一个元素");
        System.out.println("element="+queue.element()); //返回第一个元素
        System.out.println("\n返回第一个元素");
        System.out.println("peek="+queue.peek()); //返回第一个元素
    }
}