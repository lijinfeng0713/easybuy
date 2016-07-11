package com.gentlemen.easybuy.service;

import android.os.AsyncTask;

import com.gentlemen.easybuy.model.MyURL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ljf-梁燕双栖 on 2016/7/1.
 */
public class SearchGoodsTask extends AsyncTask<String,Void,String> {

    private static final String url = MyURL.QUERY_BY_CONDITION;
    private String condition;

    public SearchGoodsTask (String condition) {
        this.condition = condition;
    }

    @Override
    protected String doInBackground(String... params) {

        try {
            URL httpURL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) httpURL.openConnection();
            connection.setRequestMethod("POST");
            //设置请求参数类型
            connection.setRequestProperty("Content-Type","application/json");
            connection.setReadTimeout(5000);
            connection.connect();


            String content = "{\"condition\":"+ "\""+condition +"\"}";
            System.out.println(content);
            OutputStream out = connection.getOutputStream();
            out.write(content.getBytes());

            //获取服务器返回数据
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String str;

            while ((str = reader.readLine()) != null) {
                sb.append(str);
            }
            System.out.println(sb.toString());
            return sb.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
