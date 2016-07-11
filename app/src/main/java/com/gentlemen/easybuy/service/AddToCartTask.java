package com.gentlemen.easybuy.service;

import android.os.AsyncTask;

import com.gentlemen.easybuy.model.MyURL;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ljf-梁燕双栖 on 2016/7/1.
 */
public class AddToCartTask extends AsyncTask<String,Void,String> {

    private static final String url = MyURL.ADD_TO_CART_URL;

    private int userId;
    private int goodId;
    private int sum;

    public AddToCartTask(int userId, int goodId, int sum) {
        this.userId = userId;
        this.goodId = goodId;
        this.sum = sum;
    }

    @Override
    protected String doInBackground(String... params) {

        try {
            URL httpURL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) httpURL.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/json");
            connection.setReadTimeout(5000);
            connection.connect();

            String content = "{\"uid\":"
                    +userId
                    +",\"gid\":"
                    +goodId
                    +",\"sum\":"
                    +sum
                    +"}";

            //上传数据到服务器
            OutputStream out = connection.getOutputStream();
            out.write(content.getBytes());

            String code = new Integer(connection.getResponseCode()).toString();
            if (code.equals("200")) {
                return "true";
            } else {
                return "false";
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
