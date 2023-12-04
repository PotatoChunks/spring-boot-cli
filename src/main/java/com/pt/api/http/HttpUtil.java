package com.pt.api.http;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HttpUtil {

    /**
     * 总最大连接数
     */
    private static final int MAX_TOTAL_CONNECTIONS = 1000;
    /**
     * 每个http主机的最大连接数
     */
    private static final int MAX_CONNECTIONS_PER_ROUTE = 200;
    /**
     * 建立连接的超时时间（以毫秒为单位）
     */
    private static final int CONNECT_TIMEOUT = 6000;
    /**
     * 从连接池中获取连接的超时时间（以毫秒为单位）
     */
    private static final int CONNECTION_REQUEST_TIMEOUT = 30000;
    /**
     * 等待数据的超时时间(就是请求超时时间)（以毫秒为单位）
     */
    private static final int SOCKET_TIMEOUT = 120000;
    /**
     * 空闲连接清理间隔(秒)
     */
    private static final int IDLE_CONNECTION_CLEAR_INTERVAL = 30;

    public static String get(String urlStr, Map<String, String> parameters) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true); // 设置该连接是可以输出的
        httpURLConnection.setRequestMethod("GET"); // 设置请求方式
        httpURLConnection.setRequestProperty("charset", "utf-8");
        PrintWriter pw = new PrintWriter(new BufferedOutputStream(httpURLConnection.getOutputStream()));

        StringBuilder parameter = new StringBuilder();
        parameter.append("1=1");
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            parameter.append("&").append(entry.getKey()).append("=").append(entry.getValue());
        }
        pw.write(parameter.toString());// 向连接中写数据（相当于发送数据给服务器）
        pw.flush();
        pw.close();

        BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), StandardCharsets.UTF_8));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) { // 读取数据
            sb.append(line).append("\n");
        }
        br.close();
        return sb.toString();
    }

    /**
     * POST JSON 请求
     */
    public static String post(String url, String data) throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        //装填参数
        httpPost.setHeader("Content-Type", "application/json");
        StringEntity stringEntity = new StringEntity(data, "UTF-8");
        //设置参数到请求对象中
        httpPost.setEntity(stringEntity);
        CloseableHttpResponse response = client.execute(httpPost);
        HttpEntity entity = response.getEntity();
        httpPost.abort();
        if (entity != null) {
            InputStream is = entity.getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String line = br.readLine();
            StringBuilder sb = new StringBuilder();
            while (line != null) {
                sb.append(line).append("\n");
                line = br.readLine();
            }
            return sb.toString();
        }
        return "";
    }

}
