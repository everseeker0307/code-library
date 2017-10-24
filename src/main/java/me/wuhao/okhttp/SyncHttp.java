package me.wuhao.okhttp;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * Created by everseeker on 2017/7/18.
 */
public class SyncHttp {
    public String get(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("服务器端错误: " + response);
            }
            return response.body().string();    // 调用response.body().xxx后会自动关闭response
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws IOException {
        String result = new SyncHttp().get("http://103.27.187.156");
        System.out.println(result);
    }
}
