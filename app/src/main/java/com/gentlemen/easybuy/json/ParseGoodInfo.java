package com.gentlemen.easybuy.json;

import com.gentlemen.easybuy.model.Good;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ljf-梁燕双栖 on 2016/6/30.
 */
public class ParseGoodInfo {

    private String jsonData;

    public ParseGoodInfo (String jsonData) {
        this.jsonData = jsonData;
    }

    public List<Good> parseGoodInfoJson () {
        List<Good> list = new ArrayList<>();
        try {

            JSONArray jsonArray = new JSONArray(jsonData);

            //遍历json数组，将json键值对映射到Good实体中
            for (int i = 0; i<jsonArray.length(); i++) {
                JSONObject goodObject = jsonArray.getJSONObject(i);

                //获取每一个字段
                int id = goodObject.getInt("id");
                int cid = goodObject.getInt("cid");
                int storage = goodObject.getInt("storage");
                String name = goodObject.getString("name");
                String description = goodObject.getString("description");
                String image = goodObject.getString("image");
                String unit = goodObject.getString("unit");
                double price = goodObject.getDouble("price");
                double offset = goodObject.getDouble("offset");
                Timestamp time = new Timestamp(goodObject.getLong("time"));

                //映射到实体中
                Good good = new Good(id, cid, name, price, offset, storage, unit, description, image,  time);
                list.add(good);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
