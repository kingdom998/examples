package com.kingdom.test;

public class TMethod {
    public static void main(String[] args) {

    }
}

/**
 * 阶乘
 */
class Factorial {
    public static void main(String args[]) {
        for (int counter = 0; counter <= 10; counter++) {
            System.out.printf("%d! = %d\n", counter,
                    factorial(counter));
        }
    }

    private static long factorial(long number) {
        if (number <= 1)
            return 1;
        else
            return number * factorial(number - 1);
    }
}

/**
 * 方法覆盖示例
 */
class Findareas {
    public static void main(String[] agrs) {
        Figure f = new Figure(10, 10);
        Rectangle r = new Rectangle(9, 5);
        Figure figref;
        figref = f;
        System.out.println("Area is :" + figref.area());
        figref = r;
        System.out.println("Area is :" + figref.area());
    }
}

class Figure {
    double dim1;
    double dim2;

    Figure(double a, double b) {
        dim1 = a;
        dim2 = b;
    }

    Double area() {
        System.out.println("Inside area for figure.");
        return (dim1 * dim2);
    }
}

class Rectangle extends Figure {
    Rectangle(double a, double b) {
        super(a, b);
    }

    Double area() {
        System.out.println("Inside area for rectangle.");
        return (dim1 * dim2);
    }
}


/**
 * Enum（枚举）构造函数及方法的使用
 */
class Price {
    enum Car {
        lamborghini(900), tata(2), audi(50), fiat(15), honda(12);
        private int price;

        Car(int p) {
            price = p;
        }

        int getPrice() {
            return price;
        }
    }

    public static void main(String args[]) {
        System.out.println("所有汽车的价格：");
        for (Car c : Car.values())
            System.out.println(c + " 需要 "
                    + c.getPrice() + " 千美元。");
    }
}


/**
 * 重载(overloading)方法中使用 com.kingdom.test.Varargs
 */
class Varargs {
    private static void vaTest(int... no) {
        System.out.print("vaTest(int ...): "
                + "参数个数: " + no.length + " 内容: ");
        for (int n : no)
            System.out.print(n + " ");
        System.out.println();
    }

    private static void vaTest(boolean... bl) {
        System.out.print("vaTest(boolean ...) " +
                "参数个数: " + bl.length + " 内容: ");
        for (boolean b : bl)
            System.out.print(b + " ");
        System.out.println();
    }

    private static void vaTest(String msg, int... no) {
        System.out.print("vaTest(String, int ...): " +
                msg + "参数个数: " + no.length + " 内容: ");
        for (int n : no)
            System.out.print(n + " ");
        System.out.println();
    }

    public static void main(String args[]) {
        vaTest(1, 2, 3);
        vaTest("测试: ", 10, 20);
        vaTest(true, false, false);
    }
}