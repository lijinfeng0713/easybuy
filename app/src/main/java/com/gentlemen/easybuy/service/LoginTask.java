package com.gentlemen.easybuy.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.gentlemen.easybuy.model.MyURL;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ljf-梁燕双栖 on 2016/6/30.
 */
public class LoginTask extends AsyncTask<String, Void, String> {

    private static String url = MyURL.LOGIN_URL;

    private String username;
    private String password;
    private Context context;

    public LoginTask (Context context) {
        this.context = context;
    }

    private String login(String username, String password) {

        String result = "false";
        try {

            URL httpURL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) httpURL.openConnection();
            connection.setRequestMethod("POST");
            //设置请求参数类型
            connection.setRequestProperty("Content-Type","application/json");
            connection.setReadTimeout(5000);
            connection.connect();

            //上传数据到服务器
            String loginInfo = "{\"username\":\""+username+"\",\"password\":\""+password+"\"}";
            OutputStream out = connection.getOutputStream();
            out.write(loginInfo.getBytes());

            //获取返回的状态码
            String code = new Integer(connection.getResponseCode()).toString();

            if ("200".equals(code)) {
                result = "true";
            } else {
                result = "false";
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        return result;
    }
    @Override
    protected String doInBackground(String... params) {
        return login(params[0],params[1]);
    }
}
