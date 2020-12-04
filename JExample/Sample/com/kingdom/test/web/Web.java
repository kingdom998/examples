package com.kingdom.test.web;

import java.io.*;
import java.net.*;
import java.util.Date;

public class Web {
}

/**
 * 获取IP地址
 */
class GetIP {
    public static void main(String[] args) throws Exception {
        getRemote();
        System.out.println();
        getLocalHost();
    }


    /**
     * 获取指定主机的IP地址
     */
    public static void getRemote() {
        InetAddress addr = null;
        try {
            addr = InetAddress.getByName("www.runoob.com");
        } catch (UnknownHostException e) {
            System.exit(2);
        }

        System.out.println("Remote HostAddress: " + addr.getHostAddress());
        String hostname = addr.getHostName();
        System.out.println("Remote host name: " + hostname);
    }


    /**
     * 获取本机ip地址及主机名
     *
     * @throws Exception
     */
    public static void getLocalHost() throws Exception {
        InetAddress addr = InetAddress.getLocalHost();
        System.out.println("Local HostAddress: " + addr.getHostAddress());
        String hostname = addr.getHostName();
        System.out.println("Local host name: " + hostname);
    }
}

/**
 * 查看端口是否已使用
 */
class PortIsUsedOrNot {
    public static void main(String[] args) {
        Socket Skt;
        String host = "localhost";
        if (args.length > 0) {
            host = args[0];
        }
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("查看 " + i);
                Skt = new Socket(host, i);
                System.out.println(Skt.toString());
                System.out.println("端口 " + i + " 已被使用");
            } catch (UnknownHostException e) {
                System.out.println("Exception occured" + e);
                break;
            } catch (IOException e) {
            }
        }
    }
}



/**
 * Socket 实现多线程服务器程序
 */
class MultiThreadServer implements Runnable {
    Socket csocket;

    MultiThreadServer(Socket csocket) {
        this.csocket = csocket;
    }

    public static void main(String args[])
            throws Exception {
        ServerSocket ssock = new ServerSocket(1234);
        System.out.println("Listening");
        while (true) {
            Socket sock = ssock.accept();
            System.out.println("Connected");
            new Thread(new MultiThreadServer(sock)).start();
        }
    }

    public void run() {
        try {
            PrintStream pstream = new PrintStream
                    (csocket.getOutputStream());
            for (int i = 100; i >= 0; i--) {
                pstream.println(i +
                        " bottles of beer on the wall");
            }
            pstream.close();
            csocket.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}

/**
 * 使用 Socket 连接到指定主机
 */
class WebPing {
    public static void main(String[] args) {
        try {
            InetAddress addr;
            Socket sock = new Socket("www.runoob.com", 80);
            addr = sock.getInetAddress();
            System.out.println("连接到 " + addr);
            sock.close();
        } catch (java.io.IOException e) {
            System.out.println("无法连接 " + args[0]);
            System.out.println(e);
        }
    }
}

/**
 * 网页抓取
 */
class WebScrap {
    public static void main(String[] args)
            throws Exception {
        URL url = new URL("http://www.runoob.com");
        BufferedReader reader = new BufferedReader
                (new InputStreamReader(url.openStream()));
        BufferedWriter writer = new BufferedWriter
                (new FileWriter("data.html"));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            writer.write(line);
            writer.newLine();
        }
        reader.close();
        writer.close();
    }
}


/**
 * 解析 URL
 */
class ParseUrl {
    public static void main(String[] args)
            throws Exception {
        URL url = new URL("http://www.runoob.com/html/html-tutorial.html");
        System.out.println("URL 是 " + url.toString());
        System.out.println("协议是 " + url.getProtocol());
        System.out.println("文件名是 " + url.getFile());
        System.out.println("主机是 " + url.getHost());
        System.out.println("路径是 " + url.getPath());
        System.out.println("端口号是 " + url.getPort());
        System.out.println("默认端口号是 "
                + url.getDefaultPort());

        // 获取 URL响应头的日期信息
        HttpURLConnection httpCon =
                (HttpURLConnection) url.openConnection();
        long dt = httpCon.getDate();
        if (dt == 0)
            System.out.println("无法获取信息。");
        else
            System.out.println("链接时间: " + new Date(dt));

        // 获取远程文件大小
        int size;
        URLConnection conn = url.openConnection();
        size = conn.getContentLength();
        if (size < 0)
            System.out.println("无法获取文件大小。");
        else
            System.out.println("文件大小为：" + size + " bytes");

        // 文件最后修改时间
        long timestamp = conn.getLastModified();
        Date date = new Date(timestamp);
        System.out.println("文件最后修改时间 :" + date.toString());

        conn.getInputStream().close();
    }
}
