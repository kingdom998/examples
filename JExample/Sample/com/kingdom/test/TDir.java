package com.kingdom.test;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;

public class TDir {
    public static void main(String[] args) {
        String dirs = "D:\\a\\b\\c";
        isEmpDir(dirs);
//        mdDirs(dirs);
        delDir(new File(dirs));
    }

    /**
     * 目录为空
     * @param dir
     * @return
     */
    public static boolean isEmpDir(String dir) {
        File file = new File(dir);
        boolean isdir = file.isDirectory();
        if (isdir) {
            String[] files = file.list();
            if (files.length > 0) {
                System.out.println("目录 " + file.getPath() + " 不为空！");
            }
        }
        else {
            System.out.println(isdir);
        }

        return true;
    }

    /**
     * 创建目录
     * @param dirs
     * @return
     */
    public static boolean mdDirs(String dirs) {
        File file = new File(dirs);
        boolean result = file.mkdirs();
        System.out.println("Status = " + result);

        return result;
    }

    /**
     * 删除目录
     * @param dir
     * @return
     */
    public static boolean delDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = delDir
                        (new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        if (dir.delete()) {
            System.out.println("目录已被删除！");
            return true;
        } else {
            System.out.println("目录删除失败！");
            return false;
        }
    }
}

/**
 * 打印目录结构
 */
class FileUtil {
    public static void main(String[] a)throws IOException {
        showDir(1, new File("d:\\Downloads"));
    }
    static void showDir(int indent, File file) throws IOException {
        for (int i = 0; i < indent; i++)
            System.out.print('-');
        System.out.println(file.getName());
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++)
                showDir(indent + 4, files[i]);
        }
    }
}

/**
 * 遍历指定目录下的所有目录
 */
class ListDir {
    public static void main(String[] args) {
        File dir = new File("D:\\Downloads");
        FileFilter fileFilter = file -> file.isDirectory();
        File[] files = dir.listFiles(fileFilter);
        System.out.println(files.length);
        if (files.length == 0) {
            System.out.println("目录不存在或它不是一个目录");
        }
        else {
            for (int i=0; i< files.length; i++) {
                File filename = files[i];
                System.out.println(filename.toString());
            }
        }
    }
}


/**
 * 在指定目录中查找文件
 */
class FindFileInDir {
    public static void main(String[] args) {
        File dir = new File("C:");
        FilenameFilter filter = (dir1, name) -> name.startsWith("b");
        String[] children = dir.list(filter);
        if (children == null) {
            System.out.println("目录不存在或它不是一个目录");
        }
        else {
            for (int i=0; i < children.length; i++) {
                String filename = children[i];
                System.out.println(filename);
            }
        }
    }
}

/**
 * 查看系统根目录
 */
class ListRoot{
    public static void main(String[] args){
        File[] roots = File.listRoots();
        System.out.println("系统所有根目录：");
        for (int i=0; i < roots.length; i++) {
            System.out.println(roots[i].toString());
        }
    }
}


/**
 * 查看当前工作目录
 */
class GetCurDir {
    public static void main(String[] args) {
        String curDir = System.getProperty("user.dir");
        System.out.println("你当前的工作目录为 :" + curDir);
    }
}

/**
 * 遍历目录
 */
class ListAllDir {
    public static void main(String[] argv) throws Exception {
        System.out.println("遍历目录");
        File dir = new File("D:/Downloads"); //要遍历的目录
        visitAllDirsAndFiles(dir);
    }
    public static void visitAllDirsAndFiles(File dir) {
        System.out.println(dir);
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                visitAllDirsAndFiles(new File(dir, children[i]));
            }
        }
    }
}