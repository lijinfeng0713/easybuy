package com.gentlemen.easybuy.service;

import android.os.AsyncTask;
import android.util.Log;

import com.gentlemen.easybuy.model.MyURL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ljf-梁燕双栖 on 2016/6/30.
 */
public class UserRegister extends AsyncTask<Void,Void,String> {

    private static String url = MyURL.REGISTER_URL;

    private String username;
    private String password;
    private String phone;
    private String address;

    public UserRegister(String username, String password, String phone, String address) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }


    @Override
    protected String doInBackground(Void... params) {

        try {
            URL httpURL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) httpURL.openConnection();
            connection.setRequestMethod("POST");
            connection.setReadTimeout(5000);

            connection.setRequestProperty("Content-Type","application/json");
            connection.connect();

            String registerInfo =  "{\"username\":\""+username+"\",\"password\":\""+password+"\",\"phone\":\""+phone+"\",\"address\":\""+address+"\"}";
            //提交数据到服务器
            OutputStream out = connection.getOutputStream();
            out.write(registerInfo.getBytes());

            String code = new Integer(connection.getResponseCode()).toString();

            if (code.equals("200")) {
                return "true";
            } else {
                return "false";
            }

        }  catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
