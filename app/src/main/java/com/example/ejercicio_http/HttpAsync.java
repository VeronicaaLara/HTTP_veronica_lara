package com.example.ejercicio_http;

import android.content.Context;
import android.os.AsyncTask;

public class HttpAsync extends AsyncTask<Object, Void, Object> {
    public AsyncResponse delegate = null;
    public HttpAsync() { }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Object... params) {
        Context context = (Context) params[0];
        String requestType = (String) params[1];
        Object requestParam = params[2];

        if("get".equals(requestType)){
            return HttpResponse.getHttpResponse("https://jsonplaceholder.typicode.com/posts/");
        }else if("getparam".equals(requestType)){
            return HttpResponse.getParamHttpResponse("https://jsonplaceholder.typicode.com/posts/" + requestParam);
        }else if("post".equals(requestType)){
            return HttpResponse.postHttpResponse("https://jsonplaceholder.typicode.com/posts/", (String[]) requestParam);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(result);
        if(result != null){
            delegate.processFinish(result);
        }
    }
}
