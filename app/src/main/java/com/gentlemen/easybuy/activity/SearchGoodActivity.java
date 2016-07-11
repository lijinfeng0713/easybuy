package com.gentlemen.easybuy.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.gentlemen.easybuy.R;
import com.gentlemen.easybuy.adapter.GoodAdapter;
import com.gentlemen.easybuy.json.ParseGoodInfo;
import com.gentlemen.easybuy.model.Good;
import com.gentlemen.easybuy.service.SearchGoodsTask;
import com.gentlemen.easybuy.service.ShowIndexTask;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class SearchGoodActivity extends AppCompatActivity {

    private GoodAdapter adapter;
    private static List<Good> list;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_good);

        listView = (ListView) findViewById(android.R.id.list);
        Intent i = getIntent();
        show(i.getStringExtra("condition"));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Good good = list.get(position);
                Intent intent = new Intent(SearchGoodActivity.this,GoodDetailInfoActivity.class);
                intent.putExtra("good",good);
                startActivity(intent);
            }
        });
    }

    private void show(String condition) {
        SearchGoodsTask task = new SearchGoodsTask(condition);

        try {
            final String jsonData = task.execute().get();
            System.out.println("-----"+jsonData);
            Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    list = new ParseGoodInfo(jsonData).parseGoodInfoJson();
                    if (list != null) {
                        adapter = new GoodAdapter(SearchGoodActivity.this,list);
                        listView.setAdapter(adapter);
                    } else {
                        Toast.makeText(SearchGoodActivity.this, "加载失败",Toast.LENGTH_SHORT).show();
                    }
                }
            });

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

}
