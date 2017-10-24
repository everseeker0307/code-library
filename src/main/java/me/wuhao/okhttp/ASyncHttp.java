package me.wuhao.okhttp;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by everseeker on 2017/7/18.
 */
public class ASyncHttp {
    MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * 1、okhttp3默认支持https协议，但是必须是由CA机构认证的https网站；如果是自建证书的网站，比如12306，可以设置信任全部证书
     *      或者下载证书后信任该证书。SSLSocketClient类实现了信任全部证书。
     * 2、构建OkhttpClient的时候可以设置多个参数，比如：
     *      超时时间connectTimeout
     *      连接池threadpool
     *      认证authenticator
     *      过滤器Interceptor
     *      缓存cache
     */
    private static final OkHttpClient client = new OkHttpClient().newBuilder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .sslSocketFactory(SSLSocketClient.getSSLSocketFactory(), SSLSocketClient.getX509TrustManager())
            .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
            .build();

    public void post(String url) {
        RequestBody formdata = new FormBody.Builder()
                    .add("endDay", "2017/07/18")
                    .add("interval", "30")
                    .add("region", "不限")
                    .build();

        Request request = new Request.Builder()
                .url(url)
                .post(formdata)
                .build();

        newcall(request);
    }

    public void post(String url, String jsonFormbody) {
        FormBody.Builder builder = new FormBody.Builder();

        // 解析字符串为json
        JsonObject json = new JsonParser().parse(jsonFormbody).getAsJsonObject();
        for (String key: json.keySet())
            builder.add(key, json.get(key).getAsString());

        RequestBody requestBody = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        newcall(request);
    }

    public void newcall(Request request) {
        client.newCall(request).enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("服务器端错误: " + response);
                }
                System.out.println(response.body().string());
            }
        });
    }

    public static void main(String[] args) throws IOException {
        ASyncHttp http = new ASyncHttp();
        String url = "http://103.27.187.156:8080/getIntervalSaledZhuzhaiHouseNumSum";
        http.post(url);
        String formdata = "{startDay:2017-06-20, endDay:2017-07-18, interval:7, region:滨湖区}";
        http.post(url, formdata);
    }
}
