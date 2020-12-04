package com.kingdom.test;

import java.io.*;
import java.util.Date;


class TFile {
    public static void main(String[] args) throws Exception {
        String src = "src.log";
        String tofile = "out.log";
        write(src);
        read(src);
        copy(src, tofile);
        read(tofile);
        delete(src);
        delete(tofile);
    }


    /**
     * 写文件
     *
     * @param filename 文件名称
     */
    public static void write(String filename) {
        System.out.println("创建文件：");
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(filename));
            out.write("菜鸟教程");
            out.close();
            System.out.println("文件创建成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读文件
     *
     * @param filename 文件名称
     */
    public static void read(String filename) {
        System.out.println("\n读取文件内容：");
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            String str;
            while ((str = in.readLine()) != null) {
                System.out.println(str);
            }
            System.out.println(str);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将文件内容复制到另一个文件
     *
     * @throws Exception
     */
    public static void copy(String src, String tofile) throws Exception {
        System.out.println("\n复制文件内容:");

        InputStream in = new FileInputStream(new File(src));
        OutputStream out = new FileOutputStream(new File(tofile));
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
        System.out.println("复制文件内容成功！");
    }

    /**
     * 向文件中追加数据
     *
     * @throws Exception
     */
    public static void append() {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("filename"));
            out.write("aString1\n");
            out.close();
            out = new BufferedWriter(new FileWriter("filename", true));
            out.write("aString2");
            out.close();
            BufferedReader in = new BufferedReader(new FileReader("filename"));
            String str;
            while ((str = in.readLine()) != null) {
                System.out.println(str);
            }
            in.close();
        } catch (IOException e) {
            System.out.println("exception occoured" + e);
        }
    }

    /**
     * 删除文件
     *
     * @param filename 文件名称
     */
    private static void delete(String filename) {
        System.out.println("\n删除文件：");
        try {
            File file = new File(filename);
            if (file.delete()) {
                System.out.println(file.getName() + " 文件已被删除！");
            } else {
                System.out.println("文件删除失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/**
 * 临时文件
 */
class TempFile {
    public static void main(String[] args) throws Exception {
        File temp = File.createTempFile("testrunoobtmp", ".txt");
        System.out.println("文件路径: " + temp.getAbsolutePath());
        temp.deleteOnExit();
        BufferedWriter out = new BufferedWriter(new FileWriter(temp));
        out.write("aString");
        System.out.println("临时文件已创建!");
        out.flush();
        out.close();

        File f;

        try {

            // 创建临时文件
            f = File.createTempFile("tmp", ".txt", new File("E:/"));

            // 输出绝对路径
            System.out.println("File path: " + f.getAbsolutePath());

            // 终止后删除临时文件
            f.deleteOnExit();

            // 创建临时文件
            f = File.createTempFile("tmp", null, new File("D:/"));

            // 输出绝对路径
            System.out.print("File path: " + f.getAbsolutePath());

            // 终止后删除临时文件
            f.deleteOnExit();

        } catch (Exception e) {

            // 如果有错误输出内容
            e.printStackTrace();
        }
    }
}

/**
 * 修改文件最后的修改日期
 */
class ModifyLastModifiedDate {
    public static void main(String[] args) {
        File file = new File("out.log");
        Date filetime = new Date(file.lastModified());
        System.out.println("修改前时间：" + filetime.toString());

        file.setLastModified(System.currentTimeMillis());   // 设置为当前时间
        filetime = new Date(file.lastModified());
        System.out.println("修改后时间：" + filetime.toString());
    }
}

class FileSize {
    private static long getFileSize(String filename) {
        File file = new File(filename);
        if (!file.exists() || !file.isFile()) {
            System.out.println("文件不存在");
            return -1;
        }
        return file.length();
    }

    public static void main(String[] args) {
        long size = getFileSize("c:/java.txt");
        System.out.println("java.txt文件大小为: " + size);
    }
}

/**
 * 重命名
 */
class Rename {
    public static void main(String[] args) {
        String oldName = "out.txt";
        String newName = "out.log";
        File file = new File(oldName);
        if (file.exists()) {
            rename(file, newName);
        } else {
            file = new File(newName);
            rename(file, oldName);
        }

        System.out.println(file.setReadOnly());
        System.out.println(file.canWrite());
    }

    private static void rename(File file, String newName) {
        if (file.renameTo(new File(newName))) {
            System.out.println("已重命名");
        } else {
            System.out.println("Error");
        }

    }
}

/**
 * 新建文件
 */
class NewFile {
    public static void main(String[] args) {
        try {
            File file = new File("D:/myfile.txt");
            if (file.createNewFile())
                System.out.println("文件创建成功！");
            else
                System.out.println("出错了，该文件已经存在。");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}