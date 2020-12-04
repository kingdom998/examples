package com.kingdom.test.demo;

import cn.hutool.json.JSONArray;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class ShipMng {
    public static void main(String[] args) throws IOException {
        ShipMng shipMng = new ShipMng();
        int mmsi = 413454360;
        String content = shipMng.getShipName(mmsi);
        shipMng.parserShipName(content);

    }


    private String getShipName(int mmsi) throws IOException {
        String content = null;
        // Post请求的url，与get不同的是不需要带参数
        URL postUrl = new URL("http://www.shipxy.com/ship/getSysShipName");
        // 打开连接
        HttpURLConnection conn = (HttpURLConnection) postUrl.openConnection();
        conn.setRequestMethod("POST");  // 默认是 GET方式
        conn.setDoInput(true);          // Read from the connection. Default is true.
        conn.setDoOutput(true);         // 是否向connection输出，post请求，参数要放在http正文内
        conn.setUseCaches(false);       // Post 请求不能使用缓存
        conn.setReadTimeout(30000);     //30秒读取超时
        conn.setConnectTimeout(30000);  //30秒连接超时
        conn.setInstanceFollowRedirects(true);  //设置本次连接是否自动重定向

        conn.setRequestProperty("host", "www.shipxy.com");
        conn.setRequestProperty("Referer", "http://www.shipxy.com/*");
        conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        conn.connect();         // 配置必须要在connect之前完成， 要注意的是connection.getOutputStream会隐含的进行connect

        // 此处getOutputStream会隐含的进行connect(即：如同调用上面的connect()方法，
        // 所以在开发中不调用上述的connect()也可以)。
        OutputStream out = conn.getOutputStream();
        String params = String.format("mmsi=%s", mmsi);
        out.write(params.getBytes());
        out.flush(); //清空缓冲区,发送数据
        out.close();

        // 解析获取到数据
        if (200 == conn.getResponseCode()) {
            // 调用HttpURLConnection连接对象的getInputStream()函数,
            // 将内存缓冲区中封装好的完整的HTTP请求电文发送到服务端。
            InputStream inputStream = conn.getInputStream();     // <===注意，实际发送请求的代码段就在这里
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            content = br.readLine();
            br.close();
        }

        conn.disconnect();  //该干的都干完了,记得把连接断了

        return content;
    }


    private void parserShipName(String content){
        JSONArray jsonArray = new JSONArray(content);
        List<Map<String, Object>> mapListJson = (List)jsonArray;

        for (Map<String, Object> item: mapListJson){
            System.out.println("MMSI : " + item.get("MMSI"));
            System.out.println("NameCN : " + item.get("NameCN"));
            System.out.println("NameEN : " + item.get("NameEN"));
        }

    }
}
