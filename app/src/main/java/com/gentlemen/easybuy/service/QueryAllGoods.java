package com.gentlemen.easybuy.service;

import android.os.AsyncTask;

import com.gentlemen.easybuy.model.MyURL;

import java.net.URL;

/**
 * Created by ljf-梁燕双栖 on 2016/6/30.
 */
public class QueryAllGoods extends AsyncTask<Void,Void,String> {

    private static final String url = MyURL.queryAllURL;
    @Override
    protected String doInBackground(Void... params) {

        try {
            URL httpURL = new URL(url);

        } catch (Exception e) {

        }
        return null;
    }
}
