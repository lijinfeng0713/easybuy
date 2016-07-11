package com.gentlemen.easybuy.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.gentlemen.easybuy.R;
import com.gentlemen.easybuy.service.LoginTask;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private EditText editUsername;
    private EditText editPassword;

    private String result; //服务器返回结果

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editUsername = (EditText) findViewById(R.id.username);
        editPassword = (EditText) findViewById(R.id.password);

        //处理登录按钮点击事件
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //执行登录操作
                String username = editUsername.getText().toString().trim();
                String password = editPassword.getText().toString().trim();
                if ("".equals(username) || "".equals(password)) {
                    Toast.makeText(MainActivity.this,"请输入用户名和密码", Toast.LENGTH_SHORT).show();
                } else {
                    loginAsyncTask(username,password);
                }
            }
        });


        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void loginAsyncTask (String username, String password) {

        LoginTask task = new LoginTask(MainActivity.this);
        try {
            result = task.execute(username, password).get();
            System.out.println("result:"+result);
            Handler handler = new Handler();

            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (result.equals("true")) {
                        Toast.makeText(MainActivity.this, "登录成功！",Toast.LENGTH_SHORT).show();
                        //处理登录成功的操作
                        Intent intent = new Intent(MainActivity.this, IndexActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(MainActivity.this, "登录失败！",Toast.LENGTH_SHORT).show();
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
