package com.gentlemen.easybuy.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.gentlemen.easybuy.R;
import com.gentlemen.easybuy.model.Good;
import com.gentlemen.easybuy.service.AddToCartTask;

import java.util.concurrent.ExecutionException;

public class GoodDetailInfoActivity extends AppCompatActivity {

    private TextView nameTv;
    private TextView descriptionTv;
    private TextView priceTv;
    private TextView discountTv;

    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_detail_info);

        findViewById(R.id.btu_detail_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //加载控件
        nameTv = (TextView) findViewById(R.id.detail_name);
        descriptionTv = (TextView) findViewById(R.id.detail_description);
        priceTv = (TextView) findViewById(R.id.detail_price);
        discountTv = (TextView) findViewById(R.id.detail_discount);

        Intent intent = getIntent();
        final Good good = (Good) intent.getSerializableExtra("good");

        nameTv.setText(good.getName());
        descriptionTv.setText(good.getDescription());
        priceTv.setText(good.getPrice()+"元/"+good.getUnit());
        discountTv.setText("折扣："+String.valueOf(good.getOffset()));

        findViewById(R.id.btn_addToCart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddToCartTask task = new AddToCartTask(1,good.getId(),1);

                try {
                    result = task.execute().get();

                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (result.equals("true")) {
                                Toast.makeText(GoodDetailInfoActivity.this, "添加购物车成功！",Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(GoodDetailInfoActivity.this, "添加购物车失败！",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
