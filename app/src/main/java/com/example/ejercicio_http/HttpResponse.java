package com.example.ejercicio_http;

import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpResponse {
    public static Object getHttpResponse(String url){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = null;

        try{
            response = okHttpClient.newCall(request).execute();
            return response.body().string();
        }catch (IOException ex){
            Log.e("HTTP", "Error getting get response.");
        }

        return null;
    }

    public static Object getParamHttpResponse(String url) {
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = null;

        try {
            response = httpClient.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            Log.e("HTTP", "Error getting get params response.");
        }
        return null;
    }


    public static Object postHttpResponse(String url, String[] requestParam) {
        OkHttpClient httpClient = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("userId", String.valueOf(requestParam[0]))
                .add("title", requestParam[1])
                .add("body", requestParam[2])
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        Response response = null;

        try {
            response = httpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }

        } catch (IOException e) {
            Log.e("HTTP", "Error getting post response.");
        }
        return null;
    }
}
