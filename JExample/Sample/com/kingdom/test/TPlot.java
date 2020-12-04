package com.kingdom.test;

/**
 * 打印图形
 */
public class TPlot {
    public static void main(String[] args) {

    }
}


/**
 * 菱形
 */
class Diamond {
    public static void main(String[] args) {
        print(8); // 输出 8 行的菱形
    }

    public static void print(int size) {
        if (size % 2 == 0) {
            size++; // 计算菱形大小
        }
        for (int i = 0; i < size / 2 + 1; i++) {
            for (int j = size / 2 + 1; j > i + 1; j--) {
                System.out.print(" "); // 输出左上角位置的空白
            }
            for (int j = 0; j < 2 * i + 1; j++) {
                System.out.print("*"); // 输出菱形上半部边缘
            }
            System.out.println(); // 换行
        }
        for (int i = size / 2 + 1; i < size; i++) {
            for (int j = 0; j < i - size / 2; j++) {
                System.out.print(" "); // 输出菱形左下角空白
            }
            for (int j = 0; j < 2 * size - 1 - 2 * i; j++) {
                System.out.print("*"); // 输出菱形下半部边缘
            }
            System.out.println(); // 换行
        }
    }
}

/**
 * 九九乘法表
 */
class MultiplicationTable {
    public static void main(String[] args) {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + "×" + i + "=" + i * j + "\t");// \t 跳到下一个TAB位置
            }
            System.out.println();
        }
    }
}

/**
 * 三角形
 */
class Triangle {
    public static void main(String[] args) {
        Triangle triangle = new Triangle();
        triangle.triangle();
        System.out.println();
        triangle.invertedTriangle();

    }

    /**
     * 三角形
     */
    public void triangle() {
        for (int i = 1; i <= 5; i++) {
            for (int j = 5; i <= j; j--)
                System.out.print(" ");
            for (int j = 1; j <= i; j++)
                System.out.print("*");
            for (int j = 1; j < i; j++)
                System.out.print("*");
            System.out.println();
        }
    }

    /**
     * 倒立的三角形
     */
    public void invertedTriangle() {

        for (int m = 1; m <= 4; m++) {
            //打印空格
            for (int n = 0; n <= m; n++) {
                System.out.print(" ");
            }
            //打印*
            for (int x = 1; x <= 7 - 2 * (m - 1); x++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}


/**
 * 平行四边形
 */
class Parallelogram {
    public static void main(String[] args) {
        //外层循环 每次打出一个*
        for (int i = 1; i <= 5; i++) {
            //填充空格
            for (int j = 1; j <= 5 - i; j++) {
                System.out.print(" ");
            }
            //内层循环 每次打印一个*
            for (int k = 1; k <= 5; k++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}

/**
 * 矩形
 */
class Rect {
    public static void main(String[] args) {
        //外层循环 每次输出一行*
        for (int i = 1; i <= 5; i++) {
            System.out.print("*");
            //内层循环 每次输出一个*
            for (int j = 1; j <= 5; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}