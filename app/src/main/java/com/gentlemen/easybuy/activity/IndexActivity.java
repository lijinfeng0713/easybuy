package com.gentlemen.easybuy.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.gentlemen.easybuy.R;
import com.gentlemen.easybuy.adapter.GoodAdapter;
import com.gentlemen.easybuy.json.ParseGoodInfo;
import com.gentlemen.easybuy.model.Good;
import com.gentlemen.easybuy.service.ShowIndexTask;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class IndexActivity extends AppCompatActivity {

    private GoodAdapter adapter;
    private static List<Good> list;
    private ListView listView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        listView = (ListView) findViewById(android.R.id.list);
        editText = (EditText) findViewById(R.id.index_search);
        show();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Good good = list.get(position);
                Intent intent = new Intent(IndexActivity.this,GoodDetailInfoActivity.class);
                intent.putExtra("good",good);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_index_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IndexActivity.this, SearchGoodActivity.class);
                intent.putExtra("condition",editText.getText().toString().trim());
                startActivity(intent);
            }
        });
    }

    private void show() {
        ShowIndexTask task = new ShowIndexTask();

        try {
            final String jsonData = task.execute().get();
            System.out.println("-----"+jsonData);
            Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    list = new ParseGoodInfo(jsonData).parseGoodInfoJson();
                    if (list != null) {
                        adapter = new GoodAdapter(IndexActivity.this,list);
                        listView.setAdapter(adapter);
                    } else {
                        Toast.makeText(IndexActivity.this, "加载失败",Toast.LENGTH_SHORT).show();
                    }
                }
            });

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    private List<Good> getGoods() {
        Good good = new Good();
        good.setId(1);
        good.setCid(2);
        good.setName("手机");
        good.setOffset(1.0);
        good.setPrice(20);
        good.setDescription("好好好，六六六");
        good.setUnit("件");
        good.setTime(null);

        Good good2 = new Good();
        good2.setId(1);
        good2.setCid(2);
        good2.setName("手机");
        good2.setOffset(1.0);
        good2.setPrice(20);
        good2.setUnit("件");
        good2.setDescription("好好好，六六六");
        good2.setTime(null);


        List<Good> list = new ArrayList<>();
        list.add(good);
        list.add(good2);
        return list;
    }
}
