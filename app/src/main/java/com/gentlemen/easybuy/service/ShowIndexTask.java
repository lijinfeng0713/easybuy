package com.gentlemen.easybuy.service;

import android.os.AsyncTask;

import com.gentlemen.easybuy.model.MyURL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ljf-梁燕双栖 on 2016/6/30.
 */
public class ShowIndexTask extends AsyncTask<Void,Void,String> {

    private static final String url = MyURL.INDEX_URL;

    @Override
    protected String doInBackground(Void... params) {
        try {
            URL httpURL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) httpURL.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(50000);
            connection.connect();

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
